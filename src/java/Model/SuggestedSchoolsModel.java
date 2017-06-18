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
public class SuggestedSchoolsModel {
    private int stud_id;
    private String studName;
    private String name;
    private String level;
    private String education_level;
    private String mode;
    private String type;
    private String cl;
    private Date date;

    public SuggestedSchoolsModel(int stud_id, String name, String level, String education_level, String mode, String type, String cl, Date date) {
        this.stud_id = stud_id;
        this.name = name;
        this.level = level;
        this.education_level = education_level;
        this.mode = mode;
        this.type = type;
        this.cl = cl;
        this.date = date;
    }

    public SuggestedSchoolsModel() {
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }
    

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
