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
public class AccStudentAdd {
    
    private String stud_id;
    private String name;
    private double school;
    private double upkeep;
    private double other;

    public AccStudentAdd(String stud_id, String name, double school, double upkeep, double other) {
        this.stud_id = stud_id;
        this.name = name;
        this.school = school;
        this.upkeep = upkeep;
        this.other = other;
    }

    public AccStudentAdd() {
    }
    

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }
    
    
    
}
