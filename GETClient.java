import client.GetWeatherHttpRepository;
import client.GetWeatherRepository;
import client.ListWeatherHttpRepository;
import client.ListWeatherRepository;
import shares.LamportClock;
import shares.Weather;

import java.util.List;

public class GETClient
{
    /**
     * list, get weather
     * @param args
     */
    public static void main( String[] args )
    {
        LamportClock lamportClock = new LamportClock();

        // call api GET_WEATHER_API
        GetWeatherRepository getWeatherRepository = new GetWeatherHttpRepository(lamportClock);
        System.out.println(getWeatherRepository.getById("IDS60901"));

        // call api LIST_WEATHER_API
        ListWeatherRepository listWeatherRepository = new ListWeatherHttpRepository(lamportClock);
        List<Weather> weathers = listWeatherRepository.list();
        weathers.forEach(System.out::println);
    }
}
