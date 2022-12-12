package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;

public class MainPanelWindow extends JFrame {
    private MainService mainService;
    private JPanel panel;
    private JButton paymentButton;
    private JButton reservationsButton;
    private JButton profileButton;
    private JButton courtsButton;
    private JButton logOutButton;

    public MainPanelWindow (WindowUtils windowUtils, MainService mainService){
        this.mainService = mainService;
        panel = new JPanel();
        panel.setLayout(null);
        int width = 400;
        int height = 400;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        add(panel);
        paymentButton = new JButton("Płatność");
        paymentButton.setBounds(60, 170, 200, 25);
        paymentButton.addActionListener(
                e -> {
                    JFrame paymentFrame = new PaymentWindow(windowUtils, mainService);
                    paymentFrame.setVisible(true);
                    paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();

                }
        );

// TODO popracuj na wygladem okienka i wyświetlanie wszystkich rezerwacji -> podobne okienko jak do wyświetlania kortów -> zrobione
        courtsButton = new JButton("Zarezerwuj");
        courtsButton.setBounds(60, 220, 200, 25);
        courtsButton.addActionListener(
                e -> {
                    JFrame reservationsSpecificationsFrame = new ReservationsSpecificationWindow(windowUtils, mainService);
                    reservationsSpecificationsFrame.setVisible(true);
                    reservationsSpecificationsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
        );

        reservationsButton = new JButton("Rezerwacje");
        reservationsButton.setBounds(60, 270, 200, 25);
        reservationsButton.addActionListener(
                e -> {
                    JFrame ReservationsFrame = new ReservationsWindow(windowUtils, mainService);
                    ReservationsFrame.setVisible(true);
                    ReservationsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
        );


        panel.add(courtsButton);
        panel.add(paymentButton);
        panel.add(reservationsButton);
        setTitle("Główne Menu");

    }
}

