package weather_api;

import jdbc_api.ForecastRepository;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import weather_api.responses.ForecastResponse;
import weather_api.responses.SearchLocationResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();

    private final ForecastRepository forecastRepository = new ForecastRepository();

    @Override
    public void getWeather(Periods periods) throws IOException {
        final SearchLocationResponse city = detectCityKey();

        var period = "";

        if (periods.equals(Periods.NOW)) {

            period = "1day";
        }
        else if (periods.equals(Periods.FIVE_DAYS)) {

            period = "5day";
        }
        else {

            throw new IllegalStateException();
        }

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment(period)
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

        ForecastResponse jsonResponse = Utils.getObjectMapper().readValue(response.body().string(), ForecastResponse.class);

        final List<Forecast> forecastList = jsonResponse.dailyForecasts.stream()
            .map(daily -> Forecast.fromApi(daily, city))
            .toList();

        forecastRepository.insert(forecastList);

        String result = forecastList
            .stream()
            .map(Forecast::toString)
            .collect(Collectors.joining("\n"));

        System.out.println(result);
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

        if (Utils.getObjectMapper().readTree(jsonResponse).size() > 0) {
            String cityName = Utils.getObjectMapper().readTree(jsonResponse).get(0).at("/LocallizedName").asText();
            String countryName = Utils.getObjectMapper().readTree(jsonResponse).get(0).at("/weather_api.responses.Country/LocalizesName").asText();
            System.out.println("City found: " + cityName + " in country: " + countryName);
        } else
            throw new IOException("Server returns 0 cities");

        return Utils.getObjectMapper().readValue(Utils.getObjectMapper().readTree(jsonResponse).get(0).toString(),
                SearchLocationResponse.class);
    }
}
