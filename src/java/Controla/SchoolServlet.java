/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.FeesModel;
import Model.SchoolsModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import dao.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toni
 */
public class SchoolServlet extends HttpServlet {

    private Admin admin;

    public SchoolServlet() {
        admin = new Admin();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if (action.equals("add")) {

            //school params......
            String school_name = request.getParameter("school_name");
            String school_mode = request.getParameter("school_mode");
            String school_means = request.getParameter("school_means");

            if (!school_name.isEmpty()) {
                response.setContentType("text/plain");
                //check for selections
                if (!school_mode.equals("Choose Institution Mode")) {

                    if (!school_means.equals("Choose Institution type")) {

                        //check for existance..
                        if (!admin.checkSkull(school_name)) {

                            //everything good....proceed with installation
                            if (admin.addSkull(school_name, school_mode, school_means)) {

                                out.print("  <div id='err' class='alert alert-success' role='alert' style='margin:-4%;' >School Added successfully</div>");

                            } else {
                                out.print("Error in registering school");
                            }
                        } else {
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:-4%;' >School with that name already exists in our database</div>");
                        }
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:-4%;' >Please choose institution-type</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin:-4%;' >Please choose institution-mode</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Fields cannot be empty</div>");
            }
        } else if (action.equals("fees")) {

            response.setContentType("text/plain");
            //fee parameters
            String school_name = request.getParameter("selected_school");
            String first_term = request.getParameter("first_term_fees");
            String second_term = request.getParameter("second_term_fees");
            String third_term = request.getParameter("second_term_fees");

            if (!school_name.isEmpty() && !first_term.isEmpty() && !second_term.isEmpty() && !third_term.isEmpty()) {

                //check if stracture exists or not
                if (!admin.checkFeeStracture(school_name)) {

                    //add
                    if (admin.addFeeStracture(school_name, first_term, second_term, third_term)) {
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-4%;' >Fees stracture added successfully</div>");
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Error in adding fees stracture</div>");
                    }

                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Selected School Fees has already been added</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Fields cannot be empty</div>");
            }

        } else if (action.equals("all")) {

            response.setContentType("text/json");
            List<SchoolsModel> list = admin.allSkuls();

            Gson gson = new Gson();
            JsonElement elemet = gson.toJsonTree(list, new TypeToken<List<SchoolsModel>>() {
            }
                    .getType());

            JsonArray jsonArray = elemet.getAsJsonArray();
            response.getWriter().print(jsonArray);

        } else if (action.equals("feeStracture")) {

            response.setContentType("text/json");
            List<FeesModel> list = admin.allSkulFees();

            Gson gson = new Gson();
            JsonElement elemet = gson.toJsonTree(list, new TypeToken<List<FeesModel>>() {
            }.getType());

            JsonArray jsonArray = elemet.getAsJsonArray();
            response.getWriter().print(jsonArray);

        } else if (action.equals("editFee")) {

            response.setContentType("text/plain");
            
            String id = request.getParameter("selected_school_id");
            String first = request.getParameter("selected_first_term_fees");
            String second = request.getParameter("selected_second_term_fees");
            String third = request.getParameter("selected_third_term_fees");

            if (!id.isEmpty()) {

                if (!first.isEmpty() && !second.isEmpty() && !third.isEmpty()) {
                    
                    if (admin.upDateFees(id, first, second, third)) {
                        
                        out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-4%;' >Fees stracture adjusted successfully</div>");
                        
                    }else{
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Error in adjusting Fees stracture</div>");
                    }
                    
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Fields cannot be empty</div>");
                }

            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-4%;' >Oops..unable to get school id</div>");
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
