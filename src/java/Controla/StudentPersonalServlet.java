/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.StudentPersonalModel;
import dao.Student;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author toni
 */
public class StudentPersonalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String stud_id = request.getParameter("id");
        String s_name = request.getParameter("stud_surname");
        String f_name = request.getParameter("first_name");
        String l_name = request.getParameter("last_name");
        String gender = request.getParameter("stud_gender");
        String number = request.getParameter("mobile");
        String fam_location = request.getParameter("famLocation");
      
        String dob = request.getParameter("dob");

        if (!stud_id.isEmpty()) {
            if (s_name.isEmpty() || f_name.isEmpty() || l_name.isEmpty() || number.isEmpty() || fam_location.isEmpty()  || dob.isEmpty()) {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Field(s) Empty</div>");
            } else {

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    //convert string input to date
                    java.util.Date result = format.parse(dob);

                    StudentPersonalModel studentPersonalModel = new StudentPersonalModel(stud_id, s_name, f_name, l_name, gender, number, fam_location, null, result, null);
                    Student std = new Student();

                    //check if details do exist
                    if (!std.isParentDetailsExist(stud_id)) {
                        
                        //add if none foun
                        if (std.addPersoalDetails(studentPersonalModel)) {
                            
                            out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Details saved successfully</div>");

                        } else {
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Error in saving your details</div>");
                        }
                    }else{
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Personal Details already exists</div>");
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(StudentPersonalServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >" + ex.getMessage() + "</div>");
                }
            }
        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Error in getting student id</div>");
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
