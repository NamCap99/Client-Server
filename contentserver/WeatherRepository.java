package contentserver;


import shares.Weather;

import java.io.IOException;

/**
 * interface declares function get weather by dir
 */
public interface WeatherRepository {
    Weather getByDir(String dir) throws IOException;
}
