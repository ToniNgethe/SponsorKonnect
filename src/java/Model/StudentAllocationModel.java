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
public class StudentAllocationModel {
    
    private String studId;
    private double school;
    private double upkeep;
    private double others;
    private Date date;

    public StudentAllocationModel() {
    }
    
    public StudentAllocationModel(String studId, double school, double upkeep, double others, Date date) {
        this.studId = studId;
        this.school = school;
        this.upkeep = upkeep;
        this.others = others;
        this.date = date;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public double getSchool() {
        return school;
    }

    public void setSchool(double school) {
        this.school = school;
    }

    public double getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(double upkeep) {
        this.upkeep = upkeep;
    }

    public double getOthers() {
        return others;
    }

    public void setOthers(double others) {
        this.others = others;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
