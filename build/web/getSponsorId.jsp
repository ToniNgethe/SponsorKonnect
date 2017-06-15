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
        
        String query_name = "SELECT name FROM Sponsor WHERE sponsor_id = ?";
        String query_payments = "SELECT SUM(amount) FROM sponsor_payments WHERE sponsor_id = ? AND type = 0";
        String query = "SELECT SUM(amount) FROM sponsor_commits WHERE sponsor_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query_name);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            
            if (!rs.isBeforeFirst()) {
                
                out.println("<div class='alert alert-danger'>No Sponsor with that id Found!</div>");
                
            } else {
                out.print("<div class='card'>");
                out.print("<div class='card-content'>");
                out.print("<table class='table striped' border='1' cellpadding='2' width='100%'>");
                out.print("<tr><th>Sponsor Name</th><th>Total Commits</th><th>Total Payments</th><th>Balance</th></tr>");
                if (rs.next()) {
                    String s_name = rs.getString("name");
                    int commits = 0;
                    int payments = 0;
                    int balance = 0;
                    // out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getDouble(3) + "</td><td>" + rs.getDate(4) + "</td></tr>");
                    
                    PreparedStatement pst2 = conn.prepareStatement(query);
                    pst2.setString(1, name);
                    ResultSet rs2 = pst2.executeQuery();
                    
                    if (rs2.next()) {
                        //get total payments
                        commits = rs2.getInt(1);
                    }
                    
                    PreparedStatement pst3 = conn.prepareStatement(query_payments);
                    pst3.setString(1, name);               
                    ResultSet rs3 = pst3.executeQuery();
                    
                    if (rs3.next()) {
                        //get total payments
                        payments = rs3.getInt(1);
                    }
                    
                    balance = payments-commits;
                    out.print("<tr><td>" + s_name + "</td><td>" + commits + "</td><td>" + payments + "</td><td>"+balance+"</td></tr>");
                    
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