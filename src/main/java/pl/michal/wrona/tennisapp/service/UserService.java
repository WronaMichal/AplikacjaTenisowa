package pl.michal.wrona.tennisapp.service;

import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.repository.CourtsRepository;
import pl.michal.wrona.tennisapp.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserService {
    private User activeUser;
    private List<User> userList;
    private UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        userList = usersRepository.findAll();
    }

    public User login(String userName, String password) {
        Optional<User> optionalUser = userList.stream()
                .filter(user -> (user.getEmailAddress().equals(userName) || user.getPhoneNumber().equals(userName))
                        && user.getPassword().equals(password)
                ).findFirst();
        if (optionalUser.isPresent()) {
            activeUser = optionalUser.get();
            return activeUser;
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
            usersRepository.save(newUser);
            return newUser;
        }

    }

    public User getActiveUser() {
        return activeUser;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        Optional<User> optionalUser = userList.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public List <User> getUsersList(){
        return userList;
    }

    public Map <String,User> getUserMap(){
        return userList.stream()
        .collect(Collectors.toMap(User::getPhoneNumber,Function.identity()));
    }
}
