package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationsFileRepository implements ReservationsRepository {

    @Override
    public void save(Reservation reservation) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.txt", true));
            bw.write(System.lineSeparator() +
                    reservation.getFrom().toString() + "," +
                    reservation.getTo().toString() + "," +
                    reservation.getUser().getPhoneNumber() + "," +
                    reservation.getCourt().getId()
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
    public List<Reservation> findAll(List<Court> courtList, List<User> userList) {
        List<Reservation> reservationList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                reservationList.add(new Reservation(LocalDateTime.parse(properties[0]),
                        LocalDateTime.parse(properties[1]),
                        userList.stream()
                                .filter(user -> user.getPhoneNumber().equals(properties[2]))
                                .findFirst().get(),
                        courtList.stream()
                                .filter(court -> court.getId() == Integer.parseInt(properties[3]))
                                .findFirst().get()));
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
        return reservationList;
    }


}
