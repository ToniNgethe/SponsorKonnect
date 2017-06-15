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
public class SponsorPaymentsModel {
    
    private String sponsor;
    private double amount;
    private String type;
    private String bank;
    private String slip;
    private Date date;

    public SponsorPaymentsModel(String sponsor, double amount, String type, String bank, String slip, Date date) {
        this.sponsor = sponsor;
        this.amount = amount;
        this.type = type;
        this.bank = bank;
        this.slip = slip;
        this.date = date;
    }

    public SponsorPaymentsModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSlip() {
        return slip;
    }

    public void setSlip(String slip) {
        this.slip = slip;
    }
    
    
    
}
