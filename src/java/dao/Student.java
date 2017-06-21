/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBUtils.DBUtil;
import Model.SocialWorkerMOdel;
import Model.SponosorModel;
import Model.StudentGurdianModel;
import Model.StudentParentsModel;
import Model.StudentPersonalModel;
import Model.StudentSchoolModel;
import Model.StudentSiblingModel;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toni
 */
public class Student {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Student() {
        conn = (Connection) DBUtil.getConnection();
    }

    //check if student exists...
    public boolean studentExists(String email) {

        boolean exists = false;

        String query = "SELECT email FROM student WHERE email = ?";
        try {

            pst = conn.prepareStatement(query);
            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return exists;
    }

    //register student
    public boolean newStudent(String email, String pass, InputStream ip) {

        boolean success = false;

        try {
            java.util.Date today = new Date();
            java.sql.Date t = new java.sql.Date(today.getTime());

            String insert = "INSERT INTO `student`(`email`, `password`, `date`, `image`) VALUES (?, ?, ?, ?)";
            pst = (PreparedStatement) conn.prepareStatement(insert);
            pst.setString(1, email);
            pst.setString(2, pass);
            pst.setDate(3, t);
            pst.setBlob(4, ip);

            int a = pst.executeUpdate();

            if (a > 0) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();

        }

        return success;
    }

    //login student
    public int loginStudent(String email, String pass) {
        int result = 0;

        String query = "SELECT * FROM student WHERE email = ? AND password = ?";

        try {
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return result;
    }

    private void closeConnection() {
        try {
            DBUtil.closeConnection(conn);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean isParentDetailsExist(String id) {

        boolean exists = false;

        try {
            String query = "SELECT stud_id FROM student_personal WHERE stud_id = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return exists;
    }

    //Student details...
    public boolean addPersoalDetails(StudentPersonalModel studentPersonalModel) {
        boolean isSuccess = false;
        try {

            java.util.Date today = new java.util.Date();
            java.sql.Date t = new java.sql.Date(today.getTime());
            //INSERT INTO `student_personal`(`id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `age`, `dob`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9])

            String query = "INSERT INTO `student_personal`( `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, studentPersonalModel.getStud_id());
            pst.setString(2, studentPersonalModel.getS_name());
            pst.setString(3, studentPersonalModel.getF_name());
            pst.setString(4, studentPersonalModel.getL_name());
            pst.setString(5, studentPersonalModel.getGender());
            pst.setString(6, studentPersonalModel.getNumber());
            pst.setString(7, studentPersonalModel.getLocation());
            pst.setString(8, studentPersonalModel.getStatus());
            pst.setString(9, studentPersonalModel.getAge());
            pst.setDate(10, new java.sql.Date(studentPersonalModel.getDob().getTime()));
            pst.setDate(11, t);

            int a = pst.executeUpdate();
            if (a > 0) {
                isSuccess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return isSuccess;
    }

    //check if parents details exist
    public boolean checkParents(String id) {
        boolean exists = false;

        try {
            String query = "SELECT stud_id FROM student_parents WHERE stud_id = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return exists;
    }

    //insert parents
    public boolean addParents(StudentParentsModel spm) {
        boolean success = false;
        try {

            String query = "INSERT INTO `student_parents`( `stud_id`, `f_name`, `f_occup`, `f_mobile`, `m_name`, `m_occup`, `m_mobile`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(query);
            pst.setString(1, spm.getStud_id());
            pst.setString(2, spm.getF_name());
            pst.setString(3, spm.getF_occup());
            pst.setString(4, spm.getF_mobile());
            pst.setString(5, spm.getM_name());
            pst.setString(6, spm.getM_occup());
            pst.setString(7, spm.getM_mobile());

            int a = pst.executeUpdate();
            if (a > 0) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeConnection();
        }

        return success;
    }

    //check if gurdian exists
    public boolean checkGurdian(String id) {
        boolean exists = false;

        try {
            String query = "SELECT stud_id FROM student_gurdian WHERE stud_id = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return exists;
    }

    //ADD GURDIAN 
    public boolean addGurdian(StudentGurdianModel sgm) {

        boolean success = false;

        try {

            String qury = "INSERT INTO `student_gurdian`( `stud_id`, `name`, `occup`, `mobile`) VALUES ( ?, ?, ?, ?)";
            pst = conn.prepareStatement(qury);
            pst.setString(1, sgm.getStud_id());
            pst.setString(2, sgm.getName());
            pst.setString(3, sgm.getOccupation());
            pst.setString(4, sgm.getMobile());

            int a = pst.executeUpdate();

            if (a > 0) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return success;
    }
    //check sibling

    public boolean checkSibling(String id, String name) {
        boolean exists = false;

        try {
            String query = "SELECT stud_id,name FROM student_sibling WHERE stud_id = ? AND name = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, name);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return exists;
    }

    //ADD SIBLING
    public boolean addSibling(StudentSiblingModel siblingModel) {
        boolean success = false;
        try {

            String query = "INSERT INTO `student_sibling`(`stud_id`, `name`, `edu_level`, `school`, `age`) "
                    + "VALUES (?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, siblingModel.getUser_id());
            pst.setString(2, siblingModel.getName());
            pst.setString(3, siblingModel.getEdu());
            pst.setString(4, siblingModel.getSchool());
            pst.setString(5, siblingModel.getAge());

            int a = pst.executeUpdate();

            if (a > 0) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return success;
    }

    //count total siblings
    public int totalSiblingsAdded(String id) {
        int count = 0;
        try {

            String query = "SELECT COUNT(*) FROM student_sibling WHERE stud_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return count;
    }

    //insert school
    public boolean assSchool(StudentSchoolModel ssm) {

        boolean sucess = false;

        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());

        try {

            String query = "INSERT INTO `student_school`(`stud_id`, `name`,`education_level`, `mode`, `type`, `class`, `date`) "
                    + "VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);
            pst.setString(1, ssm.getStud_id());
            pst.setString(2, ssm.getName());
            pst.setString(3, ssm.getEdu_level());
            pst.setString(4, ssm.getMode());
            pst.setString(5, ssm.getType());
            pst.setString(6, ssm.getClss());
            pst.setDate(7, t);
            int a = pst.executeUpdate();

            if (a > 0) {
                sucess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return sucess;
    }

    //check skull
    public boolean checkSchool(String id) {
        boolean exists = false;

        try {
            String query = "SELECT stud_id FROM student_school WHERE stud_id = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return exists;
    }

    //check skull
    public boolean checkSelectedSkull(String id) {
        boolean exists = false;

        try {
            String query = "SELECT student FROM selected_school WHERE student = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return exists;
    }

    //record student selected school
    public boolean addSelectedSchool(String name, String student, String clss, String reg) {
        boolean added = false;

        java.util.Date today = new java.util.Date();
        java.sql.Date t = new java.sql.Date(today.getTime());

        String query = "INSERT INTO `selected_school`(`name`, `student`, `class`, `reg`, `date`) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, student);
            pst.setString(3, clss);
            pst.setString(4, reg);
            pst.setDate(5, t);

            int a = pst.executeUpdate();
            if (a > 0) {
                added = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return added;
    }

    //check if student school is already reg
    //check skull
    public boolean checkStudentSchool(String id) {
        boolean exists = false;

        try {
            String query = "SELECT stud_id FROM selected_school WHERE student = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return exists;
    }

    //check all data
    public boolean checkAllSkuls(String id) {
        boolean exists = false;

        String q1 = "SELECT * FROM selected_school WHERE student = ?";
        String q2 = "SELECT * FROM student_school WHERE stud_id = ?";

        try {
            pst = conn.prepareStatement(q1);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                //found
                exists = true;
            } else {

                PreparedStatement p = conn.prepareStatement(q2);
                p.setString(1, id);
                ResultSet r = p.executeQuery();

                if (r.next()) {
                    //found 
                    exists = true;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return exists;
    }

    public boolean checkAllParents(String id) {
        boolean exits = false;
        String queryParents = "SELECT * FROM student_parents WHERE stud_id = ?";
        String queryGurdian = "SELECT * FROM student_gurdian WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(queryParents);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                exits = true;
            } else {

                PreparedStatement p = conn.prepareStatement(queryGurdian);
                p.setString(1, id);
                ResultSet r = p.executeQuery();

                if (r.next()) {
                    exits = true;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exits;
    }

    public StudentPersonalModel retrievePersonalDetaisl(String id) {
        StudentPersonalModel studentPersonalModel = new StudentPersonalModel();

        String query = "SELECT * FROM student_personal WHERE stud_id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                // SELECT `id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added` FROM `student_personal` WHERE 1
                studentPersonalModel.setS_name(rs.getString("s_name"));
                studentPersonalModel.setF_name(rs.getString("f_name"));
                studentPersonalModel.setL_name(rs.getString("l_name"));
                studentPersonalModel.setGender(rs.getString("gender"));
                studentPersonalModel.setNumber(rs.getString("number"));
                studentPersonalModel.setLocation(rs.getString("location"));
                studentPersonalModel.setDob(rs.getDate("dob"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return studentPersonalModel;
    }

    //check if student has been allocated
    public int checkAllocation(String id) {

        int a = 0;

        String query = "SELECT * FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                a = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

        return a;

    }

    public List<SponosorModel> getAllocatedSponsor(String id) {

        List<SponosorModel> list = new ArrayList<>();
        
        String query = "SELECT sponsor FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();
            if (rs.next()) {

                String query2 = "SELECT * FROM Sponsor WHERE sponsor_id = ?";
                PreparedStatement p = conn.prepareStatement(query2);
                p.setString(1, rs.getString("sponsor"));

                ResultSet r = p.executeQuery();

                if (r.next()) {
                    SponosorModel sponosorModel = new SponosorModel();
                    sponosorModel.setName(r.getString("name"));
                    sponosorModel.setEmail(r.getString("email"));
                    sponosorModel.setNumber(r.getString("mobile"));
                    list.add(sponosorModel);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
     public List<SocialWorkerMOdel> getAllocatedSocialWorker(String id) {

        List<SocialWorkerMOdel> list = new ArrayList<>();
        
        String query = "SELECT social FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();
            if (rs.next()) {

                String query2 = "SELECT * FROM social_worker WHERE email = ?";
                PreparedStatement p = conn.prepareStatement(query2);
                p.setString(1, rs.getString("social"));

                ResultSet r = p.executeQuery();

                if (r.next()) {
                    
                    SocialWorkerMOdel sponosorModel = new SocialWorkerMOdel();
                    sponosorModel.setName(r.getString("name"));
                    sponosorModel.setEmail(r.getString("email"));
                    sponosorModel.setMobile(r.getString("number"));
                    list.add(sponosorModel);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

       public List<SocialWorkerMOdel> getAllocatedAcc(String id) {

        List<SocialWorkerMOdel> list = new ArrayList<>();
        
        String query = "SELECT acc FROM student_sponsor WHERE student = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, id);

            rs = pst.executeQuery();
            if (rs.next()) {

                String query2 = "SELECT * FROM accountant WHERE email = ?";
                PreparedStatement p = conn.prepareStatement(query2);
                p.setString(1, rs.getString("acc"));

                ResultSet r = p.executeQuery();

                if (r.next()) {
                    
                    SocialWorkerMOdel sponosorModel = new SocialWorkerMOdel();
                    sponosorModel.setName(r.getString("name"));
                    sponosorModel.setEmail(r.getString("email"));
                    sponosorModel.setMobile(r.getString("number"));
                    list.add(sponosorModel);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
