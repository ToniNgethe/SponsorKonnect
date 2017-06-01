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
public class SocialStudentsModel {
    private String stud_id;
    private String s_name;
    private String f_name;
    private String l_name;
    private String number;
    private String location;
    private String age;
    private String gender;
     private String button = "<a id='button' class='btn-floating btn-large waves-effect waves-light teal modal-trigger' data-target='modal1' ><i class='material-icons'>description</i></a>";
     public void setButton() {
        this.button = button;
    }

    
    public String getButton() {
        return button;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public SocialStudentsModel() {
    }

    public SocialStudentsModel(String stud_id, String s_name, String f_name, String l_name, String number, String location, String age, String gender) {
        this.stud_id = stud_id;
        this.s_name = s_name;
        this.f_name = f_name;
        this.l_name = l_name;
        this.number = number;
        this.location = location;
        this.age = age;
        this.gender = gender;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
   
    
}
