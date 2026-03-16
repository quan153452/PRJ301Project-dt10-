package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;

public class AccountDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    // Login
    public Account login(String username, String password) {

        try {

            String sql = "SELECT * FROM Accounts WHERE Username=? AND Password=?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);

            rs = stm.executeQuery();

            if (rs.next()) {

                Account acc = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );

                return acc;
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }

    
    // Get account by ID
    public Account getAccountById(int id) {

        try {

            String sql = "SELECT * FROM Accounts WHERE AccountID=?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();

            if (rs.next()) {

                return new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
            }

        } catch (Exception e) {
            System.out.println("getAccountById: " + e.getMessage());
        }

        return null;
    }

    
    // Get all accounts
    public List<Account> getAccounts() {

        List<Account> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Accounts";

            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                Account acc = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );

                list.add(acc);
            }

        } catch (Exception e) {
            System.out.println("getAccounts: " + e.getMessage());
        }

        return list;
    }

    
    // Create account
    public boolean createAccount(Account acc) {

        try {

            String sql = "INSERT INTO Accounts(Username,Password,Role) VALUES(?,?,?)";

            stm = connection.prepareStatement(sql);

            stm.setString(1, acc.getUsername());
            stm.setString(2, acc.getPassword());
            stm.setString(3, acc.getRole());

            return stm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("createAccount: " + e.getMessage());
        }

        return false;
    }

    
    // Update account
    public boolean updateAccount(Account acc) {

        try {

            String sql = "UPDATE Accounts SET Username=?, Password=?, Role=? WHERE AccountID=?";

            stm = connection.prepareStatement(sql);

            stm.setString(1, acc.getUsername());
            stm.setString(2, acc.getPassword());
            stm.setString(3, acc.getRole());
            stm.setInt(4, acc.getAccountID());

            return stm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("updateAccount: " + e.getMessage());
        }

        return false;
    }

    
    // Delete account
    public boolean deleteAccount(int id) {

        try {

            String sql = "DELETE FROM Accounts WHERE AccountID=?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            return stm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("deleteAccount: " + e.getMessage());
        }

        return false;
    }

    
    // Search account
    public List<Account> searchAccount(String keyword) {

        List<Account> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Accounts WHERE Username LIKE ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");

            rs = stm.executeQuery();

            while (rs.next()) {

                Account acc = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );

                list.add(acc);
            }

        } catch (Exception e) {
            System.out.println("searchAccount: " + e.getMessage());
        }

        return list;
    }
}