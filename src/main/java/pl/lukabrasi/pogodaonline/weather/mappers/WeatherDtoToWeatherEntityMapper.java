package pl.lukabrasi.pogodaonline.weather.mappers;

import pl.lukabrasi.pogodaonline.weather.dtos.WeatherDto;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;

import java.time.LocalDateTime;

public class WeatherDtoToWeatherEntityMapper {


    public static WeatherLogEntity convert(WeatherDto weatherDto) {
        WeatherLogEntity weatherLogEntity = new WeatherLogEntity();
        weatherLogEntity.setCityName(weatherDto.getName());
        weatherLogEntity.setCityTemp(weatherDto.getMain().getTemp());
        weatherLogEntity.setQueryTime(LocalDateTime.now());

        return weatherLogEntity;
    }
}