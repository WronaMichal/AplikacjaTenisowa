package pl.michal.wrona.tennisapp.repository;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.SurfaceCourt;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourtsFileRepository implements CourtsRepository {

    @Override
    public void save(Court court) {

        // TODO Maven co to oraz format JSON wczytywania plików

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.txt", true));
            bw.write(System.lineSeparator() + court.toString()
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
    public Court findById(int courtId) {
        List<Court> courtsList = new ArrayList<>();
        BufferedReader br = null;
        Optional<Court> courtFound = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                //TODO fromString metoda zrób dla Court tak samo jak toString dla Court -> statyczna metode
                String[] properties = nextLine.split(",");
                Court court = new Court().fromString(properties);
                courtsList.add(court);
                nextLine = br.readLine();
            }
            courtFound = courtsList.stream()
                    .filter(cl -> cl.getId() == courtId)
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
        return courtFound.get();
    }


    @Override
    public List<Court> findAll() {

        List<Court> courtsList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.txt"));
            String nextLine = br.readLine();
            while (null != nextLine) {
                //TODO fromString metoda zrób dla Court tak samo jak toString dla Court -> statyczna metode
                String[] properties = nextLine.split(",");
                Court court = new Court().fromString(properties);
                courtsList.add(court);
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


