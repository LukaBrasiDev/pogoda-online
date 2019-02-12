package pl.lukabrasi.pogodaonline.weather.dtos;

import lombok.Data;

@Data
public class TemperatureDto {
    private int temp;
    private int pressure;
}
