<%-- 
    Document   : SocialWorkerPanel
    Created on : May 28, 2017, 8:29:01 AM
    Author     : toni
--%>

<%@page import="Model.SponosorModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.SocialLoginModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SponsorKonnect || Sponsor Panel</title>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <link href="css/demo_table.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <link href="css/acc.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery-1.12.4.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/acc.js" type="text/javascript"></script>
        <script src="js/sweetalert2.js" type="text/javascript"></script>
    </head>
    <body>

        <%
            // SponosorModel sp = (SponosorModel) session.getAttribute("SPONSOR_MODEL");
            SponosorModel sm = null;
            String name = null;
            String email = null;
            String mobile = null;
            String means = null;
            String company = null;
            String pass = null;
            String id = null;
            //check for session
            if (session.getAttribute("SPONSOR_MODEL") == null || session.getAttribute("SPONSOR_MODEL") == "") {
                response.sendRedirect("SponsorLogin.jsp");
            } else {
                sm = (SponosorModel) session.getAttribute("SPONSOR_MODEL");
                name = sm.getName();
                email = sm.getEmail();
                mobile = sm.getNumber();
                means = sm.getMeans();
                company = sm.getCompany();
                pass = sm.getPass();
                id = sm.getSponsor_id();
            }
        %>

        <nav class="nav-extended cyan">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo">SpnsorKonnect</a>
                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">

                    <li><a href="./SponsorLogout">Log out</a></li>
                </ul>
                <ul class="side-nav" id="mobile-demo">

                    <li><a href="./SponsorLogout">Log out</a></li>
                </ul>
            </div>

        </nav>
        <div class="container">

            <div class="row">
                <div class="col s8 offset-l2">
                    <div class="card-panel black-text">
                        <div class="alert cyan white-text">
                            <h6>YOUR DETAILS</h6>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Sponsor Name"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= name%></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Sponsor Email"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= email%></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Sponsor Mobile"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= mobile%></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Communication means"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= means%></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Sponsor Company"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= company%></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 white-text"> 
                                <span class="new badge cyan" data-badge-caption="Sponsor Pass"></span>
                            </div>
                            <div class="col s4">
                                <span class=""> <%= pass%></span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


            <script>
                $(document).ready(function () {

                    $.get('GetSponsorAssignedStudServ?sponsor=<%= id%>', function (responseJson) {
                        if (responseJson[0]) {

                            $("#btn_print_social").show();
                            $("#feed_back").hide();
                            $("#assigned_students").show();

                            $("#assigned_students").find("tr:gt(0)").remove();

                            var skulTable = $("#assigned_students");
                            $.each(responseJson, function (key, value) {

                                var rowNew = $('<tr id="row_1" onclick="javascript:getStudentDet(this);"><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                                rowNew.children().eq(0).text(value['stud_id']);
                                rowNew.children().eq(1).text(value['f_name'] + " " + value['l_name']);
                                rowNew.children().eq(2).text(value['school']);
                                rowNew.children().eq(3).text(value['level']);
                                rowNew.children().eq(4).html('<a class="btn-floating btn-large waves-effect waves-light blue" href="#modal4"><i class="material-icons">supervisor_account</i></a>');
                                rowNew.children().eq(5).html('<a class="btn-floating btn-large waves-effect waves-light red" onClick="acc()"  href="#modal5"><i class="material-icons">monetization_on</i></a>');
                                rowNew.appendTo(skulTable);

                            });
                        } else {

                            $("#btn_print_social").hide();
                            $("#feed_back").show();
                            $("#assigned_students").hide();
                            $("#feed_back").html("No student has been assigned to you yet");
                        }
                    });

                });

                function getStudentDet(row) {
                    var x = row.cells;
//                    document.getElementById('selected_school_id').value = x[0].innerHTML;
//                    document.getElementById('selected_first_term_fees').value = x[2].innerHTML;
//                    document.getElementById('selected_second_term_fees').value = x[3].innerHTML;
//                    document.getElementById('selected_third_term_fees').value = x[4].innerHTML;
                    var stud = x[0].innerHTML;
                    document.getElementById("student_image").setAttribute("src", "ImageServlet?id=" + stud);
                    $("#stuent_names").html(x[2].innerHTML + " " + x[3].innerHTML);

                    document.getElementById("acc_student_image").setAttribute("src", "ImageServlet?id=" + stud);
                    $("#acc_stuent_names").html(x[2].innerHTML + " " + x[3].innerHTML);


                    $.ajax({
                        type: 'POST',
                        data: {stud_id: stud},
                        url: "GetStudentSocialReport?action=soc",
                        success: function (result) {
                            //Materialize.toast(result, 3000);
                            $("#social_reports").html(result);
                        },
                        error: function (result) {
                            swal('oops', result, 'error');
                        }

                    });

                    $.ajax({
                        type: 'POST',
                        data: {stud_id: stud},
                        url: "GetStudentSocialReport?action=acc",
                        success: function (result) {
                            //Materialize.toast(result, 3000);
                            $("#account_reports").html(result);
                        },
                        error: function (result) {
                            swal('oops', result, 'error');
                        }

                    });
                }

                function acc() {
                    //  alert('safdf');


                }
            </script>

            <div class="row">

                <div class="col s8 offset-l2 black-text">
                    <div class="card-panel">
                        <div class="alert cyan white-text">
                            <h6>Students assigned to you</h6>
                        </div>
                        <table  class="table striped highlight centered"
                                id="assigned_students">
                            <thead>
                                <tr>
                                    <th >Student ID</th>
                                    <th >Name</th>
                                    <th>School</th>
                                    <th>Level</th>
                                    <th>Social Worker report</th>
                                    <th>Accountant Report</th>
                                </tr>
                            </thead>
                        </table>
                        <div id="feed_back" class="alert alert-danger">

                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Modal Structure For assigning accounts-->

        <!-- Modal Structure -->
        <div id="modal4" class="modal white" class="modal modal-lg modal-fixed-footer" data-backdrop="static" data-keyboard="false" style="height: 100%; width: 60%; max-height: 100%; top: 5%; bottom: 5%;">


            <div class="modal-header">
                <img id="student_image" src="ImageServlet?id=" class="circle" alt="" style="width: 150px; height: 150px; margin-left: 40%;margin-top: 5%;"/>
                <p id="stuent_names" class="teal-text text-lighten-1" style="margin-left: 40%;"></p>
            </div>

            <div id="social_reports" class="modal-content">
            </div>
            <div class="modal-footer white">
                <button id="btn_print_social" class="btn waves-effect waves-light" type="button" name="action">Print
                    <i class="material-icons right">save</i> </button>
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">close</a>
            </div>
        </div>
        <!-- Modal Structure -->
        <div id="modal5" class="modal white" class="modal modal-lg modal-fixed-footer" data-backdrop="static" data-keyboard="false" style="height: 100%; width: 60%; max-height: 100%; top: 5%; bottom: 5%;">

            <div class="modal-header">
                <img id="acc_student_image" src="ImageServlet?id=" class="circle" alt="" style="width: 150px; height: 150px; margin-left: 40%;margin-top: 5%;"/>
                <p id="acc_stuent_names" class="teal-text text-lighten-1" style="margin-left: 40%;"></p>
            </div>

            <div id="account_reports" class="modal-content">
            </div>
            <div class="modal-footer white">
                <button id="btn_print_social" class="btn waves-effect waves-light" type="button" name="action">Print
                    <i class="material-icons right">save</i> </button>
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">close</a>
            </div>
        </div>
    </body>
</html>
