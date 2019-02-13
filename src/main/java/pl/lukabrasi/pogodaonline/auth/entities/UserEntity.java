package pl.lukabrasi.pogodaonline.auth.entities;

import lombok.Data;
import pl.lukabrasi.pogodaonline.weather.entities.WeatherLogEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "weather_user")
public class UserEntity {


    private @Id
    @GeneratedValue
    int id;
    private @Column(name = "login")
    String login;
    private @Column(name = "password")
    String password;
    private @Column(name = "email")
    String email;
    //czas rejestracji
    //status konta

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<WeatherLogEntity> weatherLog;


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                //  ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", weatherLog=" + weatherLog +
                '}';
    }
}
