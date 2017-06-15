/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    private ResultSet rst;
    
    public Reports(){
        
    }
    
    public List<SponsorsCountMOdel> getTotalSponsors(){
        List<SponsorsCountMOdel> list = new ArrayList<>();
        SponsorsCountMOdel sp = new SponsorsCountMOdel();
        String query = "SELECT COUNT(*) FROM Sponsor";
        String q2 = "SELECT COUNT(*) FROM sponsor_applicants";
        
        try {
            pst = conn.prepareStatement(query);
            rst = pst.executeQuery();
            
            if(rst.next()){
                sp.setRegisterd(rst.getInt(1));
            }
            
            PreparedStatement p = conn.prepareStatement(q2);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                sp.setApplied(rst.getInt(1));
            }
            
            
            list.add(sp);
            
        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
