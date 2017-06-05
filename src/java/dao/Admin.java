/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Controla.SponsorServlet;
import DBUtils.DBUtil;
import Model.ApplicantSponsors;
import Model.FeesModel;
import Model.SchoolsModel;
import Model.SponosorModel;
import Model.StudentModel;
import Model.StudentPersonalModel;
import Model.WorkerModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toni
 */
public class Admin {
    
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private List<String> exists;
    private int totalIDs;
    private int total_commits;
    
    public Admin() {
        conn = DBUtil.getConnection();
    }

    //authenticate admin
    public int isAdmin(String email, String pass) {
        
        int id = 0;
        
        try {
            
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return id;
    }

    //check if worker exists
    public boolean checkWorker(String type, WorkerModel wm) {
        boolean exists = false;
        
        String query = "SELECT name, email FROM " + type + " WHERE name = ? AND email = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, wm.getName());
            pst.setString(2, wm.getEmail());
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return exists;
    }

    //add admin to db
    public boolean addWorker(String type, WorkerModel wm) {
        boolean success = false;
        
        String query = "INSERT INTO `" + type + "`(`name`, `number`, `email`, `location`, `password`) "
                + "VALUES ( ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, wm.getName());
            pst.setString(2, wm.getNumber());
            pst.setString(3, wm.getEmail());
            pst.setString(4, wm.getLocation());
            pst.setString(5, wm.getPassword());
            
            int a = pst.executeUpdate();
            
            if (a > 0) {
                success = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return success;
    }

    //get workers
    public List<WorkerModel> allWorkers(String tablename) {
        List<WorkerModel> myList = new ArrayList<>();
        
        String query = "SELECT * FROM " + tablename + "";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                WorkerModel workerModel = new WorkerModel();
                workerModel.setId(rs.getInt("id"));
                workerModel.setName(rs.getString("name"));
                workerModel.setEmail(rs.getString("email"));
                workerModel.setNumber(rs.getString("number"));
                workerModel.setLocation(rs.getString("location"));
                workerModel.setPassword(rs.getString("password"));
                
                myList.add(workerModel);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        return myList;
    }

    //save worker
    public boolean saveWorker(String id, String tabelename, WorkerModel wm) {
        boolean isSuccessful = false;
        
        String query = "UPDATE " + tabelename + " SET name = ?, number = ?, email = ?, location = ?, password = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, wm.getName());
            pst.setString(2, wm.getNumber());
            pst.setString(3, wm.getEmail());
            pst.setString(4, wm.getLocation());
            pst.setString(5, wm.getPassword());
            pst.setInt(6, Integer.valueOf(id));
            
            int a = pst.executeUpdate();
            if (a > 0) {
                isSuccessful = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        return isSuccessful;
    }

    //check if sponsor id
    public boolean checkSponsorId(String id) {
        boolean exists = false;
        String query = "SELECT sponsor_id FROM Sponsor WHERE sponsor_id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return exists;
    }

    //get sponsor ids
    public List<String> getSponsorsID() {
        exists = new ArrayList<>();
        String query = "SELECT sponsor_id FROM Sponsor";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                exists.add(rs.getString("sponsor_id"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return exists;
    }

    //add new sponsor
    public boolean addSponsor(SponosorModel sp) {
        boolean success = false;
        
        String query = "INSERT INTO `Sponsor`(`sponsor_id`, `name`, `mobile`, `email`, `means`, `type`, `company`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, sp.getSponsor_id());
            pst.setString(2, sp.getName());
            pst.setString(3, sp.getNumber());
            pst.setString(4, sp.getEmail());
            pst.setString(5, sp.getMeans());
            pst.setString(6, sp.getType());
            pst.setString(7, sp.getCompany());
            
            int a = pst.executeUpdate();
            if (a > 0) {
                success = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }
        
        return success;
    }

    //add sponsor comiits
    public boolean saveCommits(String id, String amount) {
        boolean isSuccess = false;

        //get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        
        String query = "INSERT INTO `sponsor_commits`(`sponsor_id`, `amount`, `date`) VALUES (?,?,?)";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setDouble(2, Double.valueOf(amount));
            pst.setDate(3, t);
            
            int a = pst.executeUpdate();
            if (a > 0) {
                isSuccess = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuccess;
    }

    //check if ammount submited is greater than comite
    public boolean checkAmount(String id, String amount) {
        boolean isGreat = false;
        
        String query = "SELECT SUM(amount) FROM sponsor_commits WHERE sponsor_id = ?";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                total_commits = rs.getInt(1);
                
                if (Integer.valueOf(amount) <= total_commits) {
                    isGreat = true;
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isGreat;
    }

    //check total payments reached
    public boolean checkTotalPayment(String id, String amount) {
        boolean isGreate = false;
        
        String query = "SELECT SUM(amount) FROM sponsor_payments WHERE sponsor_id = ? AND type = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setInt(2, 0);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                int a = rs.getInt(1) + Integer.valueOf(amount);
                
                if (a <= total_commits) {
                    isGreate = true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isGreate;
    }

    //save payment
    public boolean savePayment(String id, String amount, int type) {
        boolean isSaved = false;

        //get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        
        String query = "INSERT INTO `sponsor_payments`(`sponsor_id`, `amount`, `date`, `type`) VALUES ( ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            
            pst.setDouble(2, Double.valueOf(amount));
            pst.setDate(3, t);
            pst.setInt(4, type);
            
            int a = pst.executeUpdate();
            if (a > 0) {
                isSaved = true;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSaved;
    }

    //get all stud
    public List<StudentModel> getStudents() {
        List<StudentModel> list = new ArrayList<>();
        
        String query = "SELECT * FROM student";
        String query2 = "SELECT * FROM student_personal WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                PreparedStatement pst1 = conn.prepareStatement(query2);
                pst1.setString(1, String.valueOf(rs.getInt(1)));
                
                ResultSet rs2 = pst1.executeQuery();
                
                if (rs2.next()) {
                    StudentModel sm = new StudentModel(rs.getInt(1), rs.getString(2), rs.getString(3));
                   
                    list.add(sm);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    //get student personal details
    public List<StudentPersonalModel> getPersDetails(String id) {
        List<StudentPersonalModel> list = null;
        
        String query = "SELECT * FROM `student_personal` WHERE `stud_id` = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, Integer.valueOf(id));

            //SELECT `id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`,`age`, `dob`, `added` FROM `student_personal` WHERE 1
            rs = pst.executeQuery();
            
            while (rs.next()) {
                list = new ArrayList<>();
                StudentPersonalModel std = new StudentPersonalModel(
                        rs.getString("stud_id"), rs.getString("s_name"), rs.getString("f_name"), rs.getString("l_name"), rs.getString("gender"),
                        rs.getString("number"), rs.getString("location"), rs.getString("status"), rs.getDate("dob"), rs.getString("age"));
                list.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    //check social worker if its valid
    public boolean checkSocialWorker(String input) {
        boolean exists = false;
        
        String query = "SELECT number, email FROM social_worker WHERE number = ? OR email = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, input);
            pst.setString(2, input);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }

    //check social worker if its valid
    public boolean checkAccountant(String input) {
        boolean exists = false;
        
        String query = "SELECT number, email FROM accountant WHERE number = ? OR email = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, input);
            pst.setString(2, input);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }

    //assign sponsor
    public boolean assignSponsor(String student, String sponsor, String acc, String social) {
        boolean isSuccess = false;

        //get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        
        String queru = "INSERT INTO `student_sponsor`(`student`, `sponsor`, `social`, `acc`, `date`) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(queru);
            pst.setString(1, student);
            pst.setString(2, sponsor);
            pst.setString(3, social);
            pst.setString(4, acc);
            pst.setDate(5, t);
            
            int a = pst.executeUpdate();
            
            if (a > 0) {
                isSuccess = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuccess;
    }

    //check if student has been assigned
    public boolean checkIfAssigned(String id) {
        boolean assigned = false;
        
        String queru = "SELECT student FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(queru);
            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                assigned = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assigned;
    }

    //check if skul exists
    public boolean checkSkull(String name) {
        boolean exists = false;
        
        String query = "SELECT name FROM school WHERE name = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }

    //add new school
    public boolean addSkull(String name, String mode, String means) {
        boolean added = false;

//get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        
        String query = "INSERT INTO `school`(`name`, `mode`, `means`, `date`) VALUES (?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, mode);
            pst.setString(3, means);
            pst.setDate(4, t);
            
            int a = pst.executeUpdate();
            
            if (a > 0) {
                added = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return added;
    }

    //check if data stracture is available
    public boolean checkFeeStracture(String name) {
        
        boolean exists = false;
        
        String query = "SELECT school FROM school_fees WHERE school = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
        
    }

    //add fee stracture
    public boolean addFeeStracture(String name, String first, String second, String third) {
        boolean inserted = false;

        //get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());
        
        String query = "INSERT INTO `school_fees`( `school`, `first_term`, `second_term`, `third_term`, `date`) VALUES ( ?, ?, ?, ?, ?)";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setDouble(2, Double.valueOf(first));
            pst.setDouble(3, Double.valueOf(second));
            pst.setDouble(4, Double.valueOf(third));
            pst.setDate(5, t);
            
            int a = pst.executeUpdate();
            
            if (a > 0) {
                inserted = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return inserted;
    }

    //get all sponsors
    public List<SponosorModel> allSponors() {
        List<SponosorModel> myList = new ArrayList<>();

        //SELECT `id`, `sponsor_id`, `name`, `mobile`, `email`, `means`, `type`, `company` FROM `Sponsor` WHERE 1
        String query = "SELECT * FROM Sponsor";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                SponosorModel sm = new SponosorModel();
                sm.setSponsor_id(rs.getString(2));
                sm.setName(rs.getString(3));
                sm.setNumber(rs.getString(4));
                sm.setEmail(rs.getString(5));
                sm.setMeans(rs.getString(6));
                sm.setType(rs.getString(7));
                sm.setCompany(rs.getString(8));
         
                myList.add(sm);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;
    }

    //get all schools
    public List<SchoolsModel> allSkuls() {
        List<SchoolsModel> myList = new ArrayList<>();

        // SELECT `id`, `name`, `mode`, `means`, `date` FROM `school` WHERE 1
        String query = "SELECT * FROM school";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                SchoolsModel sm = new SchoolsModel();
                sm.setId(rs.getInt(1));
                sm.setName(rs.getString(2));
                sm.setMode(rs.getString(3));
                sm.setMeans(rs.getString(4));
                sm.setDate(rs.getDate(5));
                
                myList.add(sm);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;
    }

    //get all schools
    public List<FeesModel> allSkulFees() {
        List<FeesModel> myList = new ArrayList<>();

        // SELECT `id`, `school`, `first_term`, `second_term`, `third_term`, `date` FROM `school_fees` WHERE 1
        String query = "SELECT * FROM school_fees";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                FeesModel feesModel = new FeesModel();
                feesModel.setId(rs.getInt(1));
                feesModel.setName(rs.getString(2));
                feesModel.setFirst(rs.getDouble(3));
                feesModel.setSecond(rs.getDouble(4));
                feesModel.setThird(rs.getDouble(5));
                feesModel.setTotal(rs.getDouble(3) + rs.getDouble(4) + rs.getDouble(5));
                System.out.println(myList);
                myList.add(feesModel);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;
    }
    
    public boolean upDateFees(String id, String first, String second, String third) {
        boolean Updated = false;
        
        String query = "UPDATE school_fees SET first_term = ?, second_term = ?, third_term = ? WHERE id = ?";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setDouble(1, Double.valueOf(first));
            pst.setDouble(2, Double.valueOf(second));
            pst.setDouble(3, Double.valueOf(third));
            pst.setInt(4, Integer.valueOf(id));
            
            int a = pst.executeUpdate();
            
            if (a>0) {
                Updated = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Updated;
    }
    
      //get all sponsors
    public List<ApplicantSponsors> allApplicantSponors() {
        List<ApplicantSponsors> myList = new ArrayList<>();

     //  SELECT `id`, `name`, `mobile`, `email`, `means`, `type`, `company`, `date` FROM `sponsor_applicants` WHERE 1
        String query = "SELECT * FROM sponsor_applicants";
        try {
            pst = conn.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                ApplicantSponsors sm = new ApplicantSponsors();
                //sm.setSponsor_id(rs.getString(2));
                sm.setName(rs.getString("name"));
                sm.setNumber(rs.getString("mobile"));
                sm.setEmail(rs.getString("email"));
                sm.setMeans(rs.getString("means"));
                sm.setType(rs.getString("type"));
                sm.setCompany(rs.getString("company"));
                sm.setDate(rs.getDate("date"));
                sm.setSponsor_id();
                
                myList.add(sm);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;
    }
    
 
}

