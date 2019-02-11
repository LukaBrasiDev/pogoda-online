package pl.lukabrasi.pogodaonline.auth.services;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.lukabrasi.pogodaonline.auth.entities.UserEntity;


@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSession {
    private boolean isLogin;
    private UserEntity userEntity;
}