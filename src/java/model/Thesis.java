/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Thesis {

    private int thesisId;
    private String title;
    private String description;
    private int studentId;
    private int teacherId;
    private String fileName; // Tên file
    private String filePath; // 
    private Timestamp register_date;
    private int status_id;
    private double score;

    // Constructor
    public Thesis() {
    }

    public Thesis(int thesisId, String title, String description, int studentId, int teacherId, String fileName, String filePath, Timestamp register_date, int status_id, double score) {
        this.thesisId = thesisId;
        this.title = title;
        this.description = description;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.register_date = register_date;
        this.status_id = status_id;
        this.score = score;
    }

    public int getThesisId() {
        return thesisId;
    }

    public void setThesisId(int thesisId) {
        this.thesisId = thesisId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Timestamp getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Timestamp register_date) {
        this.register_date = register_date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
