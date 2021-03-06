/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.FeesModel;
import Model.SchoolsModel;
import Model.SponosorModel;
import Model.SponsorCommitsModel;
import Model.SponsorPaymentsModel;
import Model.StudentAllocationModel;
import Model.StudentPersonalModel;
import Model.StudentSponsorModel;
import Model.SuggestedSchoolsModel;
import Object.DataTableObject;
import Object.SchoolFeesObject;
import Object.SchoolRegisteredObject;
import Object.SponsorCommitsObject;
import Object.SponsorPaymentObject;
import Object.StudentAllocationObject;
import Object.StudentPersonalObjects;
import Object.StudentSponsorObject;
import Object.SuggestedSchoolObject;
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

        } else if (action.equals("regStuds")) {
            List<StudentPersonalModel> list = dataDao.getAllStudents();

            StudentPersonalObjects dataTableObject = new StudentPersonalObjects();
            dataTableObject.setAaData(list);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);

        } else if (action.equals("studSponsor")) {
            List<StudentSponsorModel> list = dataDao.getStudentAss();

            StudentSponsorObject dataTableObject = new StudentSponsorObject();
            dataTableObject.setAaData(list);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);
        } else if (action.equals("allocation")) {

            List<StudentAllocationModel> list = dataDao.getAllocations();
            StudentAllocationObject dataTableObject = new StudentAllocationObject();
            dataTableObject.setAaData(list);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);
        } else if (action.equals("regSchool")) {

            List<SchoolsModel> list = dataDao.getRegSchools();
            SchoolRegisteredObject dataTableObject = new SchoolRegisteredObject();
            dataTableObject.setAaData(list);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);

        } else if (action.equals("fees")) {
            List<FeesModel> list = dataDao.getSchoolFees();
            SchoolFeesObject dataTableObject = new SchoolFeesObject();
            dataTableObject.setAaData(list);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataTableObject);
            out.print(json);
        } else if (action.equals("suggested")) {
            List<SuggestedSchoolsModel> list = dataDao.getSuggestedSchools();
            SuggestedSchoolObject dataTableObject = new SuggestedSchoolObject();
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
