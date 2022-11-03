import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Properties;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    // private static final String FORECAST_ENDPOINT = "forecasts";
    // private static final String CURRENT_CONDITIONS_ENDPOINT =
    // "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    static Properties prop = new Properties();

    @Override
    public void getWeather(Periods periods) throws IOException {
        final SearchLocationResponse city = detectCityKey();

        if (periods.equals(Periods.NOW)) {

            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment("forecasts")
                    .addPathSegment("v1")
                    .addPathSegment("daily")
                    .addPathSegment("1day")
                    .addPathSegment(city.key)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            ObjectMapper objectMapper = new ObjectMapper();
            ForecastResponse jsonResponse = objectMapper.readValue(response.body().string(), ForecastResponse.class);

            String result = "";
            for (DailyForecast dailyForecast : jsonResponse.dailyForecasts) {

                result += String.format(
                        "В городе %s на дату %s ожидается %s, температура - %s\n",
                        city.localizedName,
                        dailyForecast.date,
                        dailyForecast.day.iconPhrase,
                        dailyForecast.temperature.maximum.value + dailyForecast.temperature.maximum.unit);
            }

            System.out.println(result);
        } else if (periods.equals(Periods.FIVE_DAYS)) {

            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment("forecasts")
                    .addPathSegment("v1")
                    .addPathSegment("daily")
                    .addPathSegment("5day")
                    .addPathSegment(city.key)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            ObjectMapper objectMapper = new ObjectMapper();
            ForecastResponse jsonResponse = objectMapper.readValue(response.body().string(), ForecastResponse.class);

            String result = "";
            for (DailyForecast dailyForecast : jsonResponse.dailyForecasts) {

                result += String.format(
                        "В городе %s на дату %s ожидается %s, температура - %s\n",
                        city.localizedName,
                        dailyForecast.date,
                        dailyForecast.day.iconPhrase,
                        dailyForecast.temperature.maximum.value + dailyForecast.temperature.maximum.unit);
            }

            System.out.println(result);

        }
    }

    public SearchLocationResponse detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationUrl)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unable to read city info. " +
                    "Server response code = " + response.code() + " response body = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Searching city " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocallizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizesName").asText();
            System.out.println("City found: " + cityName + " in country: " + countryName);
        } else
            throw new IOException("Server returns 0 cities");

        return objectMapper.readValue(objectMapper.readTree(jsonResponse).get(0).toString(),
                SearchLocationResponse.class);
    }
}
