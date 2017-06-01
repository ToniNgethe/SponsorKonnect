/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.StudentSchoolModel;
import dao.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toni
 */
public class StudentSchoolServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        Student std = new Student();

        if (action.equals("new")) {

            //get parameters
            String stud_id = request.getParameter("sib_id");
            String name = request.getParameter("school_name");
            String edu = request.getParameter("school_edu");
            String mode = request.getParameter("school_mode");
            String type = request.getParameter("school_type");
            String _clss = request.getParameter("school_class");

            if (!stud_id.isEmpty() && !name.isEmpty() && !edu.isEmpty() && !mode.isEmpty() && !type.isEmpty() && !_clss.isEmpty()) {

                //add data model
                StudentSchoolModel ssm = new StudentSchoolModel(stud_id, name, edu, mode, type, _clss);

                if (!std.checkSchool(stud_id)) {

                    if (std.assSchool(ssm)) {
                        out.print(" <div id='err' class='alert alert-success' role='alert' >Thank you. School Added successfully</div>");
                    } else {
                        out.print(" <div id='err' class='alert alert-danger' role='alert' >Error in saving school</div>");
                    }

                } else {
                    out.print(" <div id='err' class='alert alert-danger' role='alert'  >School already added</div>");
                }

            } else {
                out.print(" <div id='err' class='alert alert-danger' role='alert' >Field(s) empty</div>");
            }
        } else if (action.equals("old")) {

            ///parameters
            String stud_id = request.getParameter("student_id");
            String reg = request.getParameter("selected_school_reg");
            String clss = request.getParameter("selected_school_class");
            String school = request.getParameter("selectedSchool");

            if (!stud_id.isEmpty()) {

                if (!reg.isEmpty() && !clss.isEmpty() && !school.isEmpty()) {

                    //check if stud already added school
                    if (!std.checkSelectedSkull(stud_id)) {

                        //check if school selected binded by student
                        if (!std.checkStudentSchool(stud_id)) {

                            //all good
                            if (std.addSelectedSchool(school, stud_id, clss, reg)) {
                                
                                out.print(" <div id='err' class='alert alert-success' role='alert' >School details successfully saved</div>");
                            } else {
                                out.print(" <div id='err' class='alert alert-danger' role='alert'  >Error occured while saving your school details</div>");
                            }

                        } else {
                            out.print(" <div id='err' class='alert alert-danger' role='alert'>You already added your school details</div>");
                        }

                    } else {
                        out.print(" <div id='err' class='alert alert-danger' role='alert'  >Looks like you have already a school</div>");
                    }

                } else {
                    out.print(" <div id='err' class='alert alert-danger' role='alert'  >Please enter your class and Reg Number</div>");
                }

            } else {
                out.print(" <div id='err' class='alert alert-danger' role='alert'  >Error occured while determining your id</div>");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
