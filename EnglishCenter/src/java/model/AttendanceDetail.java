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

public class AttendanceDetail {

    private int slotID;
    private Date slotDate;
    private String slotTime;
    private String topic;
    private int isPresent; // 1: Có mặt, 0: Vắng, -1: Chưa học (Future)
    private String note;

    public AttendanceDetail() {
    }

    public AttendanceDetail(int slotID, Date slotDate, String slotTime, String topic, int isPresent, String note) {
        this.slotID = slotID;
        this.slotDate = slotDate;
        this.slotTime = slotTime;
        this.topic = topic;
        this.isPresent = isPresent;
        this.note = note;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
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
