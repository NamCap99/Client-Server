package aggregationserver;

import shares.LamportClock;
import shares.TimestampAndWeather;
import shares.WeatherQueue;

/**
 * The service class includes functions to create or update weather
 */

public class CreateOrUpdateWeatherService {
    private final LamportClock lamportClock;
    private final WeatherQueue weatherQueue;

    public CreateOrUpdateWeatherService() {
        this.lamportClock = LamportClockSingleton.getInstance();
        this.weatherQueue = WeatherQueueSingleton.getInstance();
    }

    /**
     * update timestamp, then put weather in queue
     * @param timestampAndWeather
     * @return
     */
    public long createOrUpdate(TimestampAndWeather timestampAndWeather){
        long newTimestamp = lamportClock.tick(timestampAndWeather.getTimestamp());
        timestampAndWeather.setTimestamp(newTimestamp);
        weatherQueue.add(timestampAndWeather);
        return newTimestamp;
    }


}
