package weather_api;

import weather_api.responses.DailyForecast;
import weather_api.responses.SearchLocationResponse;

public class Forecast {

    public String city;
    public String localDate;
    public String weatherText;
    public double temperature;

    public static Forecast fromApi(DailyForecast forecast, SearchLocationResponse city) {

        final Forecast result = new Forecast();
        result.city = city.localizedName;
        result.localDate = forecast.date.toString();
        result.weatherText = forecast.day.iconPhrase;
        result.temperature = forecast.temperature.maximum.value;

        return result;
    }

    @Override
    public String toString() {

        return String.format(
            "В городе %s на дату %s ожидается %s, температура - %f C",
            city,
            localDate,
            weatherText,
            temperature
        );
    }
}
