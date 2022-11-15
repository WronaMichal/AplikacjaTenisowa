package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    /**
     * 1. check if time from, to exists.
     * 2. check if court with such surface has free spots in this particular time frame.
     * 3. check if weather allows user to make reservation
     * @param from
     * @param to
     * @param user
     * @param court
     */
    public void addReservation(LocalDateTime from, LocalDateTime to, User user, Court court){
    }

    public List<Court> listCourts (LocalDateTime from, LocalDateTime to, String courtSurface, Boolean indoor){
        return new ArrayList<>();
    }
}
