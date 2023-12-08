package contentserver;

import com.fasterxml.jackson.core.type.TypeReference;
import shares.MyObjectMapper;
import shares.Weather;

import java.io.File;
import java.io.IOException;

/**
 * Implement get weather by dir function
 */
public class WeatherIORepository implements WeatherRepository{
    @Override
    public Weather getByDir(String dir) throws IOException {
        File file = new File(dir);

        if(!file.isFile()){
            file.createNewFile();
        }

        if (file.length() == 0) return null;

        return MyObjectMapper.readFile(file,  new TypeReference<>() {});
    }
}
