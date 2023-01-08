package pl.michal.wrona.tennisapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Court {
    static int maxCourtsCount = 0;
    private int id;
    private SurfaceCourt surface;
    private int openingHour;
    private int closingHour;
    private double pricePerHour;
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    public Court() {
    }


//TODO wzorzec projektowy fabryka -> factory JAVA

    public Court(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        maxCourtsCount++;
        this.id = maxCourtsCount;
        this.surface = surface;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = pricePerHour;
    }

    public Court(int id, SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        this.id = id;
        maxCourtsCount = id;
        this.surface = surface;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = pricePerHour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public double getPricePerHour() {
        return pricePerHour;
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

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public String toString() {
        return id + "," + getSurface().name() + "," + openingHour + "," + closingHour + "," + pricePerHour;

    }


    public Court fromString(String[] properties) {
        return new Court(Integer.parseInt(properties[0]),
                SurfaceCourt.valueOf(properties[1]),
                Integer.parseInt(properties[2]),
                Integer.parseInt(properties[3]),
                Double.parseDouble(properties[4]));
    }
}