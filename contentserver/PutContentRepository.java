package contentserver;

import shares.TimestampAndWeather;

/**
 * interface declares put content
 */
public interface PutContentRepository {
    long putContent(TimestampAndWeather timestampAndWeather);
}
