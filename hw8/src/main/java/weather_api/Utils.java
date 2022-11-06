package weather_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Utils {

    private static final ObjectMapper MAPPER = JsonMapper.builder()
        .findAndAddModules()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .build();

    private Utils() { }

    public static ObjectMapper getObjectMapper() {

        return MAPPER;
    }
}
