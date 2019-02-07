package pl.lukabrasi.pogodaonline.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lukabrasi.pogodaonline.dtos.ForecastDto;
import pl.lukabrasi.pogodaonline.dtos.WeatherDto;

@Service
public class WeatherLogService {

    @Value("${api.openweathermap.key}")
    String apiKey;

    public WeatherDto getCurrentWeather(String cityName) {
        RestTemplate restTemplate = getRestTemplate();
        WeatherDto weatherDto = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=" + apiKey, WeatherDto.class);
        return weatherDto;
    }

    public ForecastDto getWeatherForecast(String cityName) {
        RestTemplate restTemplate = getRestTemplate();
        ForecastDto forecastDto = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=" + apiKey, ForecastDto.class);
        return forecastDto;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
