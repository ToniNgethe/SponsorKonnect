<%-- 
    Document   : StudentPaAfterReg
    Created on : Apr 25, 2017, 5:12:12 AM
    Author     : toni
--%>

<%@page import="Model.StudentModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/picker.date.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Panel</title>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            StudentModel studentModel = null;

            if (session.getAttribute("STUDENT_MODEL") == null || session.getAttribute("STUDENT_MODEL") == "") {
                response.sendRedirect("login.jsp");
            } else {
                studentModel = (StudentModel) session.getAttribute("STUDENT_MODEL");
            }


        %>
        <!-- Dropdown Structure -->

        <nav>
            <div class="nav-wrapper">
                <a href="#!" class="brand-logo">SponsorKonnect</a>
                <ul class="right hide-on-med-and-down">
                    <li><a class="dropdown-button" href="./StudentLogout">Log Out</a></li>
                </ul>
            </div>
        </nav>

        <!--                logout-->
        <ul id="dropdown1" class="dropdown-content">
            <li><a href="#!">Log out</a></li>
        </ul>

        <div class="container">
            <div class="section">
                <div class="row">
                    <div class="row">
                        <div class="col s12">
                            <div class="card-panel white">
                                <span class="black-text">I am a very simple card. I am good at containing small bits of information.
                                    I am convenient because I require little markup to use effectively. I am similar to what is called a panel in other frameworks.
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12">
                        <div class="col s6">
                            <div class="card blue-grey large darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">PERSONAL DETAILS</span>
                                    <p>All details have been saved.</p>
                                    
                                </div>
                                <div class="card-action">
                                    <a href="#">View</a>
                                    <a href="#">Edit</a>
                                </div>
                            </div>
                        </div>

                        <div class="col s6">

                            <div class="card blue large darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">Application Progress</span>
                                    <p>Your details have been submitted to respective Sponsors. Awaiting response</p>
                                </div>
                                <div class="card-action">
                                    <a href="#">This is a link</a>
                                    <a href="#">This is a link</a>
                                </div>
                            </div>

                        </div>

                 

                    </div>

                </div>
            </div>     
        </div>

    </body>
</html>
