package pl.michal.wrona.tennisapp;

import pl.michal.wrona.tennisapp.frames.LoginWindow;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.CourtService;
import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static CourtService courtService = new CourtService();
    public static void main(String[] args) {
//        List<Court> courts = new ArrayList<>();
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        courts.add(new Court("grass",17,18));
//        System.out.println();
//        User noAdmin = new User("123456", "aaa","avc@wp.pl", false);
//        User isAdmin = new User("1234526", "aaab","av12c@wp.pl", true);
//        courtService.addCourt("grass", 12, 13, noAdmin);

        UserService userService = new UserService();


        WindowUtils windowUtils = new WindowUtils();
        JFrame frame = new LoginWindow(windowUtils, userService);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
