package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.service.UserService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterWindow extends JFrame {

    private WindowUtils windowUtils;
    private MainService mainService;
    private JPanel panel;
    private JLabel emailAddressLabel;
    private JLabel phoneNumberLabel;
    private JLabel passwordLabel;
    private JLabel passwordConfirmLabel;
    private JLabel errorLabel;
    private JTextField emailAddressField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JButton registerButton;
    private JButton returnButton;

    public RegisterWindow(WindowUtils windowUtils, MainService mainService) throws HeadlessException {
        this.windowUtils = windowUtils;
        this.mainService = mainService;
        this.setTitle("Zarejestruj się");
        panel = new JPanel();

        panel.setLayout(null);

        emailAddressLabel = new JLabel("Podaj adres mailowy");
        emailAddressLabel.setBounds(25, 20, 200, 25);

        emailAddressField = new JTextField();
        emailAddressField.setBounds(25, 55, 200, 25);

        phoneNumberLabel = new JLabel("Podaj numer telefonu");
        phoneNumberLabel.setBounds(25, 85, 200, 25);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(25, 115, 200, 25);

        passwordLabel = new JLabel("Podaj hasło");
        passwordLabel.setBounds(25, 145, 200, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(25, 175, 200, 25);

        passwordConfirmLabel = new JLabel("Powierdź hasło");
        passwordConfirmLabel.setBounds(25, 205, 200, 25);

        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setBounds(25, 235, 200, 25);

        errorLabel = new JLabel();
        errorLabel.setBounds(25, 265, 200, 25);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.red);

        registerButton = new JButton("Rejestracja");
        registerButton.setBounds(25, 290, 200, 25);
        registerButton.addActionListener(
                e -> {
                    register();
                }
        );

        returnButton = new JButton("Cofnij");
        returnButton.setBounds(25, 330, 200, 25);
        returnButton.addActionListener(
                e -> {
                    dispose();
                    JFrame loginFrame = new LoginWindow(windowUtils, mainService);
                    loginFrame.setVisible(true);
                    loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
        );
        panel.add(returnButton);
        panel.add(emailAddressLabel);
        panel.add(emailAddressField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(passwordConfirmLabel);
        panel.add(passwordConfirmField);
        panel.add(errorLabel);
        panel.add(registerButton);

        int width = 425;
        int height = 425;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        add(panel);
    }

    private void register() {
        String error = null;
        Boolean isValid = true;
        String emailAddress = emailAddressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String passwordConfirm = String.valueOf(passwordConfirmField.getPassword());


        if (!emailAddress.matches("^(.+)@(.+)$")) {
            isValid = false;
            error = "Adres mailowy jest nieprawidłowy";
            emailAddressLabel.setForeground(Color.red);
        }

        if (!phoneNumber.matches("^\\d{9}$")) {
            isValid = false;
            error = "Numer telefonu jest nieprawidłowy";
            phoneNumberLabel.setForeground(Color.red);
        }
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")) {
            isValid = false;
            error = "Hasło jest za słabe";
            passwordLabel.setForeground(Color.red);
            passwordConfirmLabel.setForeground(Color.red);
        }
        if (!password.equals(passwordConfirm)) {
            isValid = false;
            error = "Hasła nie są identyczne";
            passwordLabel.setForeground(Color.red);
            passwordConfirmLabel.setForeground(Color.red);
        }
        if (isValid) {
            User user = mainService.register(phoneNumber, password, emailAddress);
            if(user!=null){
                dispose();
                JFrame frame = new LoginWindow(windowUtils, mainService);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else{
                JFrame errorFrame = new ErrorWindow(windowUtils,"Użytkownik o podanym emailu lub telefonie już istnieje");
                errorFrame.setVisible(true);
            }

        } else{
            JFrame errorFrame = new ErrorWindow(windowUtils,error);
            errorFrame.setVisible(true);
        }
        System.out.println(emailAddress + " " + phoneNumber + " " + password);
    }
}
