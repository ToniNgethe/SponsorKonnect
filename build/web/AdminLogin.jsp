<%-- 
    Document   : login
    Created on : Apr 22, 2017, 12:11:43 PM
    Author     : toni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
        %>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login </title>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/custom.js" type="text/javascript"></script>
    </head>
    <body >

        <div class="section center-align" style="margin-top:100dp;">
            <div class="container">

                <div class="row center">
                    <div class="col s5 offset-l4" style="margin-top: 10%;">

                        <h2>SponsorKonnect</h2>
                        
                    </div>  
                </div>

                <div class="row center">
                    <div class="col s5 offset-l4">
                        <div class="card-panel" >
                            <form action="./AdminLoginServ" method="POST" >
                                <div class="card-content black-text">
                                    <span class="card-title"><h3>Admin Login</h3></span>

                                    <div><%= msg%></div>

                                    <div class="col s12 offset-l1">

                                        <div class="row ">
                                            <div class="input-field col s10">
                                                <i class="material-icons prefix">account_circle</i>
                                                <input id="email" type="email" required="true" class="validate" name="admin_email">
                                                <label for="email" data-error="Wrong email format">Email</label>
                                            </div>
                                        </div>
                                        <div class="row center">
                                            <div class="input-field col s10">
                                                <i class="material-icons prefix">lock</i>
                                                <input id="pass" type="password" required="true"class="validate" name="admin_pass">
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
