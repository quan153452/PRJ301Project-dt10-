/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author deadg
 */
public class FeedbackDetail {

    private int feedbackId;
    private String studentName;
    private String className;
    private int rating; // Điểm số từ 1 đến 5 (nếu có)
    private String comment;

    public FeedbackDetail() {
    }

    public FeedbackDetail(int feedbackId, String studentName, String className, int rating, String comment) {
        this.feedbackId = feedbackId;
        this.studentName = studentName;
        this.className = className;
        this.rating = rating;
        this.comment = comment;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
