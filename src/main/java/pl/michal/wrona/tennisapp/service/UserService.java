package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private List<User> userList = new ArrayList<>();


    public UserService() {
        userList.add(new User("11111", "aa22", "wro1@gmai.com", false));
        userList.add(new User("22222", "aa22", "wro2@gmai.com", false));
        userList.add(new User("33333", "aa22", "wro3@gmai.com", false));
        userList.add(new User("44444", "aa22", "wro4@gmai.com", true));
    }

    public User login(String userName, String password) {
        Optional<User> optionalUser = userList.stream().filter(user -> (user.getEmailAddress().equals(userName) || user.getPhoneNumber().equals(userName))
                && user.getPassword().equals(password)
        ).findFirst();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public User register(String phoneNumber, String password, String emailAddress) {
        List<User> users = userList.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber) || user.getEmailAddress().equals(emailAddress))
                .collect(Collectors.toList());
        if (!users.isEmpty()) {
            return null;
        } else {
            User newUser = new User(phoneNumber, password, emailAddress, false);
            userList.add(newUser);
            return newUser;
        }

    }


}
