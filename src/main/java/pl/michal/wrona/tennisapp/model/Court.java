package pl.michal.wrona.tennisapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Court {
    static int numberOfCourt=0;
    private int id;
    private String surface;
    private int openingHour;
    private int closingHour;
    private List<Reservation> reservations = new ArrayList<>();

    public Court(String surface, int openingHour, int closingHour) {
        numberOfCourt++;
        this.id = numberOfCourt;
        this.surface = surface;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }


    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}