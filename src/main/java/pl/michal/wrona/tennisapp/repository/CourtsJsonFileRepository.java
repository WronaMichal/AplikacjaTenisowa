package pl.michal.wrona.tennisapp.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.SimpleType;
import pl.michal.wrona.tennisapp.model.Court;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class CourtsJsonFileRepository implements CourtsRepository {
    private ObjectMapper objectMapper;

    public CourtsJsonFileRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(Court court) {

        String courtJson = null;
        List<Court> courtsList =findAll();
        courtsList.add(court);
        try {
            courtJson = objectMapper.writeValueAsString(courtsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.json", false));
            bw.write(courtJson);
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
        BufferedReader br = null;
        Optional<Court> courtFound = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.txt"));

            findAll().stream()
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
            br = new BufferedReader(new FileReader("C:\\Users\\Michał\\Desktop\\AplikacjaTenisowa\\src\\main\\resources\\courts.json"));
            TypeReference<List<Court>> mapType = new TypeReference<>() {
            };
            return objectMapper.readValue(br, mapType);

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


