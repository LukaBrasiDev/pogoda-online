package pl.lukabrasi.pogodaonline.weather.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;

@Repository
public interface WeatherLogRepository extends CrudRepository<WeatherLogEntity, Integer> {


}