package pl.lukabrasi.pogodaonline.weather.entities;


import lombok.Data;
import pl.lukabrasi.pogodaonline.auth.entities.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "weather_log")
public class WeatherLogEntity {
    private @Id
    @GeneratedValue
    int id;
    private @Column(name = "city_name")
    String cityName;
    private @Column(name = "city_temp")
    double cityTemp;
    private @Column(name = "query_time")
    LocalDateTime queryTime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "WeatherLogEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityTemp=" + cityTemp +
                ", queryTime=" + queryTime +
                //     ", user=" + user +
                '}';
    }
}