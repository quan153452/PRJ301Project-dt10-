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

public class TimetableSlot {

    private int slotId;
    private String className;
    private String roomName;
    private Date slotDate;
    private String slotTime;
    private String topic;

    public TimetableSlot() {
    }

    public TimetableSlot(int slotId, String className, String roomName, Date slotDate, String slotTime, String topic) {
        this.slotId = slotId;
        this.className = className;
        this.roomName = roomName;
        this.slotDate = slotDate;
        this.slotTime = slotTime;
        this.topic = topic;
    }

    // Getters và Setters
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
