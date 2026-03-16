/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Student {
    private int maHS;           // Corresponding to StudentID
    private String hoTen;        // Corresponding to FullName
    private Date ngaySinh;       // Corresponding to BirthDate
    private String soDienThoai;  // Corresponding to Phone
    private String email;
    private int accountID;       // Foreign Key to Accounts table

    // Default Constructor
    public Student() {
    }

    // Full Constructor
    public Student(int maHS, String hoTen, Date ngaySinh, String soDienThoai, String email, int accountID) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.accountID = accountID;
    }

    // Getters and Setters
    public int getMaHS() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS = maHS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return "Student{" + "maHS=" + maHS + ", hoTen=" + hoTen + ", email=" + email + '}';
    }
}
