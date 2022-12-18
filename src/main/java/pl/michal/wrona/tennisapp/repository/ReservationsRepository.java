package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;

import java.util.List;

public interface ReservationsRepository {
    List<Reservation> findAll(List<Court> courtList, List<User> userList);
    void save (Reservation reservation);
}
