package aggregationserver;

import com.fasterxml.jackson.core.type.TypeReference;
import shares.Constant;
import shares.MyObjectMapper;
import shares.Weather;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class inherits from the interface that implements the functions get by id, get all, save weather
 */

public class WeatherIORepository implements WeatherRepository{
    private final File file;

    public WeatherIORepository() throws IOException {
        this.file = new File(Constant.DIR_FILE_AGGREGATION);
        if(!file.isFile()){
            file.createNewFile();
        }
    }

    /**
     * get by id, first get all then get by id weather
     * @param id
     * @return Weather
     * @throws IOException
     */
    @Override
    public Weather getById(String id) throws IOException {
        List<Weather> weathers = getAll();
        for (Weather weather : weathers){
            if (weather.getId().equals(id)) return weather;
        }
        return null;
    }

    /**
     * get all from file
     * @return List<Weather>
     */
    @Override
    public List<Weather> getAll() {
        List<Weather> weathers = new ArrayList<>();
        if (file.length() == 0) return weathers;
        weathers = MyObjectMapper.readFile(file,  new TypeReference<>() {});
        return weathers;
    }

    /**
     * save weather if it is not there, it will be inserted, otherwise it will be updated. First, get all and then pass it to the map with the key as id
     * if the id is the same, overwrite it, otherwise add a new one
     * @param weather
     */
    @Override
    public void save(Weather weather) {
        List<Weather> weathers = getAll();
        Map<String, Weather> map = new LinkedHashMap<>();
        for (Weather w : weathers){
            map.put(w.getId(), w);
        }
        map.put(weather.getId(), weather);
        MyObjectMapper.writeFile(file, map.values());
        System.out.printf("Save success weather %s%n", MyObjectMapper.serialize(weather));
    }
}
