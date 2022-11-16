package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;

public class MainPanelWindow extends JFrame {
    private JPanel panel;
    private JButton paymentButton;
    private JButton reservationsButton;
    private JButton profileButton;
    private JButton courtsButton;
    private JButton logOutButton;

    public MainPanelWindow (WindowUtils windowUtils, User currentUser){
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

                }
        );
        panel.add(paymentButton);

        courtsButton = new JButton("Zarezerwuj");
        courtsButton.setBounds(60, 220, 200, 25);
        courtsButton.addActionListener(
                e -> {
                    JFrame reservationsSpecificationsFrame = new ReservationsSpecification(windowUtils, currentUser);
                    reservationsSpecificationsFrame.setVisible(true);
                    reservationsSpecificationsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
        );
        panel.add(courtsButton);

    }
}

