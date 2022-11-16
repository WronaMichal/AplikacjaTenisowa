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
    private Date time = Date.from(Instant.ofEpochMilli(System.currentTimeMillis()));
    private String court;
    private JPanel offerSpecificationPanel;
    private JButton searchButton;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel courtLabel;
    private JComboBox<String> courtComboBox;

    public ReservationsSpecification (WindowUtils windowUtils, User user) {
        offerSpecificationPanel = new JPanel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        searchButton = new JButton("Wyszukaj");

        dateLabel = new JLabel("Data rezerwacji");
        timeLabel = new JLabel("Godzina rezerwacji");
        courtLabel = new JLabel("Typ nawierzchni");

        dateLabel.setBounds(20, 10, 100, 25);
        timeLabel.setBounds(20, 40, 130, 25);
        courtLabel.setBounds(20, 80, 130, 25);


        courtComboBox = new JComboBox<>();
        courtComboBox.addItem(" ");
        courtComboBox.addItem(SurfaceCourt.GRASS.toString());
        courtComboBox.addItem(SurfaceCourt.CLAY.toString());
        courtComboBox.addItem(SurfaceCourt.HARD.toString());
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

        SpinnerDateModel timeSpinnerModel = new SpinnerDateModel();
        timeSpinnerModel.setCalendarField(Calendar.MINUTE);
        timeSpinner= new JSpinner(timeSpinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm");
        timeEditor.getTextField().setEditable(false);
        timeSpinner.setEditor(timeEditor);

        dateSpinner.setBounds(150, 10, 100, 25);
        timeSpinner.setBounds(150, 40, 100, 25);
        courtComboBox.setBounds(20, 110, 100, 25);

        searchButton.setBounds(100, 150, 100, 25);

        courtComboBox.addActionListener(
                e -> {
                    if( courtComboBox.getSelectedIndex() > 0) {
                        court =  courtComboBox.getSelectedItem().toString();
                        // genre zmienic na nazwe podłoża (element z listy)
                    }
                }
        );

        dateSpinnerModel.addChangeListener(
                e -> date = dateSpinnerModel.getDate()
        );

        dateSpinnerModel.addChangeListener(
                e -> time = dateSpinnerModel.getDate()
        );

        offerSpecificationPanel.add(dateLabel);
        offerSpecificationPanel.add(dateSpinner);
        offerSpecificationPanel.add(timeSpinner);
        offerSpecificationPanel.add(timeLabel);
        offerSpecificationPanel.add(courtLabel);
        offerSpecificationPanel.add(courtComboBox);
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
        setBounds(500, 200, 300, 300);
        setTitle("System rezerwacji");
    }
}
