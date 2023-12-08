package aggregationserver;

import shares.Weather;
import shares.WeatherQueue;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * process queue, save weather
 * scan thread, get weather và save
 */

public class HandlerDataService implements Runnable{
    private final WeatherQueue weatherQueue;
    private final WeatherRepository weatherRepository;

    public HandlerDataService() {
        this.weatherQueue = WeatherQueueSingleton.getInstance();
        try {
            this.weatherRepository = new WeatherIORepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture.runAsync(this);
    }

    @Override
    public void run() {
        while(true){
            while (weatherQueue.size() > 0){
                Weather weather = weatherQueue.remove();
                weatherRepository.save(weather);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
