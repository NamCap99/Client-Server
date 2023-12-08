package shares;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * the WeatherQueue entity class contains a PriorityQueue sorted by timestamp from smallest to largest
 * the goal is that content server requests with the smallest timestamp will be processed first, followed by requests with larger timestamps.
 * functions use synchronized to synchronize only at a time when there is only 1 request to access the queue processing
 */
public class WeatherQueue {
    private final Queue<TimestampAndWeather> queue;

    public WeatherQueue() {
        this.queue = new PriorityQueue<>((t1, t2) -> Long.compare(t1.getTimestamp(), t2.getTimestamp()));
    }

    public void add(TimestampAndWeather timestampAndWeather){
        synchronized (queue){
            queue.add(timestampAndWeather);
        }
    }

    public Weather remove(){
        synchronized (queue){
            TimestampAndWeather timestampAndWeather = queue.remove();
            return timestampAndWeather.getWeather();
        }
    }

    public int size(){
        synchronized (queue){
            return queue.size();
        }
    }
}
