/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toni
 */
public class DBUtil {

    private static Connection conn;

    public static Connection getConnection(){
        if (conn != null) {
            return conn;
        }
        
        try {
            
            Class.forName( "com.mysql.jdbc.Driver" );
            conn =  DriverManager.getConnection( "jdbc:mysql://localhost:3306/konnect", "root", "");
            
   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
        
        return conn;
    }
    
    public static void closeConnection(Connection tobeClosed){
        if(tobeClosed != null){
            return;
        }
        
        try {
            tobeClosed.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
              System.out.println(ex.getMessage());
        }
    }
  
}
