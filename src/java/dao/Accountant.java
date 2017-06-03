/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.AccSponsor;
import Model.AccountantStudentsModel;
import Model.FeesModel;
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
public class Accountant {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    public Accountant() {
        conn = DBUtils.DBUtil.getConnection();
    }

    public int loginAcc(String email, String pass) {
        int success = 0;

        String query = "SELECT * FROM accountant WHERE email = ? && password = ?";

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, email);

            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {
                success = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return success;
    }

    public List<AccountantStudentsModel> allStuds(String acc) {

        List<AccountantStudentsModel> myList = new ArrayList<>();

        //  SELECT `id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added` FROM `student_personal` WHERE 1
        String query = "SELECT student FROM student_sponsor WHERE acc = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, acc);

            rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("Student : " + rs.getString("student"));

                String stud = rs.getString("student");

                String query2 = "SELECT * FROM student_personal WHERE stud_id = ?";
                PreparedStatement p = conn.prepareStatement(query2);
                p.setString(1, stud);

                ResultSet r = p.executeQuery();

                while (r.next()) {
                    AccountantStudentsModel sd = new AccountantStudentsModel();
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

    public List<FeesModel> getStudentSchool(String id) {
        List<FeesModel> myList = new ArrayList<>();

        String query = "SELECT name FROM selected_school WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                String q2 = "SELECT * FROM school_fees WHERE school = ?";
                PreparedStatement p = conn.prepareStatement(q2);
                p.setString(1, rs.getString("name"));

                ResultSet r = p.executeQuery();
                while (r.next()) {
                    FeesModel feesModel = new FeesModel();
                    feesModel.setId(r.getInt(1));
                    feesModel.setName(r.getString(2));
                    feesModel.setFirst(r.getDouble(3));
                    feesModel.setSecond(r.getDouble(4));
                    feesModel.setThird(r.getDouble(5));
                    feesModel.setTotal(r.getDouble(3) + r.getDouble(4) + r.getDouble(5));
                    myList.add(feesModel);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myList;
    }

    public List<AccSponsor> getStudentSponsor(String id) {
        List<AccSponsor> myList = new ArrayList<>();

        String query = "SELECT sponsor FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                AccSponsor accSponsor = new AccSponsor();

                //get sponsor id
                accSponsor.setSponsor_id(rs.getString("sponsor"));

                String q1 = "SELECT name FROM Sponsor WHERE sponsor_id = ?";
                PreparedStatement p1 = conn.prepareStatement(q1);
                p1.setString(1, rs.getString("sponsor"));

                ResultSet r1 = p1.executeQuery();
                if (r1.next()) {

                    //get sponsor name
                    accSponsor.setName(r1.getString("name"));

                    String q2 = "SELECT SUM(amount) FROM sponsor_use WHERE sponsor_id = ?";
                    PreparedStatement p2 = conn.prepareStatement(q2);
                    p2.setString(1, rs.getString("sponsor"));
                    ResultSet rs2 = p2.executeQuery();
                    while (rs2.next()) {

                        //get commits
                        accSponsor.setCommits(rs2.getInt(1));
                    }

                    String q3 = "SELECT SUM(amount) FROM sponsor_payments WHERE sponsor_id = ?";
                    PreparedStatement p3 = conn.prepareStatement(q3);
                    p3.setString(1, rs.getString("sponsor"));
                    ResultSet r3 = p3.executeQuery();
                    while (r3.next()) {

                        //get commits
                        accSponsor.setPayments(r3.getInt(1));
                    }

                    accSponsor.setBal(accSponsor.getPayments() - accSponsor.getCommits());
                   
                    myList.add(accSponsor);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myList;
    }

    //check if allocated for this year...
    public boolean isAllocated(String stud_id) {
        boolean allocated = false;
        String query = "SELECT * FROM student_allocation WHERE stud_id = ?";

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, stud_id);

            rs = pst.executeQuery();

            if (rs.next()) {
                allocated = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allocated;
    }

    public boolean allocateStudents(String id, String amount, String upkeep, String others, String sponsor) {
        boolean success = false;

        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        //INSERT INTO `student_allocation`(`id`, `stud_id`, `school`, `upkeep`, `others`, `date`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6])
        String query = "INSERT INTO `student_allocation`(`stud_id`, `school`, `upkeep`, `others`, `date`) VALUES ( ?, ?, ?, ?,?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setDouble(2, Double.valueOf(amount));
            pst.setDouble(3, Double.valueOf(upkeep));
            pst.setDouble(4, Double.valueOf(others));
            pst.setDate(5,t);
            
            int a = pst.executeUpdate();
            
            if (a>0) {
                success = true;
                
                  //deduct amount from sponsor payments.....
                  String q = "INSERT INTO `sponsor_use`(`stud_id`, `sponsor_id`, `amount`, `date`) VALUES ( ?, ?, ?, ?)";
                  PreparedStatement p = conn.prepareStatement(q);
                  p.setString(1, id);
                  p.setString(2, sponsor);
                  p.setDouble(3, Double.valueOf(amount) + Double.valueOf(upkeep) +  Double.valueOf(others));
                  p.setDate(4, t);
                  
                  int b = p.executeUpdate();
                  if(b > 0){
                      System.out.println("Deduction made successfully");
                  }
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }
    
    public boolean isPaid(String id, String fees){
        boolean paid = false;
        
        String query = "SELECT school FROM student_allocation WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            
            rs  = pst.executeQuery();
            if (rs.next()) {
                
                if (Double.valueOf(fees) >= rs.getDouble("school")){
                    paid = true;
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Accountant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paid;
    }
    
    public boolean alterAllocation(){
        boolean success = false;
        
        return success;
    }
}
