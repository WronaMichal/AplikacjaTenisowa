package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.repository.ReservationsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ReservationService {
    UserService userService;
    CourtService courtService;
    private List<Reservation> reservationsList = new ArrayList<>();
    private ReservationsRepository reservationsRepository;

    public ReservationService(UserService userService, CourtService courtService, ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
        List<User> usersList = userService.getUsersList();
        Map<String, User> userMap = userService.getUserMap();
        reservationsList = reservationsRepository.findAll(courtService.getCourtsList(), userService.getUserMap());
        System.out.println(reservationsList);
    }
    /**
     * 1. check if time from, to exists.
     * 2. check if court with such surface has free spots in this particular time frame.
     * 3. check if weather allows user to make reservation
     *
     * @param from
     * @param to
     * @param user
     * @param court
     */

    public Reservation addReservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        Reservation reservation = new Reservation(from, to, user, court);
        court.addReservation(reservation);
        reservationsList.add(reservation);
        return reservation;
    }

    public List <Reservation> getUserReservations(User user){
        return reservationsList.stream()
                .filter(r -> r.getUser().equals(user))
                .collect(Collectors.toList());
    }


    public List<Reservation> getReservationsList() {
        return reservationsList;
    }

    public List<Court> listCourts(LocalDateTime from, LocalDateTime to, String courtSurface) {
        return new ArrayList<>();
    }

    public void save(Reservation reservation){
        reservationsRepository.save(reservation);
    }


}
