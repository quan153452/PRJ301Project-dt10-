package model;

public class Account {

    private int accountID;
    private String username;
    private String password;
    private String role;

    public Account() {}

    public Account(int accountID, String username, String password, String role) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}