/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import dao.SocialWorker;
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
public class SocialWorkerServlet extends HttpServlet {

    private SocialWorker socialWorker;

    public SocialWorkerServlet() {
        socialWorker = new SocialWorker();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();

        if (action.equals("new")) {
            //new report

            String stud_id = request.getParameter("selected_stud_id");
            String social = request.getParameter("social_worker");
            String date = request.getParameter("dateOfVisit");
            String fam_bg = request.getParameter("students_family_bg");
            String current_fam = request.getParameter("current_compo");
            String etnicity = request.getParameter("student_ethnicity");
            String health = request.getParameter("student_health");

            if (!stud_id.isEmpty()) {

                if (!social.isEmpty()) {

                    if (!date.isEmpty() && !fam_bg.isEmpty() && !current_fam.isEmpty() && !etnicity.isEmpty() && !health.isEmpty()) {

                        //check if report already made this month;
                        if (!socialWorker.checkReport(stud_id)) {
                            //  public boolean fileReport(String stud_id, String social, String bg, String composition, String ethnicity, String health){
                            //add
                            if (socialWorker.fileReport(stud_id, social, fam_bg, current_fam, etnicity, health, date)) {
                                out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Report submited successfully</div>");
                            } else {
                                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in submiting assessment report</div>");
                            }
                        } else {
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Report Already filled for this student this month</div>");
                        }

                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>File all report sections</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sorry!! Unable to determine your acc</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sorry!! Unable to determine sudent id</div>");
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
