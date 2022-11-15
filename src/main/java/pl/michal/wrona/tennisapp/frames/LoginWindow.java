package pl.michal.wrona.tennisapp.frames;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class LoginWindow extends JFrame {

    private WindowUtils windowUtils;
    private JPanel panel;

    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private UserService userService;

    public LoginWindow(WindowUtils windowUtils, UserService userService) throws HeadlessException {
        this.windowUtils = windowUtils;
        this.userService = userService;
        this.setTitle("System rezerwacji Tenis.tenis");
        panel = new JPanel();

        panel.setLayout(null);

        userNameLabel = new JLabel("Podaj login");
        userNameLabel.setBounds(25, 20, 200, 25);

        userNameField = new JTextField();
        userNameField.setBounds(25, 55, 200, 25);

        passwordLabel = new JLabel("Podaj hasło");
        passwordLabel.setBounds(25, 90, 200, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(25, 125, 200, 25);



        loginButton = new JButton("Zaloguj");
        loginButton.setBounds(25, 160, 200, 25);
        loginButton.addActionListener(
                e -> login()
        );

        registerButton = new JButton("Rejestracja");
        registerButton.setBounds(25, 195, 200, 25);
        registerButton.addActionListener(
                e -> {
                    JFrame frame = new RegisterWindow(windowUtils, userService);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
        );
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(loginButton);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);

        userNameField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        textFieldChange();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        textFieldChange();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        textFieldChange();
                    }
                }

        );
        int width = 350;
        int height = 350;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        add(panel);
    }

    private void textFieldChange() {
        loginButton.setEnabled(!userNameField.getText().isBlank());
    }

    private void login(){
        String username = userNameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        System.out.println(username + password);
        userService.login(username, password);
    }
}
