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
public class AccStudentAdditional extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String stud_id = request.getParameter("id");
        String fees = request.getParameter("fees");
        String sponsor = request.getParameter("sponsor");
        String school = request.getParameter("add_school");
        String upkeep = request.getParameter("add_upkeep");
        String other = request.getParameter("add_other");

        Accountant ac = new Accountant();

        if (!stud_id.isEmpty()) {
            if (!sponsor.isEmpty()) {
                if (!fees.isEmpty()) {

                    //check school fees is fully paid.....
                    System.out.println("FEESSS : " + fees);

                    if (ac.additionalAllocation(stud_id, school, upkeep, other, sponsor)) {
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin:4%;' >Additional Allocaton successfully made</div>");
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Error in making additional payments</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:4%;' >Unable to determine School fees</div>");
                }

            } else {
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
