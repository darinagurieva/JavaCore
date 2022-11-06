package weather_api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public  class TempValue {

    @JsonProperty("Value")
    public double value;
    @JsonProperty("Unit")
    public String unit;
    @JsonProperty("UnitType")
    public int unitType;
}
