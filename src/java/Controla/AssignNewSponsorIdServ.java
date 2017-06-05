/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.SponosorModel;
import dao.Admin;
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
public class AssignNewSponsorIdServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String sponsor_id = request.getParameter("sponsor_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String means = request.getParameter("means");
        String type = request.getParameter("type");
        String company = request.getParameter("company");
        String number = request.getParameter("number");

        Admin ad = new Admin();

        
        if (!sponsor_id.isEmpty() && !name.isEmpty() && !email.isEmpty() && !means.isEmpty() && !type.isEmpty() && !company.isEmpty()) {

            if (!ad.checkSponsorId(sponsor_id)) {
                SponosorModel sp = new SponosorModel(sponsor_id, name, number, email, means, type, company);
                if (ad.addSponsor(sp)) {
                    out.print("  <div id='err' class='alert alert-success' role='alert' style='margin:4%;' >Sponsor successfully assigned.</div>");
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Error in assigning sponsor id</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Sponsor with that id already exists</div>");
            }

        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to get sponsor details</div>");
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
