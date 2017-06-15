<%@page import="DBUtils.DBUtil"%>
<%@ page import="java.sql.*" %>  
<%
    //get todays date
    java.util.Date today = new java.util.Date();
    java.sql.Date t = new java.sql.Date(today.getTime());
    String name = request.getParameter("val");
    if (name == null || name.trim().equals("")) {
        out.print("<div class='alert alert-info'>Please enter Student id!</div>");
    } else {

        Connection conn = DBUtil.getConnection();
        String query = "SELECT * FROM student_allocation WHERE stud_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {

                out.println("<div class='alert alert-danger'>This student has not been allocated any funds</div>");

            } else {

                if (rs.next()) {
                    
                    out.print("<div class='row'> <img src='ImageServlet?id="+name+"' class='circle' alt='' style='width: 170px; height: 170px; margin-left:36%;'/> </di>");
                    
                    out.print("<div class='row'><div class='card'>"
                            + "<div class='card-header alert grey  white-text'>"
                            + "<h6>AMOUNT ALLOCATED TO SCHOOL</h6>"
                            + "</div>"
                            + "<div class='card-content'> Ksh. "
                            + rs.getDouble(3)
                            + "</div>"
                            + "</div></div>");

                    out.print("<div class='row '><div class='card'>"
                            + "<div class='card-header alert grey  white-text'>"
                            + "<h6>AMOUNT ALLOCATED TO UPKEEP</h6>"
                            + "</div>"
                            + "<div class='card-content'> Ksh. "
                            + rs.getDouble(4)
                            + "</div>"
                            + "</div></div>");

                    out.print("<div class='row'><div class='card'>"
                            + "<div class='card-header alert grey  white-text'>"
                            + "<h6>AMOUNT ALLOCATED TO OTHERS</h6>"
                            + "</div>"
                            + "<div class='card-content'> Ksh. "
                            + rs.getDouble(5)
                            + "</div>"
                            + "</div></div>");
                }

            }

        } catch (SQLException ex) {
            out.print(ex);
        } finally {
            DBUtil.closeConnection(conn);
        }

    }//end of else  
%>  