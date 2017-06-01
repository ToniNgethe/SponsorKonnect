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
public class StudentSchoolModel implements Serializable{
    private String stud_id;
    private String name;
    private String edu_level;
    private String mode;
    private String type;
    private String _clss;

    public StudentSchoolModel(String stud_id, String name, String edu_level, String mode, String type, String _clss) {
        this.stud_id = stud_id;
        this.name = name;
        this.edu_level = edu_level;
        this.mode = mode;
        this.type = type;
        this._clss = _clss;
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

    public String getEdu_level() {
        return edu_level;
    }

    public void setEdu_level(String edu_level) {
        this.edu_level = edu_level;
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

    public String getClss() {
        return _clss;
    }

    public void setClss(String _clss) {
        this._clss = _clss;
    }
    
    
}
