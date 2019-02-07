package pl.lukabrasi.pogodaonline.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastDto {

    @JsonProperty("list")
    private List<WeatherDto> weatherDtoForecast;
}
