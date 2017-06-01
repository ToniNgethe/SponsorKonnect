/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author toni
 */
public class StudentGurdianModel implements Serializable{
    
    private String stud_id;
    private String name;
    private String occupation;
    private String mobile;

    public StudentGurdianModel(String stud_id, String name, String occupation, String mobile) {
        this.stud_id = stud_id;
        this.name = name;
        this.occupation = occupation;
        this.mobile = mobile;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    
    
}
