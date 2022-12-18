package pl.michal.wrona.tennisapp;

import pl.michal.wrona.tennisapp.frames.LoginWindow;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.repository.*;
import pl.michal.wrona.tennisapp.service.CourtService;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.service.ReservationService;
import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        CourtsRepository courtsRepository = new CourtsFileRepository();
        UsersRepository usersRepository = new UsersFileRepository();
        ReservationsRepository reservationsRepository = new ReservationsFileRepository();
        CourtService courtService = new CourtService(courtsRepository);
        UserService userService = new UserService(usersRepository);
        ReservationService reservationService = new ReservationService(userService, courtService, reservationsRepository);
        MainService mainService = new MainService(courtService, userService, reservationService);
        WindowUtils windowUtils = new WindowUtils();
        JFrame frame = new LoginWindow(windowUtils, mainService);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
