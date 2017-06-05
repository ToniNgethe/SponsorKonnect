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

                    <li><a style="color: black;" href="Register.jsp">back</a></li>
                </ul>
            </div>
        </nav>

        <div class="section">
            <div class="container">
                <div class="row center">
                    <div class="col s12">
                        <div class="alert alert-info" style="margin-top: 5%;">
                            <h5>Give us your details and will get back to you</h5>
                        </div>
                        <div class="card">
                            <div class="card-title">
                                <img src="img/sponsor_logo.png" alt="" style="margin-bottom: -98px; width: 295px; margin-top: -58px;"/>
                            </div>
                            <div class="card-content">

                                <form id="new_sponsor_info" >
                                    <div id="new_sponsor_fb"></div>

                                    <div class="col s12">
                                        <div class="row">
                                            <div class="col s6">
                                                <div class="">
                                                    <div class="" style="margin: 5%;">
                                                        <div class="row">
                                                            <script>
                                                                $(document).ready(function () {

                                                                    document.getElementById('new_sponsor_mobile').addEventListener('input', function (e) {
                                                                        var x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,3})(\d{0,4})/);
                                                                        e.target.value = !x[2] ? x[1] : '(' + x[1] + ') ' + x[2] + (x[3] ? '-' + x[3] : '');
                                                                    });


                                                                    $("#btn_add_appSponsor").click(function () {
                                                                        // alert('dsfsdff');
                                                                  
                                                                        $.ajax({
                                                                            type: 'POST',
                                                                            data: $("#new_sponsor_info").serialize(),
                                                                            url: "NewSponsorApplication",
                                                                            success: function (result) {
                                                                                swal(
                                                                                        'Server Feedback',
                                                                                        result,
                                                                                        'info'
                                                                                        );
                                                                            },
                                                                            error: function (result) {
                                                                                swal(
                                                                                        'Oops...',
                                                                                        result,
                                                                                        'error'
                                                                                        );
                                                                            }

                                                                        });
                                                                    });

                                                                });
                                                            </script>
                                                            <div class="input-field col s12">
                                                                <input required="true" name="new_sponsor_name" id="sponsor_name" type="text" class="validate">
                                                                <label for="sponsor_name">Your Names</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input required="true" name="new_sponsor_mobile" id="new_sponsor_mobile" type="text" class="validate" placeholder="(072) 999-9999">
                                                                <label data-error="Not a number" for="sponsor_number">Your Mobile Number</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input required="true" name="new_sponsor_email" id="sponsor_email" type="email" class="validate">
                                                                <label data-error="Invalid Email" for="sponsor_email">Your Email</label>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col s6" >
                                                <div style="margin: 5%;">
                                                    <div class="row" >                                 
                                                        <div class="input-field col s12">
                                                            <select name="new_sponsor_means" id="new_sponsor_means">
                                                                <option value="" disabled selected>Choose your communication means</option>
                                                                <option value="email">Email</option>
                                                                <option value="phonenumber">Phone Number</option>
                                                                <option value="skype">Skype</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                                <select name="new_sponsor_type" id="new_sponsor_type">
                                                                <option value="" disabled selected>Type of Sponsorship</option>
                                                                <option value="primary">Primary</option>
                                                                <option value="secondary">Secondary</option>
                                                                <option value="vocational">Vocational</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input required="true" name="new_sponsor_company" id="sponsor_company" type="text" class="validate">
                                                            <label  for="sponsor_company">Your Company</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="row ">
                                        <div class="col s6 offset-l4">
                                            <button id="btn_add_appSponsor" style="width: 50%;" class="btn waves-effect waves-light btn-large" type="button" name="action">Submit
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
