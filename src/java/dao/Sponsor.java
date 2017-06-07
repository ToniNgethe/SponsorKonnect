/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.AssignedStudentModel;
import Model.SponosorModel;
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
public class Sponsor {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Sponsor() {
        conn = DBUtils.DBUtil.getConnection();
    }

    public List<AssignedStudentModel> assignedList(String sponsor) {
        List<AssignedStudentModel> myList = new ArrayList<>();

        String query = "SELECT student FROM student_sponsor WHERE sponsor = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, sponsor);

            rs = pst.executeQuery();

            while (rs.next()) {

                String q2 = "SELECT * FROM student_personal WHERE stud_id = ?";
                PreparedStatement p = conn.prepareStatement(q2);
                p.setString(1, rs.getString("student"));

                ResultSet r = p.executeQuery();

                while (r.next()) {

                    String schol = "SELECT name, class FROM selected_school WHERE student = ?";
                    PreparedStatement s = conn.prepareStatement(schol);
                    s.setString(1, rs.getString("student"));

                    ResultSet sr = s.executeQuery();

                    while (sr.next()) {

                        AssignedStudentModel ad = new AssignedStudentModel();
                        ad.setStud_id(r.getString("stud_id"));
                        ad.setF_name(r.getString("f_name"));
                        ad.setL_name(r.getString("l_name"));
                        ad.setS_name(r.getString("s_name"));
                        ad.setSchool(sr.getString("name"));
                        ad.setLevel(sr.getString("class"));

                        myList.add(ad);

                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Sponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myList;
    }

    public int checkLogin(String id, String pass) {
        int a = 0;
        String query = "SELECT * FROM Sponsor WHERE sponsor_id = ? AND pass = ?";

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {

                a = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Sponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public SponosorModel getSponsorDetails(String id, String pass) {
        SponosorModel sp = new SponosorModel();
        // SELECT `id`, `sponsor_id`, `name`, `mobile`, `email`, `means`, `type`, `company`, `pass` FROM `Sponsor` WHERE 1
        String query = "SELECT * FROM Sponsor WHERE sponsor_id = ? AND pass = ?";

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {

                sp.setSponsor_id(rs.getString("sponsor_id"));
                sp.setName(rs.getString("name"));
                sp.setNumber(rs.getString("mobile"));
                sp.setEmail(rs.getString("email"));
                sp.setMeans(rs.getString("means"));
                sp.setType(rs.getString("type"));
                sp.setCompany(rs.getString("company"));
                sp.setPass(rs.getString("pass"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Sponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sp;
    }

    public boolean isNew(String email) {
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
        } finally {
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
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return success;
    }
}
