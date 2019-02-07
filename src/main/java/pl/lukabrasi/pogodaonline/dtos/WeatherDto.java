package pl.lukabrasi.pogodaonline.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherDto {

    private TemperatureDto main;
    private CloudsDto clouds;
    private WindDto wind;
    private String name;
    @JsonProperty("dt_txt")
    private String date;


}
