package shares;

import java.util.concurrent.atomic.AtomicLong;

/**
 * LamportClock entity class contains timestamp for aggregation server
 */
public class LamportClock {
    private final AtomicLong timestamp;

    public LamportClock() {
        this.timestamp = new AtomicLong(1);
    }

    /**
     * Get the max of request time and server time then ++
     * @param requestTime
     * @return timestamp
     */
    public long tick(long requestTime){
        synchronized (timestamp){
            System.out.printf("Current timestamp lamport clock %d%n", timestamp.get());
            long timestampMax = Long.max(timestamp.get(), requestTime);
            timestamp.set(timestampMax);
            long timestampAfter = timestamp.incrementAndGet();
            System.out.printf("After process timestamp lamport clock %d%n", timestampAfter);
            return timestampAfter;
        }
    }

    public long getTimestamp() {
        synchronized (timestamp) {
            return timestamp.get();
        }
    }
}
