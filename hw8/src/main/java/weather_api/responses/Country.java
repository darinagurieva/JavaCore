package weather_api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

    @JsonProperty("ID")
    public String id;
    @JsonProperty("LocalizedName")
    public String localizedName;
}
