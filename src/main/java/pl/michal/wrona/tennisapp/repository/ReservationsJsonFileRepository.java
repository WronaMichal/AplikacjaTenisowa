package pl.michal.wrona.tennisapp.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



public class ReservationsJsonFileRepository implements ReservationsRepository  {
    private ObjectMapper objectMapper;
    private List<User> usersList;
    private Map<String,User> userMap;
    private List<Court> courtsList;

    public ReservationsJsonFileRepository(ObjectMapper objectMapper, CourtsRepository courtsRepository, UsersRepository usersRepository) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());

    }


    @Override
    public void save(Reservation reservation) {
        String reservationJson = null;
        List<Reservation> reservationsList = findAll(courtsList,userMap);
        reservationsList.add(reservation);
        try {
            reservationJson = objectMapper.writeValueAsString(reservationsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.json", false));
            bw.write(reservationJson);
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
    public List<Reservation> findAll(List<Court> courtList, Map<String, User> userMap)  {
        List<Reservation> reservationList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\reservations.json"));
            TypeReference<List<Reservation>> mapType = new TypeReference<>() {

            };
            reservationList = objectMapper.readValue(br, mapType);
            reservationList.forEach(r->{
                r.setUser(userMap.get(r.getUserPhoneNumber()));
                r.setCourt(courtList.stream()
                        .filter(c->c.getId()==(r.getCourtId()))
                        .findFirst().get());
            });
//            reservationList.forEach(r->{
//                r.setUser(userList.stream()
//                        .filter(u->u.getPhoneNumber().equals(r.getUserPhoneNumber()))
//                        .findFirst().get());
//            });
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
