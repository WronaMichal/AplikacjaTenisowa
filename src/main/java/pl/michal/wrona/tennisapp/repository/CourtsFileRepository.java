package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourtsFileRepository implements CourtsRepository {

    @Override
    public void save(Court court) {
        //TODO jak zapisywać nowe korty przez metodę save do pliku txt
        //TODO usuwanie kortu z pliku txt -> metodę jak usuwasz kort to pamietaj, ze moga byc na nim rezerwacje! Co
        // z nimi zrobić
        //TODO znajdź sobie kort po ID czyli wczytujesz jeden tylko kort czyli jak użytkownik szuka konkretnego kortu
        // żeby jeden kort był wczytay do pamieci Javy
    }

    @Override
    public List<Court> findAll() {

        List<Court> courtsList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
               String[] properties = nextLine.split(",");
               courtsList.add(new Court(
                       Integer.parseInt(properties[0]),
                       SurfaceCourt.valueOf(properties[1]),
                       Integer.parseInt(properties[2]),
                       Integer.parseInt(properties[3]),
                       Double.parseDouble(properties[4])
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
        return courtsList;
    }
}


