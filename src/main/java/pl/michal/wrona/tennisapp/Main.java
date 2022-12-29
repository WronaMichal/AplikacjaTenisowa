package pl.michal.wrona.tennisapp;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        CourtsRepository courtsRepository = new CourtsJsonFileRepository(objectMapper);
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

//        Optional <User> user1 = usersRepository.findByPhoneNumber("111111");
//        System.out.println(user1.get());
//        User user2 = usersRepository.getByPhoneNumber("111111");
//        System.out.println(user2.getPassword());

    }
}
