/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Committee {

    private int committee_id;
    private String name;
    private int thesis_id;
    private int deparment_id;

    public Committee() {
    }

    public Committee(int committee_id, String name, int thesis_id, int deparment_id) {
        this.committee_id = committee_id;
        this.name = name;
        this.thesis_id = thesis_id;
        this.deparment_id = deparment_id;
    }

    public int getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(int committee_id) {
        this.committee_id = committee_id;
    }

    public int getThesis_id() {
        return thesis_id;
    }

    public void setThesis_id(int thesis_id) {
        this.thesis_id = thesis_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeparment_id() {
        return deparment_id;
    }

    public void setDeparment_id(int deparment_id) {
        this.deparment_id = deparment_id;
    }

}
