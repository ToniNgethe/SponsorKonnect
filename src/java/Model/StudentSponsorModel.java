/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author toni
 */
public class StudentSponsorModel {
    
    private String stud_id, stud_name;
    private String sponsor_name;
    private String social_worker, acc_name;
    private Date date;

    public StudentSponsorModel() {
    }

    public StudentSponsorModel(String stud_id, String stud_name, String sponsor_name, String social_worker, String acc_name) {
        this.stud_id = stud_id;
        this.stud_name = stud_name;
        this.sponsor_name = sponsor_name;
        this.social_worker = social_worker;
        this.acc_name = acc_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        this.stud_name = stud_name;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getSocial_worker() {
        return social_worker;
    }

    public void setSocial_worker(String social_worker) {
        this.social_worker = social_worker;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }
    
    
    
}
