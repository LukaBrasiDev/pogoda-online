package pl.lukabrasi.pogodaonline.auth.entities;

import lombok.Data;

import javax.persistence.*;

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

/*    @OneToMany(mappedBy = "user")
    private List<WeatherLogEntity> weatherLog;*/

}
