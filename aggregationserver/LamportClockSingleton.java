package aggregationserver;

import shares.LamportClock;

/**
 * Singleton class
 */
public class LamportClockSingleton {
    private static volatile LamportClock instance;

    private LamportClockSingleton() {
    }

    public static synchronized LamportClock getInstance() {
        if (instance == null) {
            instance = new LamportClock();
        }
        return instance;
    }
}
