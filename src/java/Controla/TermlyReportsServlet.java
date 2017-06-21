/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toni
 */
public class TermlyReportsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/plain");
        PrintWriter out = response.getWriter();

        String stud_id = request.getParameter("stud_id");
        String term = request.getParameter("term");
        Connection conn = DBUtils.DBUtil.getConnection();
        if (!term.isEmpty() && !stud_id.isEmpty()) {

            String query = "SELECT * FROM SocialVisits WHERE stud_id = ? AND term = ?";
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement(query);
                pst.setString(1, stud_id);
                pst.setString(2, term);

                ResultSet rs = pst.executeQuery();

                if (!rs.isBeforeFirst()) {

                    out.println("<div class='alert alert-danger'>Student assesment report has not been filed</div>");

                } else {

                    if (rs.next()) {
                        //   out.print("<div class='row'> <img src='ImageServlet?id=" + name + "' class='circle' alt='' style='width: 170px; height: 170px; margin-left:36%;'/> </di>");

                        out.print("<div class='card'>");
                        out.print("<div class='card-header alert'> <span class='new badge cyan left' data-badge-caption='Students family background Condition'></span> </div>");
                        out.print("<div class='card-content'>" + rs.getString("background") + "</div>");
                        out.print("</div>");

                        out.print("<div class='card'>");
                        out.print("<div class='card-header alert'><span class='new badge cyan left' data-badge-caption='Current family composition and/or household membership'></span></div>");
                        out.print("<div class='card-content'>" + rs.getString("composition") + "</div>");
                        out.print("</div>");

                        out.print("<div class='card'>");
                        out.print("<div class='card-header alert'><span class='new badge cyan left' data-badge-caption='Ethnicity, religion, and spirituality '></span></div>");
                        out.print("<div class='card-content'>" + rs.getString("ethnicity") + "</div>");
                        out.print("</div>");

                        out.print("<div class='card'>");
                        out.print("<div class='card-header alert'><span class='new badge cyan left' data-badge-caption='Physical functioning, health concerns, illness, disabilities, medications'></span><h6></h6></div>");
                        out.print("<div class='card-content'>" + rs.getString("health") + "</div>");
                        out.print("</div>");

                    }
                    out.print("</ul>");

                }

            } catch (SQLException ex) {
                Logger.getLogger(GetStudentSocialReport.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtils.DBUtil.closeConnection(conn);
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
