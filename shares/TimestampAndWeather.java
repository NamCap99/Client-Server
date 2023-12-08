package shares;

/**
 * The TimestampAndWeather entity class contains timestamp and weather read from the content server
 */

public class TimestampAndWeather {
    private long timestamp;
    private Weather weather;


    public TimestampAndWeather(long timestamp, Weather weather) {
        this.timestamp = timestamp;
        this.weather = weather;
    }

    public TimestampAndWeather() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public static TimestampAndWeather create(long timestamp, Weather weather){
        return new TimestampAndWeather(timestamp, weather);
    }
}
