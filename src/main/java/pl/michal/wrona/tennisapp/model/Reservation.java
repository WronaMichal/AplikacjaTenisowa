package pl.michal.wrona.tennisapp.model;

import java.time.LocalDateTime;

public class Reservation {
    private LocalDateTime from;
    private LocalDateTime to;
    private User user;
    private Court court;

    public Reservation(LocalDateTime from, LocalDateTime to, User user, Court court) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.court = court;
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
}
