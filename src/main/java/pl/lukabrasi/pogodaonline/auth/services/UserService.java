package pl.lukabrasi.pogodaonline.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lukabrasi.pogodaonline.auth.entities.UserEntity;
import pl.lukabrasi.pogodaonline.auth.forms.LoginForm;
import pl.lukabrasi.pogodaonline.auth.forms.RegisterForm;
import pl.lukabrasi.pogodaonline.auth.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    public boolean registerUser(RegisterForm registerForm) {

        if (!isLoginFree(registerForm.getLogin())) {
            return false;

        }

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(registerForm.getLogin());
        userEntity.setPassword(getBCrypt().encode(registerForm.getPassword()));
        userEntity.setEmail(registerForm.getEmail());

        userRepository.save(userEntity);
        return true;

    }

    final UserRepository userRepository;
    final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }


/*    public boolean addUser(String login, String password, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setEmail(email);
        userEntity.setPassword(password);


        if (userRepository.existsByLogin(login)) {
            return false;
        }
        return userRepository.save(userEntity) != null;
    }*/

    private boolean isLoginFree(String login) {
        return !userRepository.existsByLogin(login);
    }

    public LoginResponse login(LoginForm loginForm) {
        Optional<UserEntity> userOptional = userRepository.findByLogin(loginForm.getLogin());
        if (!userOptional.isPresent()) {
            return LoginResponse.BAD_CREDENTIALS;
        }

        if (!getBCrypt().matches(loginForm.getPassword(), userOptional.get().getPassword())) {
            return LoginResponse.BAD_CREDENTIALS;
        }

        userSession.setLogin(true);
        userSession.setUserEntity(userOptional.get());
        return LoginResponse.SUCCESS;
    }

    public void logout() {
        userSession.setLogin(false);
        userSession.setUserEntity(null);
    }

    @Bean
    public BCryptPasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder();
    }


    public enum LoginResponse {
        SUCCESS,
        BAD_CREDENTIALS,
        BANNED
    }
}
