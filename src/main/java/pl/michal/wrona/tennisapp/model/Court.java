package pl.michal.wrona.tennisapp.model;

import java.util.ArrayList;
import java.util.List;

public class Court {
    static int courtsCount =0;
    private int id;
    private SurfaceCourt surface;
    private int openingHour;
    private int closingHour;
    private double pricePerHour;
    private List<Reservation> reservations = new ArrayList<>();

    public Court(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        courtsCount++;
        this.id = courtsCount;
        this.surface = surface;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public SurfaceCourt getSurface() {
        return surface;
    }

    public void setSurface(SurfaceCourt surface) {
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

    public void addReservation (Reservation reservation){
        reservations.add(reservation);
    }
}