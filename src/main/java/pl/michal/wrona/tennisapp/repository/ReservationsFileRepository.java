package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReservationsFileRepository implements ReservationsRepository {

    @Override
    public void save(Reservation reservation) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.txt", true));
            bw.write( System.lineSeparator() + reservation.getId() + "," + reservation.toString()
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
    public Reservation findById(int reservationId, List<Court> courtList, List<User> userList) {
        List<Reservation> reservationList = new ArrayList<>();
        BufferedReader br = null;
        Optional<Reservation> reservationFound =null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                reservationList.add(new Reservation((Integer.parseInt(properties[0])),
                        LocalDateTime.parse(properties[1]),
                        LocalDateTime.parse(properties[2]),
                        userList.stream()
                                .filter(user -> user.getPhoneNumber().equals(properties[3]))
                                .findFirst().get(),
                        courtList.stream()
                                .filter(court -> court.getId() == Integer.parseInt(properties[4]))
                                .findFirst().get()));
                nextLine = br.readLine();

                //TODO dodaj JUnit i zrób testy funkcjonalnosci
                //TODO Pokrycie kodu przynajmniej 70% -> run with coverage
                //TODO sprawdz czy nowy plik sie wczytuje lub czy nowy kort sie dodaje
            }
            reservationFound = reservationList.stream()
                    .filter(rl-> rl.getId()==reservationId)
                    .findFirst();

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
        return reservationFound.get();
    }


    @Override
    public List<Reservation> findAll(List<Court> courtList, Map<String,User> userMap) {
        List<Reservation> reservationList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                String[] properties = nextLine.split(",");
                User user = userMap.get(properties[3]);
                Court court = courtList.stream()
                        .filter(c -> c.getId() == Integer.parseInt(properties[4]))
                        .findFirst().get();
                reservationList.add(new Reservation((Integer.parseInt(properties[0])),
                        LocalDateTime.parse(properties[1]),
                        LocalDateTime.parse(properties[2]),
                        user, court
                                ));

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
