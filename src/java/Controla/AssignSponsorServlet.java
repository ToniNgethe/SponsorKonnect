/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import dao.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignSponsorServlet extends HttpServlet {

    private Admin admin;

    public AssignSponsorServlet() {
        admin = new Admin();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String stud_id = request.getParameter("ass_student_id");
        String sponsor = request.getParameter("ass_sponsor_assigned");
        String social_worker = request.getParameter("ass_social_assigned");
        String acc = request.getParameter("ass_acc_assigned");
        
             
        if (!sponsor.isEmpty() && !social_worker.isEmpty() && !acc.isEmpty()) {

            //check if sposnor is valid
            if (admin.checkSponsorId(sponsor)) {

                //check social worker
                if (admin.checkSocialWorker(social_worker)) {

                    //check accountant
                    if (admin.checkAccountant(acc)) {

                        //check if student has already been assigned..
                        if (!admin.checkIfAssigned(stud_id)) {

                            //all is good...insert
                            if (admin.assignSponsor(stud_id, sponsor, acc, social_worker)) {
                                out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Student assigned successfully</div>");
                            } else {
                                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in assigning sponsor to student</div>");
                            }
                        } else {
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>This student has already been assigned a Sponsor</div>");
                        }
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Accountant cannot be verified</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>The Social Worker cannot be verified</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>The Sponsor id cannot be verified</div>");
            }

        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Field(s) cannot be empty</div>");
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
