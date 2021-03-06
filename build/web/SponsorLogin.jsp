<%-- 
    Document   : login
    Created on : Apr 22, 2017, 12:11:43 PM
    Author     : toni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>



        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <link href="css/demo_table.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <link href="css/sponsor.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/sweetalert2.js" type="text/javascript"></script>
        <script src="js/custom.js" type="text/javascript"></script>


    </head>
    <body class="sp">
        <nav style="background-color: white;">
            <div style class="nav-wrapper">
                <a href="http://localhost:8084/SponsorKonnect-master/" style="color: black;" class="brand-logo">SponsorKonnect</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                </ul>
            </div>
        </nav>

        <div class="section">
            <div class="container">
                <div class="row center">
                    <div class="col s5 offset-l4">
                        <div class="card-panel " style="margin-top: 20%;">
                            <form action="./SponsorLoginServ" method="POST" >
                                <div class="card-content black-text">
                                    <span class="card-title"><h4 class="blue-text">Sponsor Login</h4></span>
                                    <%
                                        String msg = (String) request.getAttribute("msg");
                                        if (msg == null || msg == "") {
                                            msg = "";
                                        }
                                    %>
                                    <div id="login_bg"><%= msg%></div>

                                    <div class="col s12 offset-l1" style="margin-top: 6%;">

                                        <div class="row ">
                                            <div class="input-field col s10">
                                                <i class="material-icons prefix">account_circle</i>
                                                <input id="sponsor_id" type="text" required="true" class="validate" name="sponsor_id">
                                                <label for="sponsor_id" >Enter id</label>
                                            </div>
                                        </div>
                                        <div class="row center">
                                            <div class="input-field col s10">
                                                <i class="material-icons prefix">lock</i>
                                                <input id="pass" type="password" required="true"class="validate" name="sponsor_pass">
                                                <label for="pass">Password</label>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                                <div class="card-action">

                                    <div class="row">
                                        <input class="btn waves-effect waves-light" type="submit" value="Submit"/>
                                    </div>
                                    <!-- Modal Trigger -->
                                    <div class="row">
                                        <a class="waves-effect waves-light " href="#modal1">Forgot password?</a>
                                    </div>


                                </div>
                            </form>

                        </div>
                    </div>

                </div>

                <!-- Modal Structure -->
                <div id="modal1" class="modal">
                    <div class="modal-content">

                        <div class="input-field col s6 offset-l3">

                            <input id="email" type="email" required="true" class="validate" name="student_email_reset">
                            <label for="email" data-error="Wrong email format">Enter email to send password</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Reset</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
