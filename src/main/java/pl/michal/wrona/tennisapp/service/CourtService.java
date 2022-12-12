package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.exception.AccessDeniedException;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourtService {
    private List<Court> courtsList = new ArrayList<>();

    public CourtService() {
        courtsList.add(new Court(SurfaceCourt.GRASS, 10, 21, 60));
        courtsList.add(new Court(SurfaceCourt.HARD, 8, 22, 50));
        courtsList.add(new Court(SurfaceCourt.CLAY, 9, 20, 40));
        courtsList.add(new Court(SurfaceCourt.CLAY, 9, 21, 40));
    }

    public List<Court> getCourtsList() {
        return courtsList;
    }

    public Court getCourt(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        Optional<Court> selectedCourt = courtsList.stream()
                .filter(c -> c.getSurface().equals(surface) && c.getOpeningHour()==openingHour
                        && c.getClosingHour()==closingHour && c.getPricePerHour() == pricePerHour)
                .findAny();
        return selectedCourt.orElse(null);
    }

    public int getMinOpeningHour() {
        return courtsList.stream()
                .map(Court::getOpeningHour).min(Comparator.comparingInt(Integer::valueOf))
                .get();
    }

    // TODO jak chcesz filtrować korty, za pomocą parametrów jakie wybierze użytkownik -> zrób z Danielem. Zrob filtr aby zgadzał się z typem kortu -> zrobione
    //TODO napisać noneMatch -> overlapping dates java -> zrobione
    //TODO https://community.coda.io/t/checking-for-date-overlaps-the-most-optimal-formula/25879 -> zrobione
    public List<Court> getCourtsByCriteria(LocalDateTime dateFrom, LocalDateTime dateTo, String courtType) {
        return courtsList.stream()
                .filter(c -> c.getSurface().toString().equals(courtType))
                .filter(c -> c.getReservations()
                        .stream()
                        .noneMatch(r -> r.getFrom().isBefore(dateTo)
                                && r.getTo().isAfter(dateFrom)))
                .collect(Collectors.toList());
    }

    public int getMaxClosingHour() {
        return courtsList.stream().map(Court::getClosingHour).max(Comparator.comparingInt(Integer::valueOf)).get();
    }

    public void addCourt(String surface, int openingHour, int closingHour, User user) {
        if (!user.isAdmin()) {
            throw new AccessDeniedException("Only Admin can add court");
        }

    }
}
