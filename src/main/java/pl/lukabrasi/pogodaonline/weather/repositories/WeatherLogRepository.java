package pl.lukabrasi.pogodaonline.weather.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukabrasi.pogodaonline.auth.entities.UserEntity;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherLogRepository extends CrudRepository<WeatherLogEntity, Integer> {

    List<WeatherLogEntity> findAllByUser(UserEntity userEntity);

    Optional<WeatherLogEntity> findById(int id);







}