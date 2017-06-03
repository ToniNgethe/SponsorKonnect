/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.SocialStudentsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toni
 */
public class SocialWorker {
    
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    
   public SocialWorker(){
       conn = DBUtils.DBUtil.getConnection();
   }
    
  public int checkSocialLogin(String email, String pass){
      int exists = 0;
      
      String query = "SELECT * FROM social_worker WHERE email = ? AND password = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);
            
            rs = pst
                    .executeQuery();
            
            if (rs.next()) {
                exists = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SocialWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      return exists;
  }
  
  public List<SocialStudentsModel> allStuds(String social){
      
      List<SocialStudentsModel> myList = new ArrayList<>();
      
    //  SELECT `id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added` FROM `student_personal` WHERE 1
    
      String query = "SELECT student FROM student_sponsor WHERE social = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, social);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Student : "+ rs.getString("student"));
                
                String stud = rs.getString("student");
                
                String query2 = "SELECT * FROM student_personal WHERE stud_id = ?";
                PreparedStatement p = conn.prepareStatement(query2);
                p.setString(1, stud);
                
                ResultSet r = p.executeQuery();
                
                while(r.next()){
                    SocialStudentsModel sd = new SocialStudentsModel();
                    sd.setStud_id(r.getString("stud_id"));
                    sd.setS_name(r.getString("s_name"));
                    sd.setF_name(r.getString("f_name"));
                    sd.setL_name(r.getString("l_name"));
                    sd.setGender(r.getString("gender"));
                    sd.setNumber(r.getString("number"));
                    sd.setLocation(r.getString("location"));
                    sd.setAge(r.getString("age"));
                    sd.setButton();
                   
                    myList.add(sd);
                    System.out.println(sd);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SocialWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      return myList;
  }
  
  //INSERT INTO `SocialVisits`(`id`, `stud_id`, `social_worker`, `background`, `composition`, `ethnicity`, `health`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])
  public boolean checkReport(String id){
      boolean exists = false;
      String query = "SELECT stud_id FROM SocialVisits WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SocialWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
  }
  
  public boolean fileReport(String stud_id, String social, String bg, String composition, String ethnicity, String health, String date){
      boolean isSuccess = false;
      
      String query = "INSERT INTO `SocialVisits`(`stud_id`, `social_worker`, `background`, `composition`, `ethnicity`, `health`, `date`)"
              + " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
      
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, stud_id);
            pst.setString(2, social);
            pst.setString(3, bg);
            pst.setString(4,composition);
            pst.setString(5, ethnicity);
            pst.setString(6, health);
            pst.setString(7, date);
            
            
            int a = pst.executeUpdate();
            
            if (a>0) {
                isSuccess = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SocialWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      return isSuccess;
  }
  
  
}
