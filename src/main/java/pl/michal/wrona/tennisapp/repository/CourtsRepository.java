package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface CourtsRepository {
    List<Court> findAll();
    void save(Court court);

}
