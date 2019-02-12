package pl.lukabrasi.pogodaonline.weather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lukabrasi.pogodaonline.weather.dtos.ForecastDto;
import pl.lukabrasi.pogodaonline.weather.dtos.WeatherDto;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;
import pl.lukabrasi.pogodaonline.weather.mappers.WeatherDtoToWeatherEntityMapper;
import pl.lukabrasi.pogodaonline.weather.repositories.WeatherLogRepository;

@Service
public class WeatherLogService {
    final WeatherLogRepository weatherLogRepository;

    @Autowired
    public WeatherLogService(WeatherLogRepository weatherLogRepository) {
        this.weatherLogRepository = weatherLogRepository;
    }

    public boolean saveWeatherLog(WeatherDto weatherDto) {
        WeatherLogEntity weatherLogEntity = WeatherDtoToWeatherEntityMapper.convert(weatherDto);
        return weatherLogRepository.save(weatherLogEntity) != null;

    }

    @Value("${api.openweathermap.key}")
    String apiKey;

    public WeatherDto getCurrentWeather(String cityName) {
        RestTemplate restTemplate = getRestTemplate();
        WeatherDto weatherDto = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=" + apiKey, WeatherDto.class);
        saveWeatherLog(weatherDto);
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
