/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.SocialWorkerMOdel;
import Model.SponosorModel;
import Model.WorkerModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import dao.Student;
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
public class StudentPanelServlets extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        Student s = new Student();
        if (action.equals("allocations")) {
            if (!id.isEmpty()) {
                response.setContentType("text/plain");

                int a = s.checkAllocation(id);
                if (a == 1) {
                    out.print("true");
                } else {
                    out.print("<div class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>You have not been assigned any sponsor. Check in later</div>");
                }

            } else {
                out.print("  <div class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Unable to determine your id</div>");
            }

        } else if (action.equals("sponsor")) {

            List<SponosorModel> sponosorModel = s.getAllocatedSponsor(id);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(sponosorModel, new TypeToken<List<SponosorModel>>() {
            }.getType());

            JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);

        } else if (action.equals("social")) {

            List<SocialWorkerMOdel> sponosorModel = s.getAllocatedSocialWorker(id);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(sponosorModel, new TypeToken<List<SocialWorkerMOdel>>() {
            }.getType());

            JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);

        }else if(action.equals("acc")){
            List<SocialWorkerMOdel> sponosorModel = s.getAllocatedAcc(id);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(sponosorModel, new TypeToken<List<SocialWorkerMOdel>>() {
            }.getType());

            JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
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
