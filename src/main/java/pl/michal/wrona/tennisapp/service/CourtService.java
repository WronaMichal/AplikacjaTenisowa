package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.exception.AccessDeniedException;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;

import java.util.List;

public class CourtService {
    private List<Court> courtsList;
    public void addCourt(String surface, int openingHour, int closingHour, User user){
        if(!user.isAdmin()){
            throw new AccessDeniedException("Only Admin can add court");
        }
        courtsList.add(new Court("grass",12,13));
    }
}
