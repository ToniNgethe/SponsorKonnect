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
public class ApplicantSponsors {
   
     private String sponsor_id="<a id='assign_sponsor_id' class='btn-floating btn-large waves-effect waves-light blue'><i class='material-icons'>assignment_ind</i></a>";
     private String name, number, email, means, type, company;
     private Date date;

    public ApplicantSponsors(String name, String number, String email, String means, String type, String company, Date date, String sponsor_id) {
        this.sponsor_id = sponsor_id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.means = means;
        this.type = type;
        this.company = company;
        this.date = date;
    }

    public ApplicantSponsors() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
   
    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id() {
        this.sponsor_id = sponsor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
     
}
