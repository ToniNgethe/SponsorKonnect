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
public class SponsorCommitsModel {
    
    private String sponsor;
    private double amount;
    private Date date;

    public SponsorCommitsModel() {
    }

    
    public SponsorCommitsModel(String sponsor, double amount, Date date) {
        this.sponsor = sponsor;
        this.amount = amount;
        this.date = date;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
