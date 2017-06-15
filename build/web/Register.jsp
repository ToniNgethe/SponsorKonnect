<%-- 
    Document   : login
    Created on : Apr 22, 2017, 12:11:43 PM
    Author     : toni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Registration </title>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
    </head>
    <body class="bg">


        <%
            String msg = (String) request.getAttribute("message");
            if (msg == null) {
                msg = "";
            }
        %>

        <nav style="background-color: white;">
            <div style class="nav-wrapper">
                <a href="http://localhost:8084/SponsorKonnect-master/" style="color: black;" class="brand-logo">SponsorKonnect</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a style="color: black;" href="login.jsp">Login?</a></li>
                </ul>
            </div>
        </nav>

        <div class="section">
            <div class="container">
                <div class="row center">
                    <div class="col s5 offset-l4">
                        <div class="card-panel" style="margin-top: 20%;">
                            <form action="./StudentRegister" method="POST" enctype = "multipart/form-data">
                                <div class="card-content black-text">
                                    <!--                                    <span class="card-title">Sign up with us</span>-->

                                    <div><%= msg%></div>


                                    <div class="col s12 offset-l1">
                                        <div class="row">
                                            <img id="profile_pic" src="img/unnamed.png" alt="" class="circle" style="height: 150px;width: 150px; margin-right: 17%;"/>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s10">

                                                <input name="profile_pic" onchange="document.getElementById('profile_pic').src = window.URL.createObjectURL(this.files[0])" type="file" accept="image/jpeg, image/png">

                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="input-field col s10">
                                                <input id="email" type="email" required="true" class="validate" name="stud_email">
                                                <label data-erro="Wrong email format" for="email">Email</label>
                                            </div>
                                        </div>
                                        <div class="row center">
                                            <div class="input-field col s10">
                                                <input id="pass" type="password" class="validate" name="stud_pass">
                                                <label for="pass">Password</label>
                                            </div>
                                        </div>
                                        <div class="row center">
                                            <div class="input-field col s10">
                                                <input id="passc" type="password" class="validate" name="stud_confirm">
                                                <label for="passc">Confirm password</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-action">

                                    <input class="btn waves-effect waves-light" type="submit" value="Register"/>

                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
