/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author toni
 */
public class AccSponsor {
    
    private String sponsor_id;
    private String name;
    private double commits;
    private double payments;
    private double bal;
    private double use;

    public AccSponsor(String sponsor_id, String name, double commits, double payments, double bal, double use) {
        this.sponsor_id = sponsor_id;
        this.name = name;
        this.commits = commits;
        this.payments = payments;
        this.bal = bal;
        this.use = use;
    }

    public AccSponsor() {
    }

    public double getUse() {
        return use;
    }

    public void setUse(double use) {
        this.use = use;
    }

    
    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommits() {
        return commits;
    }

    public void setCommits(double commits) {
        this.commits = commits;
    }

    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }
    
    
}
