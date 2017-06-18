/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.FeesModel;
import Model.SchoolsModel;
import Model.SponosorModel;
import Model.SponsorCommitsModel;
import Model.SponsorPaymentsModel;
import Model.SponsorsCountMOdel;
import Model.StudentAllocationModel;
import Model.StudentPersonalModel;
import Model.StudentSponsorModel;
import Model.SuggestedSchoolsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * conn
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
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return myList;
    }

    public List<SponsorCommitsModel> getCommits() {

        List<SponsorCommitsModel> list = new ArrayList<>();
        String query = "SELECT * FROM `sponsor_commits`";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                SponsorCommitsModel s = new SponsorCommitsModel();
                s.setSponsor(rs.getString(2));
                s.setAmount(rs.getDouble(3));
                s.setDate(rs.getDate(4));

                list.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;

    }

    public List<SponsorPaymentsModel> getPayments() {

        List<SponsorPaymentsModel> list = new ArrayList<>();
        String query = "SELECT * FROM `sponsor_payments`";
        // SELECT `id`, `sponsor_id`, `amount`, `date`, `type`, `bank`, `slip` FROM `sponsor_payments` WHERE 1
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                SponsorPaymentsModel s = new SponsorPaymentsModel();

                s.setSponsor(rs.getString(2));
                s.setAmount(rs.getDouble(3));
                s.setDate(rs.getDate(4));
                s.setBank(rs.getString("bank"));
                s.setSlip(rs.getString("slip"));
                if (rs.getInt("type") == 1) {
                    s.setType("Deposit");
                } else {
                    s.setType("Commitment payment");
                }

                list.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;

    }

    public List<StudentPersonalModel> getAllStudents() {
        List<StudentPersonalModel> list = new ArrayList();
        //SELECT `id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added` FROM `student_personal` WHERE 1
        String query = "SELECT * FROM `student_personal`";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                StudentPersonalModel std = new StudentPersonalModel();
                std.setStud_id(rs.getString("stud_id"));
                std.setS_name(rs.getString("s_name"));
                std.setF_name(rs.getString("f_name"));
                std.setL_name(rs.getString("l_name"));
                std.setGender(rs.getString("gender"));
                std.setNumber(rs.getString("number"));
                std.setLocation(rs.getString("location"));
                std.setDob(rs.getDate("dob"));
                std.setAdded(rs.getDate("added"));

                list.add(std);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

    public List<StudentSponsorModel> getStudentAss() {
        List<StudentSponsorModel> list = new ArrayList<>();
        //   SELECT `id`, `student`, `sponsor`, `social`, `acc`, `date` FROM `student_sponsor` WHERE 1
        String query = "SELECT * FROM student_sponsor";
        String stud_query = "SELECT s_name,f_name, l_name FROM student_personal WHERE stud_id = ?";
        String sponsor_query = "SELECT name FROM Sponsor WHERE sponsor_id = ?";
        String soc_query = "SELECT name FROM social_worker WHERE email = ?";
        String acc_query = "SELECT name FROM accountant WHERE email = ?";

        try {
            pst = conn.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                StudentSponsorModel sponsorModel = new StudentSponsorModel();

                sponsorModel.setStud_id(rs.getString("student"));
                sponsorModel.setDate(rs.getDate("date"));

                PreparedStatement stud_p = conn.prepareStatement(stud_query);
                stud_p.setString(1, rs.getString("student"));
                ResultSet stud_r = stud_p.executeQuery();

                if (stud_r.next()) {
                    sponsorModel.setStud_name(stud_r.getString("s_name") + " " + stud_r.getString("f_name") + " " + stud_r.getString("l_name"));
                }

                PreparedStatement sponsor_p = conn.prepareStatement(sponsor_query);
                sponsor_p.setString(1, rs.getString("sponsor"));
                ResultSet sponsor_r = sponsor_p.executeQuery();

                if (sponsor_r.next()) {
                    sponsorModel.setSponsor_name(sponsor_r.getString("name"));
                }

                PreparedStatement soc_p = conn.prepareStatement(soc_query);
                soc_p.setString(1, rs.getString("social"));
                ResultSet soc_r = soc_p.executeQuery();

                if (soc_r.next()) {
                    sponsorModel.setSocial_worker(soc_r.getString("name"));
                }
                PreparedStatement acc_p = conn.prepareStatement(acc_query);
                acc_p.setString(1, rs.getString("acc"));
                ResultSet acc_r = acc_p.executeQuery();

                if (acc_r.next()) {

                    sponsorModel.setAcc_name(acc_r.getString("name"));

                }

                list.add(sponsorModel);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

    public List<StudentAllocationModel> getAllocations() {
        List<StudentAllocationModel> list = new ArrayList<>();

        String query = "SELECT * FROM student_allocation";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                StudentAllocationModel sl = new StudentAllocationModel();
                sl.setStudId(rs.getString(2));
                sl.setSchool(rs.getDouble(3));
                sl.setUpkeep(rs.getDouble(4));
                sl.setOthers(rs.getDouble(5));
                sl.setDate(rs.getDate(6));

                list.add(sl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

    public List<SchoolsModel> getRegSchools() {
        List<SchoolsModel> list = new ArrayList<>();

        String query = "SELECT * FROM school";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                SchoolsModel schoolsModel = new SchoolsModel();
                schoolsModel.setId(rs.getInt(1));
                schoolsModel.setName(rs.getString(2));
                schoolsModel.setMode(rs.getString(3));
                schoolsModel.setMeans(rs.getString(4));
                schoolsModel.setDate(rs.getDate(5));

                list.add(schoolsModel);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

    public List<FeesModel> getSchoolFees() {
        List<FeesModel> list = new ArrayList<>();

        String query = "SELECT * FROM school_fees";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                FeesModel fees = new FeesModel();
                fees.setName(rs.getString(2));
                fees.setFirst(rs.getDouble(3));
                fees.setSecond(rs.getDouble(4));
                fees.setThird(rs.getDouble(5));
                fees.setDate(rs.getDate(6));

                list.add(fees);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

    public List<SuggestedSchoolsModel> getSuggestedSchools() {
        List<SuggestedSchoolsModel> list = new ArrayList<>();

        String query = "SELECT * FROM student_school";
        String stud_query = "SELECT s_name, f_name, l_name FROM student_personal WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                SuggestedSchoolsModel schoolsModel = new SuggestedSchoolsModel();

                PreparedStatement p = conn.prepareStatement(stud_query);
                p.setString(1, rs.getString(2));
                ResultSet r = p.executeQuery();

                if (r.next()) {
                    schoolsModel.setStudName(r.getString("s_name") +" "+ r.getString("f_name") +" " +r.getString("l_name"));
                }else{
                    schoolsModel.setStudName("Not Found");
                }
                schoolsModel.setName(rs.getString("name"));
                schoolsModel.setEducation_level(rs.getString("education_level"));
                schoolsModel.setMode(rs.getString("mode"));
                schoolsModel.setType(rs.getString("type"));
                schoolsModel.setCl(rs.getString("class"));
                schoolsModel.setDate(rs.getDate("date"));
                
                list.add(schoolsModel);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtils.DBUtil.closeConnection(conn);
        }

        return list;
    }

}
