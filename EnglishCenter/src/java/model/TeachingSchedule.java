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

public class TeachingSchedule {
    private int slotID;
    private int classID;
    private String className;
    private Date slotDate;
    private String slotTime;
    private String roomName;

    public TeachingSchedule() {
    }

    public TeachingSchedule(int slotID, int classID, String className, Date slotDate, String slotTime, String roomName) {
        this.slotID = slotID;
        this.classID = classID;
        this.className = className;
        this.slotDate = slotDate;
        this.slotTime = slotTime;
        this.roomName = roomName;
    }

    public int getSlotID() { return slotID; }
    public void setSlotID(int slotID) { this.slotID = slotID; }
    public int getClassID() { return classID; }
    public void setClassID(int classID) { this.classID = classID; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public Date getSlotDate() { return slotDate; }
    public void setSlotDate(Date slotDate) { this.slotDate = slotDate; }
    public String getSlotTime() { return slotTime; }
    public void setSlotTime(String slotTime) { this.slotTime = slotTime; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
}