package weather_api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {

    @JsonProperty("Minimum")
    public TempValue minimum;
    @JsonProperty("Maximum")
    public TempValue maximum;
}
