package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ReservationsSpecification extends JFrame {
    private Date date = Date.from(Instant.ofEpochMilli(System.currentTimeMillis()));
    private String genre;
    private JPanel offerSpecificationPanel;
    private JButton searchButton;
    private JSpinner dateSpinner;
    private JLabel dateLabel;
    private JLabel movieLabel;
    private JLabel genreLabel;
    private JComboBox<String> genreComboBox;

    public ReservationsSpecification (WindowUtils windowUtils, User user) {
        offerSpecificationPanel = new JPanel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        searchButton = new JButton("Wyszukaj");

        dateLabel = new JLabel("Data seansu:");
        genreLabel = new JLabel("Kategoria:");
        movieLabel = new JLabel("Film:");

        dateLabel.setBounds(20, 10, 100, 25);
        genreLabel.setBounds(20, 50, 70, 25);
        movieLabel.setBounds(140, 50, 70, 25);

        genreComboBox = new JComboBox<>();
        genreComboBox.addItem(" ");
        genreComboBox.addItem(SurfaceCourt.GRASS.toString());
        genreComboBox.addItem(SurfaceCourt.CLAY.toString());
        genreComboBox.addItem(SurfaceCourt.HARD.toString());
////        MovieService.genres.forEach(genre ->
////                genreComboBox.addItem(genre)
//                // pobieranie listych mozliwych podlozy kortów (lista wysuwana - combobox)
//        );

        SpinnerDateModel dateSpinnerModel = new SpinnerDateModel(date,
                Date.from(Instant.ofEpochSecond((1623669049L))),
                Date.from(Instant.ofEpochSecond(1704049027)),
                Calendar.DAY_OF_YEAR);
        dateSpinner = new JSpinner(dateSpinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateEditor.getTextField().setEditable(false);
        dateSpinner.setEditor(dateEditor);

        dateSpinner.setBounds(130, 10, 100, 25);
        genreComboBox.setBounds(20, 90, 100, 25);

        searchButton.setBounds(80, 130, 100, 25);

        genreComboBox.addActionListener(
                e -> {
                    if(genreComboBox.getSelectedIndex() > 0) {
                        genre = genreComboBox.getSelectedItem().toString();
                        // genre zmienic na nazwe podłoża (element z listy)
                    }
                }
        );

        dateSpinnerModel.addChangeListener(
                e -> date = dateSpinnerModel.getDate()
        );

        offerSpecificationPanel.add(dateLabel);
        offerSpecificationPanel.add(dateSpinner);
        offerSpecificationPanel.add(movieLabel);
        offerSpecificationPanel.add(genreLabel);
        offerSpecificationPanel.add(genreComboBox);
        offerSpecificationPanel.add(searchButton);

        offerSpecificationPanel.setLayout(null);

        searchButton.addActionListener(
                e -> {
                    LocalDateTime localDate = Instant.ofEpochMilli(date.getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
//                    JFrame offerListFrame = new OffersListFrame(mainService, localDate, genre, movie);
//                    offerListFrame.setVisible(true);
//                    dispose();
                }
        );

        add(offerSpecificationPanel);
        setBounds(500, 200, 260, 230);
        setTitle("Specyfikacja");
    }
}
