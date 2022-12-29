package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersFileRepository implements UsersRepository {
    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\users.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                usersList.add(User.fromString(properties));
                nextLine = br.readLine();
            }

            // TODO popraw metody fromString na static tak jak w przykładzie fromString UserList -> tworzenia obiektów ze
            // TODO statycznych metod
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
        return usersList;
    }

    @Override
    public void save(User user) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\users.txt", true));
            bw.write(System.lineSeparator() + user.toString()
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
            }
        }
    }


    @Override
    public Optional <User> findByPhoneNumber(String phoneNumber) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\users.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                if (properties[0].equals(phoneNumber)) {
                    return Optional.of(User.fromString(properties));
                }
                nextLine = br.readLine();

            }

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
        return Optional.empty();

    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\users.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                if (properties[0].equals(phoneNumber)) {
                    return User.fromString(properties);
                }
                nextLine = br.readLine();

            }

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
        return null;

    }

}
