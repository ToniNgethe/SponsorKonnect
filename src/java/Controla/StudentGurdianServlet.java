/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.StudentGurdianModel;
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
public class StudentGurdianServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();
        
        //parameters..
        String stud_id = request.getParameter("g_id");
        String name = request.getParameter("g_name");
        String occupation = request.getParameter("g_occupation");
        String mobile = request.getParameter("g_number");
        
        if (!stud_id.isEmpty() && !name.isEmpty() && !occupation.isEmpty() && !mobile.isEmpty()) {

            //save to model
            StudentGurdianModel studentGurdianModel = new StudentGurdianModel(stud_id, name, occupation, mobile);
            Student std = new Student();
            
            //check if parents already added
            if(!std.checkParents(stud_id)){
                
                //paernts not found...proceed
                if (!std.checkGurdian(stud_id)) {
                    
                    if(std.addGurdian(studentGurdianModel)){
                        out.print("<div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Gurdian successfully added</div>");
                    }
                    else{
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Error in saving gurdian</div>");
                    }
                }else{
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Gurdian already added</div>");
                }
                
            }else{
                //parents added so no need of gurdians
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Records of your Parents found, cannot add gurdian</div>");
            }
            
        }else{
             out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Field(s) empty</div>");
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
