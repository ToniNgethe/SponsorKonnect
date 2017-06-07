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
public class AssignedStudentModel {
    
    private String f_name;
    private String stud_id;
    private String l_name;
    private String s_name;
    private String school;
    private String level;

    public AssignedStudentModel(String f_name, String stud_id, String l_name, String s_name,String school, String level) {
        this.f_name = f_name;
        this.stud_id = stud_id;
        this.l_name = l_name;
        this.s_name = s_name;
        this.school = school;
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    
    public AssignedStudentModel() {
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
    
    
}
