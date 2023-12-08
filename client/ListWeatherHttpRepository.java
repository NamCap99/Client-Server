package client;

import com.fasterxml.jackson.core.type.TypeReference;
import shares.*;
import shares.LamportClock;
import shares.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * implement function list weather
 */
public class ListWeatherHttpRepository extends HandlerResponseHttp<List<Weather>> implements ListWeatherRepository {
    private final HttpCaller httpCaller;
    private final LamportClock lamportClock;

    public ListWeatherHttpRepository(LamportClock lamportClock) {
        this.lamportClock = lamportClock;
        this.httpCaller = new HttpCaller();
    }

    /**
     * call api list passing param timestamp
     * @return List<Weather>
     */
    @Override
    public List<Weather> list() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("request-time", String.valueOf(lamportClock.getTimestamp()));
            String result = httpCaller.get(Constant.LIST_WEATHER_API, null, params, null, 10000);
            ResponseBody responseBody = MyObjectMapper.deserialize(result, ResponseBody.class);

            lamportClock.tick(responseBody.getTimestamp());

            return handlerResponse(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<Weather> handlerResponseSuccess(Object result) {
        PagingPayload<List<Weather>> pagingPayload = MyObjectMapper.deserialize(MyObjectMapper.serialize(result), new TypeReference<>() {});
        return pagingPayload.getData();
    }
}
