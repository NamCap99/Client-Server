package aggregationserver;

import shares.WeatherQueue;

/**
 * Singleton class
 */
public class WeatherQueueSingleton {
    private static volatile WeatherQueue instance;

    private WeatherQueueSingleton() {
    }

    public static synchronized WeatherQueue getInstance() {
        if (instance == null) {
            instance = new WeatherQueue();
        }
        return instance;
    }
}
