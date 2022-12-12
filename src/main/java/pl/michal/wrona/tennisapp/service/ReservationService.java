package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ReservationService {
    UserService userService;
    CourtService courtService;
    private List<Reservation> reservations = new ArrayList<>();

    public ReservationService(UserService userService, CourtService courtService) {
        addReservation(LocalDateTime.of(2022, 12, 10, 12, 0),
                LocalDateTime.of(2022, 12, 10, 14, 0),
                userService.getUserByPhoneNumber("11111"),
                courtService.getCourt(SurfaceCourt.GRASS, 10, 21, 60));

        addReservation(LocalDateTime.of(2022, 12, 10, 12, 0),
                LocalDateTime.of(2022, 12, 10, 14, 0),
                userService.getUserByPhoneNumber("22222"),
                courtService.getCourt(SurfaceCourt.HARD, 8, 22, 50));

        addReservation(LocalDateTime.of(2022, 12, 10, 12, 0),
                LocalDateTime.of(2022, 12, 10, 14, 0),
                userService.getUserByPhoneNumber("33333"),
                courtService.getCourt(SurfaceCourt.CLAY, 9, 20, 40));

        addReservation(LocalDateTime.of(2022, 12, 10, 12, 0),
                LocalDateTime.of(2022, 12, 10, 14, 0),
                userService.getUserByPhoneNumber("44444"),
                courtService.getCourt(SurfaceCourt.CLAY, 9, 21, 40));

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
        reservations.add(reservation);
        return reservation;
    }

    public List <Reservation> getUserReservations(User user){
        return reservations.stream()
                .filter(r -> r.getUser().equals(user))
                .collect(Collectors.toList());
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Court> listCourts(LocalDateTime from, LocalDateTime to, String courtSurface) {
        return new ArrayList<>();
    }


}
