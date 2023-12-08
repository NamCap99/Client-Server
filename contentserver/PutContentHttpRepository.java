package contentserver;

import shares.Constant;
import shares.HttpCaller;
import shares.MyObjectMapper;
import shares.ResponseBody;
import shares.TimestampAndWeather;
import org.json.JSONObject;

/**
 * The class implements the put content function from the api provided from the aggregation server
 */
public class PutContentHttpRepository implements PutContentRepository{
    private final HttpCaller httpCaller;

    public PutContentHttpRepository() {
        this.httpCaller = new HttpCaller();
    }

    @Override
    public long putContent(TimestampAndWeather timestampAndWeather) {
        try {
            String inputString = MyObjectMapper.serialize(timestampAndWeather);
            String result = httpCaller.put(Constant.UPDATE_WEATHER_API, null, new JSONObject(inputString), null, 10000, HttpCaller.ContentType.APPLICATION_JSON);
            ResponseBody responseBody = MyObjectMapper.deserialize(result, ResponseBody.class);

            return responseBody.getTimestamp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
