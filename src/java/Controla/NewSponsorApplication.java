/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import dao.Sponsor;
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
public class NewSponsorApplication extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/plain");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("new_sponsor_name");
        String number = request.getParameter("new_sponsor_mobile");
        String email = request.getParameter("new_sponsor_email");
        String means = request.getParameter("new_sponsor_means");
        String type = request.getParameter("new_sponsor_type");
        String company = request.getParameter("new_sponsor_company");

        Sponsor sp = new Sponsor();
        
        if (!name.isEmpty() && !number.isEmpty() && !means.isEmpty() && !type.isEmpty() && !email.isEmpty() && !company.isEmpty()) {
            
            if (!sp.isNew(email)) {
                if (sp.registerNew(name, number, email, means, type, company)) {
                      out.print("<div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Details saved successfully, we will get in touch soon</div>");
                }else{
                    out.print("<div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>An error occured while saving your details.Try later</div>");
                }
            }else{
                 out.print("<div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>A sponsor by that email already exists in our database</div>");
            }
            
        } else {
            out.print("<div class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Field(s) cannot be empty</div>");
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
