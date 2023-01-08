package pl.michal.wrona.tennisapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;


import java.time.LocalDateTime;


public class Reservation {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    @JsonIgnore
    private User user;
    private String userPhoneNumber;
    static int maxReservationCount = 0;
    @JsonIgnore
    private Court court;
    private int courtId;




    public Reservation(int id, LocalDateTime from, LocalDateTime to, User user, Court court) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
        this.userPhoneNumber = user.getPhoneNumber();
        this.courtId = court.getId();

        if (id > maxReservationCount) {
            maxReservationCount = id;
        }

    }

    public Reservation() {
    }

    public Reservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        maxReservationCount++;
        this.id = maxReservationCount;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
        this.userPhoneNumber = user.getPhoneNumber();
        this.courtId = court.getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public static void setMaxReservationCount(int maxReservationCount) {
        Reservation.maxReservationCount = maxReservationCount;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }


    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }


    public LocalDateTime getTo() {
        return to;
    }


    public User getUser() {
        return user;
    }


    public Court getCourt() {
        return court;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public int getCourtId() {
        return courtId;
    }

    @Override
    public String toString() {
        return from + "," + to + "," + userPhoneNumber + "," + courtId;
    }


}
