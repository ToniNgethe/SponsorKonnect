<%@page import="DBUtils.DBUtil"%>
<%@ page import="java.sql.*" %>  
<%

    String name = request.getParameter("val");
    if (name == null || name.trim().equals("")) {
        out.print("<div class='alert alert-info'>Please enter Student iD!</div>");
    } else {

        Connection conn = DBUtil.getConnection();
        String query = "SELECT * FROM SocialVisits WHERE stud_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {

                out.println("<div class='alert alert-danger'>Student assesment report has not been filed</div>");

            } else {

                if (rs.next()) {

                    out.print("<div class='card'>");
                    out.print("<div class='card-header alert teal white-text'><h6>Date Submitted</h6></div>");
                    out.print("<div class='card-content'>" + rs.getString("date") + "</div>");
                    out.print("</div>");

                    out.print("<div class='card'>");
                    out.print("<div class='card-header alert teal white-text'><h6>Students family background Condition</h6></div>");
                    out.print("<div class='card-content'>" + rs.getString("background") + "</div>");
                    out.print("</div>");

                    out.print("<div class='card'>");
                    out.print("<div class='card-header alert teal white-text'><h6>Current family composition and/or household membership</h6></div>");
                    out.print("<div class='card-content'>" + rs.getString("composition") + "</div>");
                    out.print("</div>");

                    out.print("<div class='card'>");
                    out.print("<div class='card-header alert teal white-text'><h6>Ethnicity, religion, and spirituality </h6></div>");
                    out.print("<div class='card-content'>" + rs.getString("ethnicity") + "</div>");
                    out.print("</div>");

                    out.print("<div class='card'>");
                    out.print("<div class='card-header alert teal white-text'><h6>Physical functioning, health concerns, illness, disabilities, medications</h6></div>");
                    out.print("<div class='card-content'>" + rs.getString("health") + "</div>");
                    out.print("</div>");

                }
                out.print("</ul>");

            }

        } catch (SQLException ex) {
            out.print(ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

    }//end of else  

%>  