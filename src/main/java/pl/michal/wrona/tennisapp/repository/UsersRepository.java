package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll();
}
