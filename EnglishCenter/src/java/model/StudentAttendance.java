/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quan
 */
public class StudentAttendance {

    private int studentId;
    private String studentName;
    private int attendanceId; // Sẽ = 0 nếu chưa từng điểm danh
    private int isPresent; // 1: Present, 0: Absent, -1: Chưa điểm danh
    private String note;

    public StudentAttendance() {
    }

    public StudentAttendance(int studentId, String studentName, int attendanceId, int isPresent, String note) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceId = attendanceId;
        this.isPresent = isPresent;
        this.note = note;
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

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
