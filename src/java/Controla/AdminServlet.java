/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.WorkerModel;
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
public class AdminServlet extends HttpServlet {

    private Admin admin;

    public AdminServlet() {
        admin = new Admin();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        String action = request.getParameter("add");

        String name = request.getParameter("name");
        String mobile = request.getParameter("number");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String pass = request.getParameter("password");

        if (!action.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !location.isEmpty() && !pass.isEmpty()) {

            WorkerModel wm = new WorkerModel(name, mobile, email, location, pass);

            //social worker
            if (action.equals("social")) {

                //check if worker exists
                if (!admin.checkWorker("social_worker", wm)) {

                    if (admin.addWorker("social_worker", wm)) {
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Social worker added sucessfully</div>");
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error inserting Social Worker</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Social Worker with that name and email exists</div>");
                }

            } else if (action.equals("acct")) {

                //check acct if he/she exits
                if (!admin.checkWorker("accountant", wm)) {
                    if (admin.addWorker("accountant", wm)) {
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Accountant added sucessfully</div>");
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error inserting Accountant</div>");
                    }
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Accountant with that name and email exists</div>");
                }

            } else if (action.equals("admin")) {

                //check admin if he/she exits
                if (!admin.checkWorker("admin", wm)) {
                    if (admin.addWorker("admin", wm)) {
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Admin added sucessfully</div>");
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error inserting Admin</div>");
                    }
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Admin with that name and email exists</div>");
                }

            } else if (action.equals("saveAdmin")) {

                //save admin changes
                if (id != null) {

                    if (admin.saveWorker(id, "admin", wm)) {
                        out.print("  <div  class='alert alert-success'>Admin changes sucessfully made. Close & Open to note chnages</div>");
                    } else {
                        out.print("  <div class='alert alert-danger' role='alert'>Error Saving Admin changes</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Unable to get Admin id</div>");
                }

            } else if (action.equals("saveAcc")) {

                //save accs changes
                if (id != null) {

                    if (admin.saveWorker(id, "accountant", wm)) {
                        out.print("  <div  class='alert alert-success'>Accountant changes sucessfully made. Close & Open to note chnages</div>");
                    } else {
                        out.print("  <div class='alert alert-danger' role='alert'>Error Saving Accountant changes</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Unable to get Admin id</div>");
                }

            } //save social wporker chnages
            else if (action.equals("saveSoc")) {
                if (id != null) {

                    if (admin.saveWorker(id, "social_worker", wm)) {
                        out.print("  <div  class='alert alert-success'>Social Worker changes sucessfully made. Close & Open to note chnages</div>");
                    } else {
                        out.print("  <div class='alert alert-success' role='alert'>Error Saving Social Worker changes</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Unable to get Admin id</div>");
                }
            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Action not found</div>");
            }

        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Field(s) Empty</div>");
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
