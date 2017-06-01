<%@page import="DBUtils.DBUtil"%>
<%@ page import="java.sql.*" %>  
<%
    //get todays date
    java.util.Date today = new java.util.Date();
    java.sql.Date t = new java.sql.Date(today.getTime());
    String name = request.getParameter("val");
    
    if (name == null || name.trim().equals("")) {
        out.print("<div class='alert alert-info'>Please enter Social Worker Email or Number</div>");
    } else {

        Connection conn = DBUtil.getConnection();
        String query = "SELECT * FROM social_worker WHERE email = ? OR number = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, name);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {

                out.println("<div class='alert alert-danger'>No Social worker with that email or Number Found!</div>");

            } else {
                out.print("<div class='card'>");
                out.print("<div class='card-content'>");
                out.print("<table class='table striped' border='1' cellpadding='2' width='100%'>");
                out.print("<tr><th>Name</th><th>Email</th><th>Number</th><th>Location</th></tr>");
                while (rs.next()) {
                    out.print("<tr><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>"+rs.getString(5) +"</td></tr");
                }
                out.print("</table>");
                out.print("</div>");
                out.print("</div>");
            }

        } catch (SQLException ex) {
            out.print(ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

    }//end of else  
%>  