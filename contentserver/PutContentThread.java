package contentserver;

import shares.Constant;
import shares.LamportClock;
import shares.TimestampAndWeather;
import shares.Weather;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * this thread continuously scans files containing json weather in the folder and uploads them to the aggregation server,
 * response returns updates timestamp clock
 */
public class PutContentThread implements Runnable {
    private final WeatherRepository weatherRepository;
    private final PutContentRepository putContentRepository;
    private final LamportClock clock;

    public PutContentThread(LamportClock clock) {
        this.weatherRepository = new WeatherIORepository();
        this.putContentRepository = new PutContentHttpRepository();
        this.clock = clock;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println("Scanning folder ...");
            Set<String> listFiles = getListFiles(Constant.DIR_FOLDER_CONTENT);
            listFiles.forEach(s -> {
                try {
                    Weather weather = weatherRepository.getByDir(Constant.DIR_FOLDER_CONTENT + s);
                    if(weather != null){
                        long timestamp = putContentRepository.putContent(TimestampAndWeather.create(clock.getTimestamp(), weather));
                        clock.tick(timestamp);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * get name file in folder
     * @param dir
     * @return
     */
    public Set<String> getListFiles(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
