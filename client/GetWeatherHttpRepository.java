package client;

import com.fasterxml.jackson.core.type.TypeReference;
import shares.*;
import shares.LamportClock;
import shares.Weather;

import java.util.HashMap;
import java.util.Map;

/**
 * get weather by id function, call api from aggregation server
 */
public class GetWeatherHttpRepository extends HandlerResponseHttp<Weather> implements GetWeatherRepository {
    private final HttpCaller httpCaller;
    private final LamportClock lamportClock;

    public GetWeatherHttpRepository(LamportClock lamportClock) {
        this.lamportClock = lamportClock;
        this.httpCaller = new HttpCaller();
    }

    /**
     * call api get by id passing timestamp and id weather
     * @param id
     * @return Weather
     */
    @Override
    public Weather getById(String id) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("request-time", String.valueOf(lamportClock.getTimestamp()));
            params.put("id", id);

            String result = httpCaller.get(Constant.GET_WEATHER_API, null, params, null, 10000);
            ResponseBody responseBody = MyObjectMapper.deserialize(result, ResponseBody.class);

            lamportClock.tick(responseBody.getTimestamp());

            return handlerResponse(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Weather handlerResponseSuccess(Object result) {
        PagingPayload<Weather> pagingPayload = MyObjectMapper.deserialize(MyObjectMapper.serialize(result), new TypeReference<>() {});
        return pagingPayload.getData();
    }
}
