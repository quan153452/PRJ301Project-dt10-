package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quan 
 */
import java.sql.Date;

public class ScheduleDetail {

    private int classID;
    private Date slotDate;
    private String slotTime;
    private String className;
    private String roomName;
    private int isPresent; // Trạng thái điểm danh: 1 (Attended), 0 (Absent), -1 (Not yet)

    public ScheduleDetail() {
    }

    public ScheduleDetail(int classID, Date slotDate, String slotTime, String className, String roomName, int isPresent) {
        this.classID = classID;
        this.slotDate = slotDate;
        this.slotTime = slotTime;
        this.className = className;
        this.roomName = roomName;
        this.isPresent = isPresent;
    }

    // Getters và Setters
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }
}
