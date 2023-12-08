package client;

import shares.Weather;

/**
 * declare function get weather by id
 */
public interface GetWeatherRepository {
    Weather getById(String id);
}
