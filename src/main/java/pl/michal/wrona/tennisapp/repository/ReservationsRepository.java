package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;

import java.util.List;
import java.util.Map;

public interface ReservationsRepository {
    List<Reservation> findAll(List<Court> courtList, Map<String, User> userMap);
    void save (Reservation reservation);
    Reservation findById(int reservationId, List<Court> courtList, List<User> userList);
}
