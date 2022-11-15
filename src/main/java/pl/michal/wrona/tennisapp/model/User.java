package pl.michal.wrona.tennisapp.model;

public class User {
    private String phoneNumber;
    private String password;
    private String emailAddress;
    private boolean isAdmin;

    public User(String phoneNumber, String password, String emailAddress, boolean isAdmin) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAddress = emailAddress;
        this.isAdmin = isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
