/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toni
 */
public class Sponsor {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Sponsor() {
        conn = DBUtils.DBUtil.getConnection();
    }
    
    public boolean isNew(String email){
        boolean exists = false;
        
        String query = "SELECT email FROM sponsor_applicants WHERE email = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, email);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Sponsor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtils.DBUtil.closeConnection(conn);
        }
        
        
        return exists;
    }

    public boolean registerNew(String name, String number, String email, String means, String type, String company) {
        boolean success = false;
        //get todays date
        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());

        String query = "INSERT INTO `sponsor_applicants`(`name`, `mobile`, `email`, `means`, `type`, `company`, `date`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, number);
            pst.setString(3, email);
            pst.setString(4, means);
            pst.setString(5, type);
            pst.setString(6, company);
            pst.setDate(7, t);
            
            int a = pst.executeUpdate();
            if (a > 0) {
                success = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Sponsor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtils.DBUtil.closeConnection(conn);
        }

        return success;
    }
}
