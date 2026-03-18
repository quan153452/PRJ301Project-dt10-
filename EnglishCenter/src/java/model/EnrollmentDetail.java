/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quan
 */
import java.sql.Timestamp;

public class EnrollmentDetail {

    private int enrollId;
    private String studentName;
    private String className;
    private String courseName;
    private Timestamp enrollDate;
    private boolean paymentStatus;

    public EnrollmentDetail() {
    }

    public EnrollmentDetail(int enrollId, String studentName, String className, String courseName, Timestamp enrollDate, boolean paymentStatus) {
        this.enrollId = enrollId;
        this.studentName = studentName;
        this.className = className;
        this.courseName = courseName;
        this.enrollDate = enrollDate;
        this.paymentStatus = paymentStatus;
    }

    public int getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Timestamp enrollDate) {
        this.enrollDate = enrollDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
