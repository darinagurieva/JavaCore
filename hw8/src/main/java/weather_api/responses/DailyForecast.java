package weather_api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalDate;
import java.util.ArrayList;

public class DailyForecast {

    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    public LocalDate date;
    @JsonProperty("EpochDate")
    public int epochDate;
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("Day")
    public DayInfo day;
    @JsonProperty("Night")
    public DayInfo night;
    @JsonProperty("Sources")
    public ArrayList<String> sources;
    @JsonProperty("MobileLink")
    public String mobileLink;
    @JsonProperty("Link")
    public String link;


}
