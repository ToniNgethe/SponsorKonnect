<%-- 
    Document   : AdminPanel
    Created on : May 1, 2017, 1:08:06 PM
    Author     : toni
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SponsorKonnect || Admin Panel</title>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <link href="css/demo_table.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/sweetalert2.js" type="text/javascript"></script>
        <script src="js/custom.js" type="text/javascript"></script>
        <script src="js/admin.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>

    </head>
    <body>

        <%
            //check for session
            if (session.getAttribute("ADMIN") == null || session.getAttribute("ADMIN") == "") {
                response.sendRedirect("AdminLogin.jsp");
            }
        %>

        <nav class="nav-extended blue">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo"> <img src="img/sponsor_logo.png" style="margin-top: -30%;" alt=""/></a>

                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">

                    <li><a href="./AdminLogOut">Log out</a></li>
                </ul>

            </div>
            <div class="nav-content">
                <ul id="nac" class="tabs tabs-transparent">
                    <li class="tab" ><a data-tab="tab1" class="active" href="#test1"><i class="material-icons left">home</i>Workers Section</a></li>
                    <li class="tab" ><a data-tab="tab2" href="#test2" ><i class="material-icons left">wc</i>Sponsors Section</a></li>
                    <li class="tab" ><a data-tab="tab3" href="#test3"  onclick="return getAllStudents();"><i class="material-icons left">account_box</i>Student Section</a></li>
                    <li class="tab" ><a data-tab="tab4" href="#test4" ><i class="material-icons left">school</i>School Information</a></li>
                </ul>
            </div>
        </nav>

        <div id="test1" class="container">
            <div class="row center alert blue white-text" style="margin-top: 5%;margin-bottom: 5%;">
                <h5 class=""> You can add or views workers</h5>
            </div>
            <div class="row">
                <div class="col s12">
                    <div class="col s4">
                        <div class="card large blue-grey darken-1">
                            <div class="card-image">
                                <img src="img/social.png" alt=""/>
                                <span class="card-title">Social Workers</span>
                            </div>
                            <div class="card-content white-text">

                                <p>Total Social Workers : </p>
                            </div>
                            <div class="card-action">
                                <a class="waves-ripple" href="#addSocialWorker">Add</a>
                                <a class="waves-ripple" href="#socialWorkerTable" onclick="return theFunction();">View</a>
                            </div>


                        </div>
                    </div>
                    <div class="col s4">
                        <div class="card large green darken-1">
                            <div class="card-image">
                                <img src="img/acc.png" alt=""/>
                                <span class="card-title">Accountants</span>
                            </div>
                            <div class="card-content white-text">

                                <p>Total Accountants:</p>
                            </div>
                            <div class="card-action">
                                <a class="waves-ripple" href="#addAccountant">Add</a>
                                <a class="waves-ripple" href="#accWorkerTable" onclick="return getAccs();">View</a>
                            </div>
                        </div>
                    </div>
                    <div class="col s4">
                        <div class="card large red darken-1">
                            <div class="card-image">
                                <img src="img/admin.png" alt=""/>
                                <span class="card-title">Social Workers</span>
                            </div>
                            <div class="card-content white-text">
                                <span class="card-title">Admin :</span>
                                <p>Total Admin :</p>
                            </div>
                            <div class="card-action col s12">
                                <a class="waves-ripple" href="#addAdmin">Add</a>
                                <a class="waves-ripple" href="#adminTableModel" onclick="return getAdmin();">View</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Admin  table-->
        <div id="adminTableModel" class="modal modal-lg white">

            <div class="modal-content">
                <div class="row">
                    <div class="col s12 ">
                        <table  class="table striped highlight"  id="adminTable">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Number</th>
                                    <th>Email</th>
                                    <th>Location</th>
                                    <th>Password</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>

                        </table>

                    </div>
                </div>
            </div>
            <div class=" white">
                <div class="row center">
                    <div class="col s8 offset-l2">
                        <div class="col s4 offset-l3">

                        </div>

                        <div class="col s4">
                            <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
                        </div>

                        <!--                EDIT FORM FOR ADMIN-->
                        <div id="editAdmin" class="row">
                            <div class="row">

                            </div>
                            <form id="edit_admin_table" class="col s12">
                                <div class="row">
                                    <div id="edit_admin_bg"></div>
                                </div>

                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="id" name="id" type="hidden" />
                                        <input id="name" name="name" type="text" class="validate">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="number" name="number" type="number" class="validate">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="email" name="email" type="email" class="validate">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="locationn" name="location" type="text" class="validate">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="passwordd" name="password" type="password" class="validate">
                                    </div>
                                </div>
                                <div class="row">
                                    <button id="admin_edit_btn" class="btn btn-large waves-effect waves-light" type="button" name="action">Save
                                        <i class="material-icons right">save</i>
                                    </button>
                                </div>

                            </form>
                        </div>

                    </div>

                </div>

            </div>
        </div
    </div>

    <!-- Accountant  table-->
    <div id="accWorkerTable" class="modal white">
        <div class="modal-content">
            <div class="row">
                <div class="col s12">
                    <table  class="table striped highlight" id="accountantTable">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Number</th>
                                <th>Email</th>
                                <th>Location</th>
                                <th>Password</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>

                    </table>

                </div>
            </div>
        </div>
        <div class="modal-footer white">
            <div class="row center">
                <div class="col s8 offset-l2">
                    <div class="col s4 offset-l3">

                    </div>

                    <div class="col s4">
                        <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
                    </div>

                    <!--                    EDIT ACCOUNTANTS DETAILS-->

                    <div id="editAccount" class="row">
                        <div class="row">

                        </div>
                        <form id="edit_acc_table" class="col s12">
                            <div class="row">
                                <div id="edit_admin_bg"></div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="acc_id" name="id" type="hidden" />
                                    <input id="acc_name" name="name" type="text" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="acc_number" name="number" type="number" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="acc_email" name="email" type="email" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="acc_locationn" name="location" type="text" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="acc_passwordd" name="password" type="password" class="validate">
                                </div>
                            </div>
                            <div class="row">
                                <button id="acc_edit_btn" class="btn btn-large waves-effect waves-light" type="button" name="action">Save
                                    <i class="material-icons right">save</i>
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Social worker table-->
    <div id="socialWorkerTable" class="modal white">
        <div class="modal-content">
            <div class="row">
                <div class="col s12">
                    <table  class="table striped highlight centered" id="socialFromDb">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Number</th>
                                <th>Email</th>
                                <th>Location</th>
                                <th>Password</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>

                    </table>

                </div>
            </div>
        </div>
        <div class="modal-footer white">
            <div class="row center">
                <div class="col s8 offset-l2">
                    <div class="col s4 offset-l3">

                    </div>

                    <div class="col s4">
                        <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
                    </div>


                    <!--                EDIT FORM FOR Social Worker-->
                    <div id="editSocial" class="row">
                        <div class="row">

                        </div>
                        <form id="edit_social_table" class="col s12">

                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="soc_id" name="id" type="hidden" />
                                    <input id="soci_name" name="name" type="text" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="soc_number" name="number" type="number" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="soc_email" name="email" type="email" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="soc_locationn" name="location" type="text" class="validate">

                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="soc_passwordd" name="password" type="password" class="validate">
                                </div>
                            </div>
                            <div class="row">
                                <button id="soc_edit_btn" class="btn btn-large waves-effect waves-light" type="button" name="action">Save
                                    <i class="material-icons right">save</i>
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div
</div>
<!-- Social worker -->
<div id="addSocialWorker" class="modal  white" style="max-width: 750px;">
    <div class="modal-content">
        <div class="row">
            <div class="col s12 ">

                <form id="socialWorkerData" method="post">
                    <div  id="social_feedb">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="name" id="social_name" type="text" class="validate" required="true">
                            <label for="social_name">Social Worker Name</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="number" id="social_number" type="number" class="validate" required="true">
                            <label data-error="Not a number" for="social_number">Social Worker Number</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" id="social_email" type="email" class="validate" required="true">
                            <label data-error="Wrong email fomart" for="social_email">Social Worker email</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="location" id="social_location" type="text" class="validate" required="true">
                            <label for="social_location">Social Worker location</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="password" id="social_pass" type="password" class="validate" required="true">
                            <label for="social_pass">Social Worker Password</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button id="btn_social_worker_1" type="button" class="validate btn btn-info">Save Social Worker</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="modal-footer white">
        <div style="margin: 30dp;">
            <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
        </div>
    </div>
</div
</div>

<!-- acc worker -->
<div id="addAccountant" class="modal white" style="max-width: 750px;">
    <div class="modal-content">
        <div class="row">
            <div class="col s12">

                <form id="accountatData" method="post">
                    <div  id="acct_feedb">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="name" id="acc_name" type="text" class="validate" required="true">
                            <label for="acc_name">Accountant Name</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="number" id="acc_number" type="number" class="validate" required="true">
                            <label data-error="Not a number" for="acc_number">Accountant Number</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" id="acc_email" type="email" class="validate" required="true">
                            <label data-error="Wrong email fomart" for="acc_email">Accountant email</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="location" id="acc_location" type="text" class="validate" required="true">
                            <label for="acc_location">Accountant location</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="password" id="acc_pass" type="password" class="validate" required="true">
                            <label for="acc_pass">Accountant Password</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button id="btn_accountant" type="button" class="validate btn btn-info">Save Accountant Worker</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="modal-footer white">
        <div style="margin: 30dp;">
            <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
        </div>
    </div>
</div
</div>

<!-- Admin worker -->
<div id="addAdmin" class="modal white" style="max-width: 750px;">
    <div class="modal-content">
        <div class="row">
            <div class="col s12 ">

                <form id="adminData" method="post">
                    <div  id="admin_feedb">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="name" id="admin_name" type="text" class="validate" required="true">
                            <label for="admin_name">Admin Name</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="number" id="admin_number" type="number" class="validate" required="true">
                            <label data-error="Not a number" for="admin_number">Admin Number</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" id="admin_email" type="email" class="validate" required="true">
                            <label data-error="Wrong email fomart" for="admin_email">Admin email</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="location" id="admin_location" type="text" class="validate" required="true">
                            <label for="admin_location">Admin location</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="password" id="admin_pass" type="password" class="validate" required="true">
                            <label for="social_pass">Admin Password</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button id="btn_admin" type="button" class="validate btn btn-info">Save Admin Details</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="modal-footer white">
        <div style="margin: 80dp;">
            <a href="#!" class="modal-action modal-close btn-floating btn-large waves-effect waves-light red"><i class="material-icons">close</i></a>
        </div>
    </div>
</div>

<!--SPONSORS SECTION-->
<div id="test2" class="col s12">
    <div class="container">

        <div class="" style="margin-top: 5%;">
            <div class="">

            </div>

            <div class="" style="margin-top: 5%;">
                <ul class="tabs tabs-fixed-width">
                    <li class="tab"><a class="active" href="#new_sponsor">New Sponsor</a></li>
                    <li class="tab"><a  href="#sponsor_commitments">Sponsor Commitments</a></li>
                    <li class="tab"><a href="#sponsor_payments">Sponsor Payments</a></li>
                    <li class="tab"><a href="#sponsor_reg">Registered Sponsors</a></li>
                    <li class="tab"><a href="#applicant_sponsors"> Applicants</a>
                </ul>
            </div>


            <div class=" white lighten-4" style="margin-top: 5%;">

                <!--               REGISTER NEW SPONSOR TAB-->
                <div id="new_sponsor">
                    <div class="row">
                        <form id="new_sponsor_info" method="post">
                            <div id="new_sponsor_fb"></div>


                            <div class="col s12">
                                <div class="row">
                                    <div class="col s6">
                                        <div class="card">
                                            <div class="card-content" style="margin-bottom: 10%;">
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input required="true" name="new_sponsor_number" id="sponsor_number" type="text" class="validate">
                                                        <label data-error="Not a number" for="sponsor_number">Sponsor ID</label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input required="true" name="new_sponsor_name" id="sponsor_name" type="text" class="validate">
                                                        <label for="sponsor_name">Sponsor Name</label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input required="true" name="new_sponsor_mobile" id="sponsor_number" type="number" class="validate">
                                                        <label data-error="Not a number" for="sponsor_number">Sponsor Mobile Number</label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input required="true" name="new_sponsor_email" id="sponsor_email" type="email" class="validate">
                                                        <label data-error="Invalid Email" for="sponsor_email">Sponsor Email</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s6">
                                        <div class="card">
                                            <div class="card-content">
                                                <div class="card-content"style="margin-bottom: -10%;">

                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <select name="sponsormeans">
                                                                <option value="" disabled selected>Choose your communication means</option>
                                                                <option value="email">Email</option>
                                                                <option value="phonenumber">Phone Number</option>
                                                                <option value="skype">Skype</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <select name="sponsotype">
                                                                    <option value="" disabled selected>Type of Sponsorship</option>
                                                                    <option value="primary">Primary</option>
                                                                    <option value="secondary">Secondary</option>
                                                                    <option value="vocational">Vocational</option>
                                                                </select>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input required="true" name="new_sponsor_company" id="sponsor_company" type="text" class="validate">
                                                            <label  for="sponsor_company">Sponsor Company</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input required="true" name="new_sponsor_pass" id="sponsor_password" type="text" class="validate">
                                                            <label  for="sponsor_password">Sponsor Password</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                            <div class="row ">
                                <div class="col s6 offset-l4">
                                    <button id="btn_add_newSponsor" style="width: 50%;" class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                                        <i class="material-icons right">send</i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>


                <!--SPONSOR COMMITMENTS-->
                <div id="sponsor_commitments">
                    <div class="row">
                        <div class="col s12">
                            <form name="vinform" id="sponsor_commit_form">
                                <div class="row">
                                    <div class="col s8 offset-l2">
                                        <div class="card">
                                            <div class="card-content" style="margin-bottom: 10%;">
                                                <div class="search-container row">
                                                    <div class="input-field col s12">
                                                        <input required name="name" id="search" type="text" onkeyup="searchInfo()" >
                                                        <label for="search">Enter Sponsor id</label>
                                                    </div>
                                                </div>
                                                <div class="row" id="mylocation"></div>  
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input name="sponsor_amount" required id="sponsor_amount" type="number" class="validate">
                                                        <label data-error="Not a number" for="sponsor_amount">Amount to commit</label>
                                                    </div>
                                                </div>

                                            </div>  
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col s6 offset-l4">
                                        <button id="btn_sponsor_commits" style="width: 50%;" class="btn waves-effect waves-light btn-large" type="button" name="">Submit Commitment
                                            <i class="material-icons right">send</i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="sponsor_payments">
                    <!--                   SPONSOR COMMITMENTS PAYMENTS  -->
                    <form name="sponsorcommit_payments" id="sponsorcommit_payments">
                        <div class="row">
                            <div class="col s8 centered">

                            </div>

                            <div class="">
                                <div class="col s8 offset-l2">
                                    <div class="card row">
                                        <div class="card-content">
                                            <div class="row">
                                                <div class="input-field col s12">
                                                    <input name="sponsor_comm_number" id="sponsor_comm_number" type="text" onkeyup="searchSponsor()">
                                                    <label for="sponsor_comm_number">Sponsor ID</label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div id="sponsor_commit_bg">
                                    </div>

                                    <div class="card row">
                                        <div class="card-content">
                                            <div class="row">
                                                <div class="input-field col s12">
                                                    <input name="sponsor_amount_paid" id="sponsor_amount" type="number" class="validate">
                                                    <label data-error="Not a number" for="sponsor_amount">Amount being paid</label>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s8 offset-l4">
                                <button id="btn_commit_payments" style="width: 50%;" class="btn waves-effect waves-light btn-large" type="button" >Submit Commit Payment
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s8 offset-l5">

                                <a class="waves-effect waves-light btn-flat btn-large white black-text"  href="#makeSponsorPay">Make payment instead</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="sponsor_reg">

                    <!--                    LIST OF REGISTERED SPONSORS-->
                    <div class="card">
                        <div class="card-content">

                            <script type="text/javascript" charset="utf-8">

                                $(document).ready(function () {


                                    $("#example").DataTable({
                                        "bProcessing": false,
                                        "bServerSide": false,
                                        "sAjaxSource": "PopulateServlet",
                                        "bJQueryUI": true,
                                        "aoColumns": [
                                            {"mData": "sponsor_id"},
                                            {"mData": "name"},
                                            {"mData": "number"},
                                            {"mData": "email"},
                                            {"mData": "means"},
                                            {"mData": "type"},
                                            {"mData": "company"}

                                        ]
                                    });
                                });
                            </script>

                            <div id="dynamic" class="col s12">
                                <table cellpadding="0" cellspacing="0" border="0" class="table" style="width: 100%;"
                                       id="example">
                                    <thead>
                                        <tr>
                                            <th >Sponsor ID</th>
                                            <th >Name</th>
                                            <th >Number</th>
                                            <th >Email</th>
                                            <th >Communication means</th>
                                            <th >SponsorShip Type</th>
                                            <th>Company</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>

                        </div>   
                    </div>
                </div>

                <div id="applicant_sponsors">

                    <!--                    LIST OF SPONSORS WHO HAVE APPLIED-->
                    <div class="card">
                        <div class="card-content">

                            <script type="text/javascript" charset="utf-8">

                                $(document).ready(function () {

                                    var table;
                                    table = $("#sponsor_applicants_table").DataTable({
                                        "bProcessing": false,
                                        "bServerSide": false,
                                        "sAjaxSource": "PopulateSponsorApplicants",
                                        "bJQueryUI": true,
                                        "aoColumns": [
                                            {"mData": "name"},
                                            {"mData": "number"},
                                            {"mData": "email"},
                                            {"mData": "means"},
                                            {"mData": "type"},
                                            {"mData": "company"},
                                            {"mData": "date"},
                                            {"mData": "sponsor_id"}

                                        ]
                                    });

                                    $('#sponsor_applicants_table tbody').on('click', 'tr', function () {
                                        var data = table.row(this).data();

                                        var name = data.name;
                                        var number = data.number;
                                        var email = data.email;
                                        var means = data.means;
                                        var type = data.type;
                                        var company = data.company;


                                        swal.setDefaults({
                                            input: 'text',
                                            confirmButtonText: 'Next &rarr;',
                                            showCancelButton: true,
                                            animation: false,
                                            progressSteps: ['1', '2']
                                        });

                                        var steps = [

                                            'Enter sponsor id',
                                            'Enter sponsor password'
                                        ];

                                        swal.queue(steps).then(function (result) {
                                            swal.resetDefaults();
                                            $.ajax({
                                                type: 'GET',
                                                data: {
                                                    name: name,
                                                    number: number,
                                                    email: email,
                                                    means: means,
                                                    type: type,
                                                    company: company,
                                                    sponsor_id: result[0],
                                                    pass: result[1]},
                                                url: "AssignNewSponsorIdServ",
                                                success: function (res) {

                                                    swal(
                                                            'Server Feedback',
                                                            res,
                                                            'info'
                                                            );
                                                },
                                                error: function (res) {
                                                    swal(
                                                            'Server Feedback',
                                                            res,
                                                            'info'
                                                            );
                                                }

                                            });
                                        }, function () {
                                            swal.resetDefaults();
                                        });
//                                        swal({
//                                            title: 'Enter sponsor id to assign ' + name,
//                                            input: 'text',
//                                            showCancelButton: true,
//                                            inputValidator: function (value) {
//                                                return new Promise(function (resolve, reject) {
//                                                    if (value) {
//                                                        resolve();
//                                                    } else {
//                                                        reject('Please enter sponsor id assign');
//                                                    }
//                                                });
//                                            }
//                                        }).then(function (result) {
//
//                                            $.ajax({
//                                                type: 'GET',
//                                                data: {
//                                                    name: name,
//                                                    number: number,
//                                                    email: email,
//                                                    means: means,
//                                                    type: type,
//                                                    company: company,
//                                                    sponsor_id: result},
//                                                url: "AssignNewSponsorIdServ",
//                                                success: function (res) {
//
//                                                    swal(
//                                                            'Server Feedback',
//                                                            res,
//                                                            'info'
//                                                            );
//                                                },
//                                                error: function (res) {
//                                                    swal(
//                                                            'Server Feedback',
//                                                            res,
//                                                            'info'
//                                                            );
//                                                }
//
//                                            });
//
//                                        });
                                    });
                                });


                            </script>

                            <div id="dynamic" class="col s12">
                                <table  cellpadding="0" cellspacing="0" border="0" class="table" style="width: 100%;"
                                        id="sponsor_applicants_table">
                                    <thead>
                                        <tr>
                                            <th >Name</th>
                                            <th >Number</th>
                                            <th >Email</th>
                                            <th >Communication means</th>
                                            <th >SponsorShip Type</th>
                                            <th>Company</th>
                                            <th>Date applied</th>
                                            <th>Assign id</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>

                        </div>   
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="test3" class="col s12">

    <!--  STUDENT SECTION-->
    <div class="row">
        <div class="col s12" style="margin-top: 3%;">
            <div class="col s3">
                <div class="card">
                    <div class="card-content">
                        <!--                        APPLIED STUDENTS-->
                        <h5>Registered Students</h5>

                        <table  class="table table-hover table-striped" id="studentsTable">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Email</th>

                                </tr>
                            </thead>

                        </table>
                    </div>
                </div>

            </div>

            <div class="col s9" id="student_card">

                <div id="student_status">

                </div>
                <div class="row">
                    <div class="card" >
                        <div class=" card-title alert alert-info">
                            <h6> Selected student info</h6>
                        </div>
                        <div class="card-content">
                            <table  class="table table-hover table-striped" id="studentPersonalTable">
                                <thead>
                                    <tr>
                                        <th>Surname</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Gender</th>
                                        <th>Number</th>
                                        <th>Location</th>
                                        <th>Status</th>
                                        <th>Age</th>

                                    </tr>
                                </thead>

                            </table>
                        </div>

                    </div>

                </div>
                <!--                ASSIGN SPONSOR-->
                <div class="row">
                    <div class="card">
                        <form id="sponsor_to_assign_form" name="sponsor_to_assign_form">
                            <div class=" card-title alert alert-info">
                                <h6>  Enter sponsor id to assign</h6>
                            </div>
                            <div class="card-content">
                                <div class="row">
                                    <div class="input-field col s3">
                                        <input id="sponsor_to_assign" type="text" class="" name="sponsor_to_assign" onkeyup="sponsorToAssign()">
                                        <label for="sponsor_to_assign">Sponsors Id</label>
                                    </div>
                                    <div class="input-field col s9" id="sponsor_to_assign_bg">

                                    </div>

                                </div>
                                <div class="row">

                                </div>
                            </div>
                        </form>
                    </div>

                </div>
                <!--               ASSIGN SOCIAL WORKER-->
                <div class="row">
                    <div class="card">
                        <form id="social_to_assign_form" name="social_to_assign_form">
                            <div class=" card-title alert alert-info">
                                <h6>  Enter Social Worker Email or Number to assign</h6>
                            </div>
                            <div class="card-content">
                                <div class="row">
                                    <div class="input-field col s3">
                                        <input id="social_to_assign" type="text" class="" name="social_to_assign" onkeyup="searchSocialWorker()">
                                        <label for="social_to_assign">Social Worker Email/Number</label>
                                    </div>
                                    <div class="input-field col s9" id="social_to_assign_bg">

                                    </div>

                                </div>
                                <div class="row">

                                </div>
                            </div>
                        </form>
                    </div>

                </div>

                <!--               ASSIGN ACCOUNTANT-->
                <div class="row">
                    <div class="card">
                        <form id="accountant_to_assign_form" name="accountant_to_assign_form">
                            <div class=" card-title alert alert-info">
                                <h6>  Enter Accountant Email or Number to assign</h6>
                            </div>
                            <div class="card-content">
                                <div class="row">
                                    <div class="input-field col s3">
                                        <input id="acc_to_assign" type="text" class="" name="acc_to_assign" onkeyup="searchAccountant()">
                                        <label for="acc_to_assign">Accountant Email/Number</label>
                                    </div>
                                    <div class="input-field col s9" id="acc_to_assign_bg">

                                    </div>

                                </div>
                                <div class="row">

                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <form id="assign_student">
                        <input id="ass_student_id" name="ass_student_id" type="text" hidden="">
                        <input id="ass_sponsor_assigned" name="ass_sponsor_assigned" type="text" hidden="true">
                        <input id="ass_social_assigned" name="ass_social_assigned" type="text" hidden="true">
                        <input id="ass_acc_assigned" name="ass_acc_assigned" type="text" hidden="true">
                        <button id="btn_assign_student" type="button" class="btn waves-effect waves-light btn-large">  <i class="material-icons right">send</i>Add sponsor Payment</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div id="test4">
    <div class="container">
        <div class="row">
            <div style="margin-top: 5%;">
                <div class="col s12">
                    <ul class="tabs">
                        <li class="tab col s3"><a class="active" href="#newSkull">New School</a></li>
                        <li class="tab col s3"><a href="#feeStracture">Fees Structure</a></li>
                        <li class="tab col s3 "><a href="#recordedSkuls" onclick="return getAllSkuls();">Recorded Schools</a></li>
                        <li class="tab col s3"><a href="#recordedStracture" onclick="return getFees();">Recorded Fees Stracture</a></li>
                    </ul>
                </div>

                <!--                ADD SCHOOL TAB-->
                <div id="newSkull" class="col s12">
                    <div class="col s6 offset-l3">
                        <div class="card black-text" style="margin-top: 12%;">

                            <div class="card-content">
                                <form id="newSchoolData">
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input name="school_name" id="new_school_name" type="text" class="validate" required="true">
                                            <label for="new_school_name">School Name</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <select name="school_mode">
                                                <option value="" disabled selected>Choose Institution Mode</option>
                                                <option value="private">Private</option>
                                                <option value="public">Public</option>

                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <select name="school_means">
                                                <option value="" disabled selected>Choose Institution type</option>
                                                <option value="Day">Day</option>
                                                <option value="Boarding">Boarding</option>
                                                <option value="Day&Boarding">Day & Boarding</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="input-field col s12">
                                            <button id="btn_add_school" type="button" class="validate btn btn-info"><i class="material-icons right">save</i>Save Admin Details</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--                SCHOOL FEE STRACTURE-->
                <div id="feeStracture" class="col s12">
                    <form id="feeStractureForm">
                        <%
                            Connection conn = DBUtils.DBUtil.getConnection();
                            String query = "SELECT name FROM school";
                            PreparedStatement pst = conn.prepareStatement(query);

                            ResultSet rs = pst.executeQuery();

                        %>

                        <div class="row">
                            <div class="input-field col s6 offset-l3" style="margin-top: 5%;">
                                <select name="selected_school" id="school_lists">
                                    <option value="" disabled selected>Choose School Name</option>

                                    <%  while (rs.next()) {%>
                                    <option value="<%= rs.getString(1)%>"><%= rs.getString(1)%></option>
                                    <% }%>

                                </select>

                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6 offset-l3">

                                <div class="card">
                                    <div class="card-content">

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input name="first_term_fees" id="first_term_fees" type="number" class="validate" required="true">
                                                <label for="first_term_fees" data-error="Invalid number..">First Term Fees</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input name="second_term_fees" id="second_term_fees" type="number" class="validate" required="true">
                                                <label for="second_term_fees" data-error="Invalid number">Second Term Fees</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input name="second_term_fees" id="third_term_fees" type="number" class="validate" required="true">
                                                <label for="second_term_fees" data-error="Invalid number">Third Term Fees</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6 offset-l3">
                                <button id="btn_add_school_fee" type="button" class=" btn-large btn btn-info"><i class="material-icons right">save</i>Save Fee Structure</button>

                            </div>
                        </div>
                    </form>
                </div>

                <!--                                    LIST OF RECORDED SCHOOLS-->
                <div id="recordedSkuls" class="col s12" style="margin-top: 5%;">
                    <div class="card">
                        <div class="card-content">

                            <table  class="table table-hover table-striped" id="recordedSkulsTable">
                                <thead>
                                    <tr>
                                        <th>School ID</th>
                                        <th>School Name</th>
                                        <th>School Mode</th>
                                        <th>School Means</th>
                                        <th>Date Registered</th>

                                    </tr>
                                </thead>

                            </table>

                        </div>
                    </div>
                </div>

                <!--                SCHOOL FEES STRACTURE-->
                <div id="recordedStracture" class="col s12" style="margin-top: 5%;">
                    <div class="card">
                        <div class="card-content">

                            <table  class="table table-hover table-striped" id="recordedFeeStracture">
                                <thead>
                                    <tr>
                                        <th>School ID</th>
                                        <th>School Name</th>
                                        <th>First Term</th>
                                        <th>Second Term</th>
                                        <th>Third Term</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Structure For editing school fees -->
<div id="editSchoolFees" class="modal card-content myModelStyle white" style="max-width: 550px;">
    <div class="modal-header">
        <div class="col s6 center">
            <h6 class="alert alert-info">Edit Selected School Fees structure</h6>
        </div>
    </div>
    <div class="modal-content">
        <div class="row">
            <div class="col s12">
                <form id="editFeeStractureForm">
                    <div class="row">
                        <div class="input-field">
                            <div class="">
                                <div class="">
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input name="selected_school_id" id="selected_school_id" type="hidden" class="validate">
                                            <input name="selected_first_term_fees" id="selected_first_term_fees" type="number" class="validate" required="true">
                                            <label for="selected_first_term_fees" data-error="Invalid number.."></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input name="selected_second_term_fees" id="selected_second_term_fees" type="number" class="validate" required="true">
                                            <label for="selected_second_term_fees" data-error="Invalid number"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input name="selected_third_term_fees" id="selected_third_term_fees" type="number" class="validate" required="true">
                                            <label for="selected_third_term_fees" data-error="Invalid number"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-l3">
                            <button id="btn_save_school_fee" type="button" class=" btn-large btn btn-info"><i class="material-icons right">save</i>Save Fee Structure</button>

                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>


<!-- Modal Structure For sponsor payments -->
<div id="makeSponsorPay" class="modal modal-sm card-content">
    <div class="modal-content">
        <div class="col s6 center" style="margin-bottom: 5%;">
            <h4>Record Sponsor Payments</h4>
        </div>
        <div class="row">
            <div class="col s8 offset-l2 center">
                <form id="make_sponsor_payments" name="make_sponsor_payments">

                    <div class="row">
                        <div class="input-field col s12">
                            <input name="make_sponsor_payments_id" id="make_sponsor_payments_id" type="text" onkeyup="searchSponsorForPay()">
                            <label for="make_sponsor_payments_id">Enter Sponsor Id</label>
                        </div>
                    </div>
                    <div class="row">
                        <div id="make_payments_bg">

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <input name="make_sponsorpay_amount" id="make_sponsor_amount" type="number" class="validate">
                            <label data-error="Not a number" for="make_sponsor_amount">Payment being made</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button id="btn_make_sponsor_payment" type="button" class="validate btn btn-info">Add sponsor Payment</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
    </div>
</div>
</body>
</html>
