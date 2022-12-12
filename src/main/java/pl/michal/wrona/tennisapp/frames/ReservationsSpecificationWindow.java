package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ReservationsSpecificationWindow extends JFrame {
    private Date date = Date.from(Instant.ofEpochMilli(System.currentTimeMillis()));
    public static String courtSurface;
    public static String startTime;
    public static String endTime;
    private JPanel offerSpecificationPanel;
    private JButton searchButton;
    private JButton mainPanelButton;
    private JSpinner dateSpinner;
    private JComboBox startTimeComboBox;
    private JComboBox endTimeComboBox;
    private JLabel dateLabel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private JLabel courtLabel;
    private JLabel errorLabel;
    private JComboBox<String> courtComboBox;

    public ReservationsSpecificationWindow(WindowUtils windowUtils, MainService mainService) {
        offerSpecificationPanel = new JPanel();
        dateLabel = new JLabel("Data rezerwacji");
        startTimeLabel = new JLabel("Godzina rozpoczęcia");
        endTimeLabel = new JLabel("Godzina zakończenia");
        courtLabel = new JLabel("Typ nawierzchni");
        errorLabel = new JLabel("<html>Data zakończenia musi być<br/>późniejsza niż data rozpoczęcia</html>");

        dateLabel.setBounds(20, 10, 100, 25);
        startTimeLabel.setBounds(20, 40, 130, 25);
        endTimeLabel.setBounds(20, 70, 130, 25);
        courtLabel.setBounds(300, 110, 130, 25);
        errorLabel.setBounds(280, 35, 200, 50);
        errorLabel.setVisible(false);


        courtComboBox = new JComboBox<>();
        courtComboBox.addItem(" ");
        for (SurfaceCourt s : SurfaceCourt.values()) {
            courtComboBox.addItem(s.toString());
        }
        SpinnerDateModel dateSpinnerModel = new SpinnerDateModel(date,
                Date.from(Instant.ofEpochSecond((1623669049L))),
                Date.from(Instant.ofEpochSecond(1704049027)),
                Calendar.DAY_OF_YEAR);
        dateSpinner = new JSpinner(dateSpinnerModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateEditor.getTextField().setEditable(false);
        dateSpinner.setEditor(dateEditor);
        startTimeComboBox = new JComboBox();
        endTimeComboBox = new JComboBox();
        startTimeComboBox.addItem(" ");
        endTimeComboBox.addItem(" ");
        int minOpeningHour = mainService.getMinOpeningHour();
        int maxClosingHours = mainService.getMaxClosingHour();
        // TODO zrób to w jedne pętli -> zrobione
        for (int i = minOpeningHour; i <= maxClosingHours; i++) {
            String hour;
            if (i < 10) {
                hour = String.format("0" + i + ":00");
            } else {
                hour = String.format(i + ":00");
            }
            startTimeComboBox.addItem(hour);
            endTimeComboBox.addItem(hour);
        }
        endTimeComboBox.removeItemAt(1);
        startTimeComboBox.removeItemAt(startTimeComboBox.getItemCount()-1);


        courtComboBox.addActionListener(
                e -> {
                    if (courtComboBox.getSelectedIndex() > 0) {
                        courtSurface = Objects.requireNonNull(courtComboBox.getSelectedItem()).toString();
                        setButtonEnabled();
                    }


                }
        );
        dateSpinner.setBounds(150, 10, 100, 25);
        startTimeComboBox.setBounds(150, 40, 100, 25);
        endTimeComboBox.setBounds(150, 70, 100, 25);
        courtComboBox.setBounds(300, 140, 100, 25);

        startTimeComboBox.addActionListener(
                e -> {
                    if (startTimeComboBox.getSelectedIndex() > 0) {
                        startTime = startTimeComboBox.getSelectedItem().toString();
                        setButtonEnabled();
                    }

                }
        );
        endTimeComboBox.addActionListener(
                e -> {
                    if (endTimeComboBox.getSelectedIndex() > 0) {
                        endTime = endTimeComboBox.getSelectedItem().toString();
                        setButtonEnabled();
                    }
                }
        );


        // TODO ustaw domyslna wartosc dla endTime lub startTime lub dodać wartość pustą i do comboxa dla godzin i uzytkownik musi cos wybrac aby przycisk wyszukaj byl dostepny
        // TODO COmbox nawierzchni na prawo i byl wiekszy przycisk "wyszkuj" -> zrobione
        dateSpinnerModel.addChangeListener(
                e -> date = dateSpinnerModel.getDate()
        );
        searchButton = new JButton("Wyszukaj");
        searchButton.setBounds(140, 220, 150, 50);
        searchButton.setEnabled(false);
        searchButton.addActionListener(
                e -> {
                    if (Integer.parseInt(String.valueOf(startTime.substring(0, 2))) >= Integer.parseInt(String.valueOf(endTime.substring(0, 2)))) {
                        errorLabel.setVisible(true);
                        errorLabel.setForeground(Color.red);

                    } else {
                        LocalDateTime dateLocal = Instant.ofEpochMilli(date.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                        LocalDateTime startTimeLocal = dateLocal.toLocalDate().atTime(LocalTime.parse(startTime));
                        LocalDateTime endTimeLocal = dateLocal.toLocalDate().atTime(LocalTime.parse(endTime));

                        JFrame availableSpots = new AvailableSpots(windowUtils, mainService, startTimeLocal, endTimeLocal, courtSurface);
                        availableSpots.setVisible(true);
                        dispose();
                    }
                }


        );
        mainPanelButton = new JButton("Główne Menu");
        mainPanelButton.setBounds(160, 320, 120, 40);
        mainPanelButton.addActionListener(
                e -> {
                    dispose();
                    JFrame mainPanelFrame = new MainPanelWindow(windowUtils, mainService);
                    mainPanelFrame.setVisible(true);
                    mainPanelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
        );


        offerSpecificationPanel.add(dateLabel);
        offerSpecificationPanel.add(dateSpinner);
        offerSpecificationPanel.add(startTimeComboBox);
        offerSpecificationPanel.add(endTimeComboBox);
        offerSpecificationPanel.add(startTimeLabel);
        offerSpecificationPanel.add(endTimeLabel);
        offerSpecificationPanel.add(courtLabel);
        offerSpecificationPanel.add(errorLabel);
        offerSpecificationPanel.add(courtComboBox);
        offerSpecificationPanel.add(searchButton);
        offerSpecificationPanel.add(mainPanelButton);
        offerSpecificationPanel.setLayout(null);

        add(offerSpecificationPanel);
        setBounds(500, 200, 500, 500);
        setTitle("System rezerwacji");
    }

    public void setButtonEnabled() {
        searchButton.setEnabled(startTime != null && endTime != null && courtSurface != null);
    }
}
