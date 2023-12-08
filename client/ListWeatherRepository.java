package client;

import shares.Weather;

import java.util.List;

/**
 * declare function get list weather
 */
public interface ListWeatherRepository {
    List<Weather> list();
}
