package pl.michal.wrona.tennisapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDateTime;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Reservation {
    private LocalDateTime from;
    private LocalDateTime to;
    private User user;
    static int maxReservationCount = 0;
    private int id;
    private Court court;

    //TODO pododawać court id i user id po to aby przy JSONie można było połączyć

    public Reservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        maxReservationCount++;
        this.id = maxReservationCount;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
    }


    public Reservation(int id, LocalDateTime from, LocalDateTime to, User user, Court court) {
        this.id = id;
        maxReservationCount = id;
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
    }



    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Court getCourt() {
        return court;
    }


    @Override
    public String toString() {
        return from + "," + to + "," + user.getPhoneNumber() + "," + court.getId();
    }


}
