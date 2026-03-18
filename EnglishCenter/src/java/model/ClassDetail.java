/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quan
 */
import java.sql.Date;

public class ClassDetail {

    private int classId;
    private String className;
    private String courseName;
    private String teacherName;
    private Date startDate;
    private Date endDate;
    private String status;

    public ClassDetail() {
    }

    public ClassDetail(int classId, String className, String courseName, String teacherName, Date startDate, Date endDate, String status) {
        this.classId = classId;
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters và Setters
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
