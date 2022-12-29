package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    List<User> findAll();
    void save (User user);
    Optional <User> findByPhoneNumber(String userId);
     User getByPhoneNumber(String userId);
}
