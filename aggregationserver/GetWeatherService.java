package aggregationserver;

import shares.Weather;

import java.io.IOException;

/**
 * The service class serves to get weather by id
 */

public class GetWeatherService {
    private final WeatherRepository weatherRepository;

    public GetWeatherService() {
        try {
            this.weatherRepository = new WeatherIORepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Weather getById(String id) throws Exception {
        Weather weather = weatherRepository.getById(id);
        if(weather == null) throw new Exception(String.format("Weather[id=%s] not found", id));
        return weather;
    }
}
