/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class Taskmaster {

    private int taskmaster_id;
    private int account_id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthdate;

    public Taskmaster() {
    }

    public Taskmaster(int taskmaster_id, int account_id, String name, String email, String phone, LocalDate birthdate) {
        this.taskmaster_id = taskmaster_id;
        this.account_id = account_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public int getTaskmaster_id() {
        return taskmaster_id;
    }

    public void setTaskmaster_id(int taskmaster_id) {
        this.taskmaster_id = taskmaster_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
