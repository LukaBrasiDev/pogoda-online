package pl.lukabrasi.pogodaonline.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukabrasi.pogodaonline.auth.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {//<nazwa encji, typ id>
    boolean existsByLogin(String login);

    Optional<UserEntity> findByLogin(String login);

}
