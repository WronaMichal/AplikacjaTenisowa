package pl.michal.wrona.tennisapp.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String phoneNumber;
    private String password;
    private String emailAddress;
    private boolean isAdmin;

    private List<Reservation> reservations = new ArrayList<>();

    public User(String phoneNumber, String password, String emailAddress, boolean isAdmin) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAddress = emailAddress;
        this.isAdmin = isAdmin;
    }

    public User(){

    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation (Reservation reservation){
        reservations.add(reservation);
    }

    @Override
    public String toString() {
        return phoneNumber + "," + password +  "," + emailAddress +  "," + isAdmin;
    }

    public static User fromString(String[] properties){
        return new User(properties[0],
                properties[1],
                properties[2],
                Boolean.parseBoolean(properties[3]));
    }
}
