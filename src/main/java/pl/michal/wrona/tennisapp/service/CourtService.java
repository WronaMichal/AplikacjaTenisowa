package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.exception.AccessDeniedException;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.repository.CourtsRepository;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourtService {
    private List<Court> courtsList;
    private CourtsRepository courtsRepository;

    public CourtService(CourtsRepository courtsRepository) {
        this.courtsRepository = courtsRepository;
        courtsList = courtsRepository.findAll();
    }

    public List<Court> getCourtsList() {
        return courtsList;
    }

    public Court getCourt(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour) {
        Optional<Court> selectedCourt = courtsList.stream()
                .filter(c -> c.getSurface().equals(surface) && c.getOpeningHour() == openingHour
                        && c.getClosingHour() == closingHour && c.getPricePerHour() == pricePerHour)
                .findAny();
        return selectedCourt.orElse(null);
    }

    public int getMinOpeningHour() {
        return courtsList.stream()
                .map(Court::getOpeningHour).min(Comparator.comparingInt(Integer::valueOf))
                .get();
    }


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

    public void addCourt(SurfaceCourt surface, int openingHour, int closingHour, double pricePerHour, User user) {
        if (!user.isAdmin()) {
            throw new AccessDeniedException("Only Admin can add court");
        }
        Court court = new Court(surface, openingHour, closingHour, pricePerHour);
        courtsList.add(court);
        courtsRepository.save(court);
    }
}
