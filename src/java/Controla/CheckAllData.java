/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

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
public class CheckAllData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        
        Student st = new Student();
        //check personal details
        if(st.isParentDetailsExist(id)){
            
            //check parents details
            if (st.checkParents(id) || st.checkGurdian(id)) {
                
                //check sibling information
                if (st.checkAllSkuls(id)) {
                    out.print("true");
                }else {
                    out.print("<div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >You haven't added your School Details</div>"); 
                }
                
            }else{
               out.print("<div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >You haven't added your Parent/Gurdian details</div>"); 
            }
            
        }else{
          out.print("<div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >You haven't added your personal details</div>");
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
