/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class CommitteeDetail {

    private int id;
    private int committee_id;
    private int teacher_id;
    private double score;
    private String comment;

    public CommitteeDetail() {
    }

    public CommitteeDetail(int id, int committee_id, int teacher_id, double score, String comment) {
        this.id = id;
        this.committee_id = committee_id;
        this.teacher_id = teacher_id;
        this.score = score;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(int committee_id) {
        this.committee_id = committee_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
