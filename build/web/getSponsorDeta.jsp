<%@page import="DBUtils.DBUtil"%>
<%@ page import="java.sql.*" %>  
<%
    //get todays date
    java.util.Date today = new java.util.Date();
    java.sql.Date t = new java.sql.Date(today.getTime());
    String name = request.getParameter("val");
    if (name == null || name.trim().equals("")) {
        out.print("<div class='alert alert-info'>Please enter Sponsor id!</div>");
    } else {

        Connection conn = DBUtil.getConnection();
        String query = "SELECT * FROM Sponsor WHERE sponsor_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {

                out.println("<div class='alert alert-danger'>No Sponsor with that id Found!</div>");

            } else {
                out.print("<table class='table striped' border='1' cellpadding='2' width='100%'>");
                out.print("<tr><th>Name</th><th>Email</th><th>Type</th></tr>");
                while (rs.next()) {
                    out.print("<tr><td>" + rs.getString(3) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(7) + "</td></tr");
                }
                out.print("</table>");

            }

        } catch (SQLException ex) {
            out.print(ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

    }//end of else  
%>  