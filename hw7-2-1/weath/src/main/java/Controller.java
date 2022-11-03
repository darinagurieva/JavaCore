import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    /*
     * String variantsResult = "[{\"1\":\"GET_CURRENT_WEATHER\"}," +
     * "{\"2\":\"GET_WEATHER_IN_NEXT_5_DAYS\"}]";
     * ObjectMapper objectMapper = new ObjectMapper();
     * Map<Integer, Functionality<T>> variantResult =
     * objectMapper.readValue(variantsResult,
     * new TypeReference<Map<Integer, Functionality<T>>>() {
     * });
     */
    Map<Integer, Functionality> variantResult = new HashMap<>();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
    }

    public void onUserInput(String input) throws IOException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key" + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;

            default:
                System.out.println("No such definition.");
                break;

        }
    }

    public void getCurrentWeather() throws IOException {
        weatherProvider.getWeather(Periods.NOW);
    }

    public void getWeatherIn5Days() throws IOException {
        weatherProvider.getWeather(Periods.FIVE_DAYS);
    }
}