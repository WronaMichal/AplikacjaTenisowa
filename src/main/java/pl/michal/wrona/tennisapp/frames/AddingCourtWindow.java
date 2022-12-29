package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AddingCourtWindow extends JFrame {
    private WindowUtils windowUtils;
    private MainService mainService;
    public static String courtSurface;
    private JPanel addingCourtPanel;
    private JButton addingButton;
    private JButton mainPanelButton;
    private JButton addingCourt;
    private JLabel courtLabel;
    private JLabel openingHourLabel;
    private JLabel closingHourLabel;
    private JLabel pricePerHourLabel;
    private JLabel errorLabel;
    private JTextField openingHourField;
    private JTextField closingHourField;
    private JTextField pricePerHourField;
    private JComboBox<String> courtComboBox;

    public AddingCourtWindow(WindowUtils windowUtils, MainService mainService) {
        this.windowUtils = windowUtils;
        this.mainService = mainService;

        addingCourtPanel = new JPanel();
        this.setTitle("Dodwanie kortu");
        addingCourtPanel.setLayout(null);


        courtLabel = new JLabel("Typ nawierzchni");
        courtLabel.setBounds(25, 175, 200, 25);

        openingHourLabel = new JLabel("Godzina otwarcia");
        openingHourLabel.setBounds(25, 50, 200, 25);

        openingHourField = new JTextField();
        openingHourField.setBounds(25, 75, 130, 25);

        closingHourLabel = new JLabel("Godzina zamknięcia");
        closingHourLabel.setBounds(25, 100, 200, 25);

        closingHourField = new JTextField();
        closingHourField.setBounds(25, 125, 130, 25);

        pricePerHourLabel = new JLabel("Cena za godzinę");
        pricePerHourLabel.setBounds(25, 150, 200, 25);

        pricePerHourField = new JTextField();
        pricePerHourField.setBounds(25, 175, 130, 25);

        courtComboBox = new JComboBox<>();
        courtComboBox.addItem(" ");
        for (SurfaceCourt s : SurfaceCourt.values()) {
            courtComboBox.addItem(s.toString());
        }

        courtComboBox.setBounds(25, 220, 100, 25);


        addingButton = new JButton("Dodaj kort");
        addingButton.setBounds(110, 290, 200, 25);
        addingButton.addActionListener(e -> {
            addingCourt();
        });

        errorLabel = new JLabel();
        errorLabel.setBounds(100, 180, 200, 25);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.red);

        courtComboBox.addActionListener(e -> {
            if (courtComboBox.getSelectedIndex() > 0) {
                courtSurface = Objects.requireNonNull(courtComboBox.getSelectedItem()).toString();
            }


        });

        addingCourtPanel.add(courtLabel);
        addingCourtPanel.add(courtComboBox);
        addingCourtPanel.add(courtComboBox);
        addingCourtPanel.add(openingHourLabel);
        addingCourtPanel.add(openingHourField);
        addingCourtPanel.add(closingHourLabel);
        addingCourtPanel.add(closingHourField);
        addingCourtPanel.add(pricePerHourLabel);
        addingCourtPanel.add(pricePerHourField);
        addingCourtPanel.add(addingButton);
        addingCourtPanel.add(errorLabel);

        int width = 450;
        int height = 450;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        add(addingCourtPanel);


    }

    private void addingCourt() {
        Boolean isValid = true;
        String error = null;
        int openingHour = Integer.parseInt(openingHourField.getText());
        int closingHour = Integer.parseInt(closingHourField.getText());
        int pricePerHour = Integer.parseInt(pricePerHourField.getText());

        if (openingHour < 7) {
            isValid = false;
            error = "Godzina otwarcia nie może być wcześniejsza niż 8";
        }
        if (closingHour > 23) {
            isValid = false;
            error = "Godzina zamknięcia nie może być późniejsza niż 23";
        }

        if (openingHour >= closingHour) {
            isValid = false;
            error = "Godzina zamknięcia nie może być wcześniejsza niż godzina otwarcia";
        }

        if (pricePerHour < 40 && pricePerHour > 200) {
            isValid = false;
            error = "Cena kortu musi zawierać się w przedziale 40-200";
        }

        if (isValid) {
            mainService.saveCourt(courtSurface, openingHour, closingHour, pricePerHour);
            dispose();
            JFrame mainPanelFrame = new MainPanelWindow(windowUtils, mainService);
            mainPanelFrame.setVisible(true);
            mainPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        } else {
            JFrame errorFrame = new ErrorWindow(windowUtils, error);
            errorFrame.setVisible(true);


        }
    }
}
