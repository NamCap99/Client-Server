package aggregationserver;

import shares.Weather;

import java.io.IOException;
import java.util.List;

/**
 * interface declares functions get by id, get all, save weather
 */
public interface WeatherRepository {
    Weather getById(String id) throws IOException;
    List<Weather> getAll();
    void save(Weather weather);
}
