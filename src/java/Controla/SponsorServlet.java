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
public class SponsorServlet extends HttpServlet {

    private Admin admin;

    public SponsorServlet() {
        admin = new Admin();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        //saving sponsor info
        String action = request.getParameter("action");
        String number = request.getParameter("new_sponsor_number");
        String name = request.getParameter("new_sponsor_name");
        String mobile = request.getParameter("new_sponsor_mobile");
        String email = request.getParameter("new_sponsor_email");
        String comm_means = request.getParameter("sponsormeans");
        String type = request.getParameter("sponsotype");
        String company = request.getParameter("new_sponsor_company");
        String pas = request.getParameter("new_sponsor_pass");

        //making commits params
        String id = request.getParameter("name");
        String amount = request.getParameter("sponsor_amount");

        //repaying commits params
        String sponsor_id = request.getParameter("sponsor_comm_number");
        String amount_paid = request.getParameter("sponsor_amount_paid");

        //making pays
        String sponsor_pay_id = request.getParameter("make_sponsor_payments_id");
        String payment_made = request.getParameter("make_sponsorpay_amount");

        //check action
        if (action.equals("commits")) {

            //check if fields are empty
            if (!number.isEmpty() && !name.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !company.isEmpty() && !comm_means.isEmpty() && !type.isEmpty() && !pas.isEmpty()) {

                if (!comm_means.equals("Choose your communication means")) {
                    if (!type.equals("Type of Sponsorship")) {

                        //check if sponsor id exists
                        if (!admin.checkSponsorId(number)) {

                            //sponsor does not exist..so add him/her
                            SponosorModel sp = new SponosorModel(number, name, mobile, email, comm_means, type, company, pas);
                            if (admin.addSponsor(sp)) {

                                //was a success
                                out.print("  <div id='err' class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sponsor information stored successfully</div>");

                            } else {
                                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in saving sponsors information</div>");
                            }

                        } else {

                            //sponsor exists
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sorry!! Sponsor with that id exists</div>");

                        }
                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Choose sponsorship type</div>");
                    }
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Choose sponsor communication means</div>");
                }
            } else {
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Field(s) cannot be empty</div>");
            }
        } else if (action.equals("saveCommits")) {

            //check i empty
            if (!id.isEmpty()) {
                //check if sponsor id is valid
                if (admin.checkSponsorId(id)) {
                    //exists....soo add commits
                    if (admin.saveCommits(id, amount)) {

                        //successfully saved
                        out.print("  <div class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Commit added successfully</div>");

                    } else {
                        out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in saving commit</div>");
                    }

                } else {
                    //sponsor id not found
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sponsor Id not found</div>");
                }
            } else {
                out.print("  <div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Enter Sponsor Id</div>");
            }
        } else if (action.equals("payments")) {

            String bank_name = request.getParameter("sponsor_bank");
            String bank_slip = request.getParameter("sponsor_bank_slip");
            //check if sponsor id exists
            if (admin.checkSponsorId(sponsor_id)) {

                //check if amount paid is not above comited
                if (admin.checkAmount(sponsor_id, amount_paid)) {

                    if (admin.checkTotalPayment(sponsor_id, amount_paid)) {
                        //record payment
                        if (admin.savePayment(sponsor_id, amount_paid, 0, bank_name, bank_slip)) {

                            //successfully saved
                            out.print("  <div class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Payment made successfully</div>");

                        } else {

                            //error in saving..
                            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in making payments</div>");
                        }

                    } else {
                        out.print("  <div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Commitments for this sponsor already fully paid</div>");
                    }
                } else {
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Amount enetered is greater than commited. Consider entering payment instead </div>");
                }

            } else {
                //sponsor id not found
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sponsor Id not found</div>");
            }

        } else if (action.equals("pay")) {

            String bank_name = request.getParameter("make_sponsor_bank");
            String bank_slip = request.getParameter("make_ponsor_bank_slip");
            //check sponsor id
            if (admin.checkSponsorId(sponsor_pay_id)) {

                if (admin.savePayment(sponsor_pay_id, payment_made, 1,bank_name, bank_slip)) {
                    //successfully saved
                    out.print("  <div class='alert alert-success' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Payment made successfully</div>");
                } else {
                    //error in saving..
                    out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Error in saving payments</div>");
                }

            } else {
                //sponsor id not found
                out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sponsor Id not found</div>");
            }
        } else {
            out.print("  <div id='err' class='alert alert-danger' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;'>Sorry, no action not found</div>");
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
