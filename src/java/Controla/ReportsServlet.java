/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.SponosorModel;
import Model.SponsorCommitsModel;
import Model.SponsorPaymentsModel;
import Object.DataTableObject;
import Object.SponsorCommitsObject;
import Object.SponsorPaymentObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Reports;
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
public class ReportsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Reports dataDao = new Reports();

        PrintWriter out = response.getWriter();
        if (action.equals("sponsors")) {

            List<SponosorModel> list = dataDao.allSponors();

            DataTableObject dataTableObject = new DataTableObject();
            dataTableObject.setAaData(list);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);

        } else if (action.equals("commits")) {

            List<SponsorCommitsModel> list = dataDao.getCommits();

            SponsorCommitsObject dataTableObject = new SponsorCommitsObject();
            dataTableObject.setAaData(list);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);

        } else if (action.equals("pay")) {
            List<SponsorPaymentsModel> list = dataDao.getPayments();

            SponsorPaymentObject dataTableObject = new SponsorPaymentObject();
            dataTableObject.setAaData(list);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);

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
