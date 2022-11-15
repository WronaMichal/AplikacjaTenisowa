package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private JButton returnButton;

    public ErrorWindow(WindowUtils windowUtils, String error) {
        label = new JLabel(error);
        panel = new JPanel();
        panel.add(label);
        label.setBounds(60, 110, 250, 25);
        Font myFont1 = new Font("Arial", Font.BOLD, 14);
        label.setFont(myFont1);
        panel.setLayout(null);
        int width = 350;
        int height = 350;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        add(panel);
        returnButton = new JButton("Cofnij");
        returnButton.setBounds(60, 170, 200, 25);
        returnButton.addActionListener(
                e -> {
                    dispose();
                }
        );
        panel.add(returnButton);

    }
}
