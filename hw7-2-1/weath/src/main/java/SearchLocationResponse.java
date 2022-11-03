import com.fasterxml.jackson.annotation.JsonProperty;

class AdministrativeArea{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("LocalizedName")
    public String localizedName;
}

class Country{
    @JsonProperty("ID")
    public String iD;
    @JsonProperty("LocalizedName")
    public String localizedName;
}

public class SearchLocationResponse{
    @JsonProperty("Version")
    public int version;
    @JsonProperty("Key")
    public String key;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Rank")
    public int rank;
    @JsonProperty("LocalizedName")
    public String localizedName;
    @JsonProperty("Country")
    public Country country;
    @JsonProperty("AdministrativeArea")
    public AdministrativeArea administrativeArea;
}
