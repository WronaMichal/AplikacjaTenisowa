package pl.michal.wrona.tennisapp;

import pl.michal.wrona.tennisapp.frames.LoginWindow;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.CourtService;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.service.ReservationService;
import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CourtService courtService = new CourtService();
        UserService userService = new UserService();
        ReservationService reservationService = new ReservationService(userService, courtService);
        MainService mainService = new MainService(courtService, userService, reservationService);
        WindowUtils windowUtils = new WindowUtils();
        JFrame frame = new LoginWindow(windowUtils, mainService);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
