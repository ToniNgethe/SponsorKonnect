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
public class StudentParentsModel implements Serializable{
    private String f_name;
    private String f_occup;
    private String f_mobile;
    private String m_name;
    private String m_occup;
    private String m_mobile;
    private String stud_id;

    public StudentParentsModel(String stud_id,String f_name, String f_occup, String f_mobile, String m_name, String m_occup, String m_mobile) {
        this.f_name = f_name;
        this.f_occup = f_occup;
        this.f_mobile = f_mobile;
        this.m_name = m_name;
        this.m_occup = m_occup;
        this.m_mobile = m_mobile;
        this.stud_id = stud_id;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }
    
    

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_occup() {
        return f_occup;
    }

    public void setF_occup(String f_occup) {
        this.f_occup = f_occup;
    }

    public String getF_mobile() {
        return f_mobile;
    }

    public void setF_mobile(String f_mobile) {
        this.f_mobile = f_mobile;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_occup() {
        return m_occup;
    }

    public void setM_occup(String m_occup) {
        this.m_occup = m_occup;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }
    
    
}
