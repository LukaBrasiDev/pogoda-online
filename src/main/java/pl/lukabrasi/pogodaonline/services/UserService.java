package pl.lukabrasi.pogodaonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukabrasi.pogodaonline.entities.UserEntity;
import pl.lukabrasi.pogodaonline.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }


    public boolean addUser(String login, String password, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setEmail(email);
        userEntity.setPassword(password);


        if (userRepository.existsByLogin(login)) {
            return false;
        }
        return userRepository.save(userEntity) != null;
    }

    public boolean tryLogin(String login, String password) {
        Optional<UserEntity> userEntity = userRepository.findByLoginAndPassword(login, password);

        if (userEntity.isPresent()) {
            userSession.setUserLogin(true);
            userSession.setLogin(login);
        }
        return userEntity.isPresent();
    }

    public void logout() {
        userSession.setUserLogin(false);
        userSession.setLogin(null);
    }


}
