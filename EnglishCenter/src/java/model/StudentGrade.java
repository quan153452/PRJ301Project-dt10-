/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quan
 */
public class StudentGrade {

    private int studentId;
    private String studentName;
    private Double finalGrade; // Dùng Double (chữ D viết hoa) để có thể nhận giá trị null

    public StudentGrade() {
    }

    public StudentGrade(int studentId, String studentName, Double finalGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.finalGrade = finalGrade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
