package pl.michal.wrona.tennisapp.frames;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.MainService;
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
    private MainService mainService;

    public LoginWindow(WindowUtils windowUtils, MainService mainService) throws HeadlessException {
        this.windowUtils = windowUtils;
        this.mainService = mainService;
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
                    JFrame frame = new RegisterWindow(windowUtils, mainService);
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
        User user = mainService.login(username, password);
        if(user==null){
            JFrame errorFrame = new ErrorWindow(windowUtils, "Błędne dane podczas logowania");
            errorFrame.setVisible(true);
        }
        else{
            JFrame mainPanelWindowFrame = new MainPanelWindow(windowUtils, mainService);
            dispose();
            mainPanelWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainPanelWindowFrame.setVisible(true);
        }

    }
}
