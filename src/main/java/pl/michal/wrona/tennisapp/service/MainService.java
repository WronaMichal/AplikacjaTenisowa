package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.repository.ReservationsFileRepository;
import pl.michal.wrona.tennisapp.repository.ReservationsRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MainService {
    CourtService courtService;
    UserService userService;
    ReservationService reservationService;

    public MainService(CourtService courtService, UserService userService, ReservationService reservationService) {
        this.courtService = courtService;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    public List<Court> getCourtsByCriteria(LocalDateTime dateFrom, LocalDateTime dateTo, String courtType) {
        return courtService.getCourtsByCriteria(dateFrom,dateTo,courtType);
    }

    public User login(String userName, String password) {
        return userService.login(userName, password);
    }

    public User register(String phoneNumber, String password, String emailAddress) {
        return userService.register(phoneNumber, password, emailAddress);

    }

    public int getMinOpeningHour(){
        return courtService.getMinOpeningHour();
    }

    public int getMaxClosingHour(){
        return courtService.getMaxClosingHour();
    }

    public Reservation addReservation(LocalDateTime from, LocalDateTime to, User user, Court court){
        return reservationService.addReservation(from, to, user, court);
    }

    public User getActiveUser(){
        return userService.getActiveUser();
    }

    public User getUser(String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    public Court getCourt(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour){
        return courtService.getCourt(surface, openingHour, closingHour, pricePerHour);
    }

    public List <Reservation> getUserReservations(User user){
        return reservationService.getUserReservations(user);
    }
    public void saveReservation (Reservation reservation){
        reservationService.save(reservation);
    }


}
