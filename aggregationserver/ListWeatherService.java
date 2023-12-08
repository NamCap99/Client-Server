package aggregationserver;

import shares.Weather;

import java.io.IOException;
import java.util.List;

/**
 * the service get list weather
 */

public class ListWeatherService {
    private final WeatherRepository weatherRepository;

    public ListWeatherService() {
        try {
            this.weatherRepository = new WeatherIORepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weather> list(){
        return weatherRepository.getAll();
    }
}
