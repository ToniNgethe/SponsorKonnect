/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import dao.Accountant;
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
public class AccStudentAllocation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        String student_id = request.getParameter("id");
        String sponsor = request.getParameter("sponsor");
        String amount = request.getParameter("amount_school");
        String upkeep = request.getParameter("amount_upkeep");
        String others = request.getParameter("amount_others");
        Accountant a = new Accountant();
        if (!student_id.isEmpty()) {
            if (!sponsor.isEmpty()) {

                if (!amount.isEmpty() && !upkeep.isEmpty() && !others.isEmpty()) {

                    //check if added for this term
                    if (!a.isAllocated(student_id)) {

                        if (a.allocateStudents(student_id, amount, upkeep, others, sponsor)) {
                            out.print("  <div id='err' class='alert alert-success' role='alert' style='margin:4%;' >Student succesfully allocated</div>");
                        } else {
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to get allocation details</div>");
                        }

                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Looks like this student has already been allocated</div>");
                         out.print("  <div id='err' class='alert alert-info' role='alert' style='margin:4%;' >Make additional payment?<a   onclick='return allocateAdditional();'> HERE </a></div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to get allocation details</div>");
                }
            }else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to determine Sponsor id</div>");
            }
        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to determine Student id</div>");
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
