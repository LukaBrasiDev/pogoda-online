package pl.lukabrasi.pogodaonline.weather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lukabrasi.pogodaonline.auth.services.UserSession;
import pl.lukabrasi.pogodaonline.weather.dtos.ForecastDto;
import pl.lukabrasi.pogodaonline.weather.dtos.WeatherDto;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;
import pl.lukabrasi.pogodaonline.weather.forms.WeatherForm;
import pl.lukabrasi.pogodaonline.weather.mappers.WeatherDtoToWeatherEntityMapper;
import pl.lukabrasi.pogodaonline.weather.repositories.WeatherLogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherLogService {
    final WeatherLogRepository weatherLogRepository;
    final UserSession userSession;

    @Autowired
    public WeatherLogService(WeatherLogRepository weatherLogRepository, UserSession userSession) {
        this.weatherLogRepository = weatherLogRepository;
        this.userSession = userSession;
    }


    public boolean saveWeatherLog(WeatherDto weatherDto) {
        WeatherLogEntity weatherLogEntity = WeatherDtoToWeatherEntityMapper.convert(weatherDto);
        weatherLogEntity.setUser(userSession.getUserEntity());
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

    public List<WeatherLogEntity> getWeatherLogFromUser() {

        return weatherLogRepository.findAllByUser(userSession.getUserEntity());
    }


    public void deleteWeatherLogEntityById(int id) {

        weatherLogRepository.deleteById(id);

    }

    public WeatherLogEntity getWeatherLogEntityById(int id) {
        Optional<WeatherLogEntity> optionalWeatherLogEntity = weatherLogRepository.findById(id);
        if (optionalWeatherLogEntity.isPresent()) {
            return optionalWeatherLogEntity.get();
        }
        return null;

    }

    public void updateWeatherLogEntity(int id, WeatherForm weatherForm) {


        Optional<WeatherLogEntity> optionalWeatherLogEntity = weatherLogRepository.findById(id);

        optionalWeatherLogEntity.get().setCityName(weatherForm.getCityName());
        optionalWeatherLogEntity.get().setCityTemp(weatherForm.getCityTemp());
        //  optionalWeatherLogEntity.get().setQueryTime(weatherForm.getQueryTime());
        weatherLogRepository.save(optionalWeatherLogEntity.get());


    }


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
