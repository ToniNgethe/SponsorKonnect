/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toni
 */
public class StudentPersonalModel implements Serializable{
    
    private String stud_id;
    private String s_name;
    private String f_name;
    private String l_name;
    private String gender;
    private String number;
    private String location;
    private String status;
    private Date dob,added;
    private String age;

    public StudentPersonalModel() {
    }
 

    
    public StudentPersonalModel(String id,String s_name, String f_name, String l_name, String gender, String number, String location, String status, Date dob, String age) {
        this.s_name = s_name;
        this.f_name = f_name;
        this.l_name = l_name;
        this.gender = gender;
        this.number = number;
        this.location = location;
        this.status = status;
        this.dob = dob;
        this.age = age;
        this.stud_id = id;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    
    public String getStud_id() {
        return stud_id;
    }
    
    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }
    
    

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    
}
