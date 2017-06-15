/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.SponosorModel;
import Model.SponsorCommitsModel;
import Model.SponsorPaymentsModel;
import Model.SponsorsCountMOdel;
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
public class Reports {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Reports() {
        conn = DBUtils.DBUtil.getConnection();
    }

    //get all sponsors
    public List<SponosorModel> allSponors() {
        List<SponosorModel> myList = new ArrayList<>();

        //SELECT `id`, `name`, `mobile`, `email`, `means`, `type`, `company`, `date` FROM `sponsor_applicants` WHERE 1
        String query = "SELECT * FROM sponsor_applicants";
        try {
            
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                SponosorModel sm = new SponosorModel();
                //   sm.setSponsor_id(rs.getString(2));
                sm.setName(rs.getString(2));
                sm.setNumber(rs.getString(3));
                sm.setEmail(rs.getString(4));
                sm.setMeans(rs.getString(5));
                sm.setType(rs.getString(6));
                sm.setCompany(rs.getString(7));
                sm.setDate(rs.getDate("date"));

                myList.add(sm);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myList;
    }
    
    public List<SponsorCommitsModel> getCommits(){
        
        List<SponsorCommitsModel> list = new ArrayList<>();
        String query = "SELECT * FROM `sponsor_commits`";
        
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                SponsorCommitsModel s = new SponsorCommitsModel();
                s.setSponsor(rs.getString(2));
                s.setAmount(rs.getDouble(3));
                s.setDate(rs.getDate(4));
                
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtils.DBUtil.closeConnection(conn);
        }
        
        return list;
       
    }
    
    
    public List<SponsorPaymentsModel> getPayments(){
        
        List<SponsorPaymentsModel> list = new ArrayList<>();
        String query = "SELECT * FROM `sponsor_payments`";
       // SELECT `id`, `sponsor_id`, `amount`, `date`, `type`, `bank`, `slip` FROM `sponsor_payments` WHERE 1
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                SponsorPaymentsModel s = new SponsorPaymentsModel();
           
                s.setSponsor(rs.getString(2));
                s.setAmount(rs.getDouble(3));
                s.setDate(rs.getDate(4));
                s.setBank(rs.getString("bank"));
                s.setSlip(rs.getString("slip"));
                if (rs.getInt("type") == 1) {
                     s.setType("Deposit");
                }else{
                    s.setType("Commitment payment");
                }
               
                
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtils.DBUtil.closeConnection(conn);
        }
        
        return list;
       
    }
}
