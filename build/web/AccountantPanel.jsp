<%-- 
    Document   : SocialWorkerPanel
    Created on : May 28, 2017, 8:29:01 AM
    Author     : toni
--%>

<%@page import="Model.AccLoginModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.SocialLoginModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SponsorKonnect || Accountant Panel</title>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <link href="css/demo_table.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <link href="css/acc.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery-1.12.4.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/acc.js" type="text/javascript"></script>
        <script src="js/sweetalert2.common.js" type="text/javascript"></script>
    </head>
    <body>

        <%
            AccLoginModel sm = null;
            String email = null;
            //check for session
            if (session.getAttribute("ACCOUNT_DETAILS") == null || session.getAttribute("ACCOUNT_DETAILS") == "") {
                response.sendRedirect("AccountantLogin.jsp");
            } else {
                sm = (AccLoginModel) session.getAttribute("ACCOUNT_DETAILS");
                email = sm.getEmail();
            }
        %>

        <nav class="nav-extended grey">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo">SpnsorKonnect</a>
                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="sass.html">Sass</a></li>
                    <li><a href="badges.html">Components</a></li>
                    <li><a href="collapsible.html">JavaScript</a></li>
                </ul>
                <ul class="side-nav" id="mobile-demo">
                    <li><a href="sass.html">Sass</a></li>
                    <li><a href="badges.html">Components</a></li>
                    <li><a href="collapsible.html">JavaScript</a></li>
                </ul>
            </div>
            <div class="nav-content">
                <ul class="tabs tabs-transparent">

                    <li class="tab"><a class="active" href="#file_report"><i class="material-icons left">supervisor_account</i>Allocate student</a></li>
                    <li class="tab"><a href="#my_reports"><i class="material-icons left">monetization_on</i>Student Allocations</a></li>
                </ul>
            </div>
        </nav>

        <!--        FILE REPORT SECTION-->
        <div id="file_report" class="col s12">
            <div class="container">

                <div class="row" style="margin-top: 5%;">
                    <div class="col s12">
                        <div class="card-title alert grey white-text">
                            <h6>Students Assigned to you. Select to allocate funds</h6>
                        </div>
                        <div class="card-panel">
                            <script type="text/javascript" charset="utf-8">
                                var stud_id;
                                $(document).ready(function () {
                                    var table = $("#assigned_students").DataTable({
                                        "bProcessing": false,
                                        "bServerSide": false,
                                        "sAjaxSource": "AccountantStudents?email=<%=email%>",
                                        "bJQueryUI": true,
                                        "aoColumns": [
                                            {"mData": "stud_id"},
                                            {"mData": "s_name"},
                                            {"mData": "f_name"},
                                            {"mData": "l_name"},
                                            {"mData": "number"},
                                            {"mData": "location"},
                                            {"mData": "age"},
                                            {"mData": "gender"},
                                            {"mData": "button"}
                                        ]
                                    });


                                    $('#assigned_students tbody').on('click', 'tr', function () {
                                        var data = table.row(this).data();
                                        //alert('You clicked on ' + data.f_name + '\'s row');

                                        document.getElementById('acc_stud_id').value = data.stud_id;
                                        document.getElementById('atud_first_name').value = data.f_name;
                                        document.getElementById('stud_last_name').value = data.l_name;


                                         //AJAX FOR GETTING STUDENT SCHOOL
                                        $.get('AccGetSchoolDetails?id=' + data.stud_id, function (responseJson) {

                                            if (responseJson != null) {
                                                $("#school_table").find("tr:gt(0)").remove();

                                                var table1 = $("#school_table");

                                                $.each(responseJson, function (key, value) {

                                                    var rowNew = $('<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                                                    rowNew.children().eq(0).text(value['id']);
                                                    rowNew.children().eq(1).text(value['name']);
                                                    rowNew.children().eq(2).text(value['first']);
                                                    rowNew.children().eq(3).text(value['second']);
                                                    rowNew.children().eq(4).text(value['third']);
                                                    rowNew.children().eq(5).text(value['total']);
                                                    rowNew.appendTo(table1);
                                                });
                                            }
                                        });
                                        
                                        //AJAX FOR GETTING STUDENT SPONSOR
                                        $.get('AccGetSponsorsDetails?id=' + data.stud_id, function (responseJson) {

                                            if (responseJson != null) {
                                                $("#adminTable").find("tr:gt(0)").remove();

                                                var table1 = $("#adminTable");

                                                $.each(responseJson, function (key, value) {

                                                    var rowNew = $('<tr><td></td><td></td><td></td><td></td></tr>');
                                                    rowNew.children().eq(0).text(value['sponsor_id']);
                                                    rowNew.children().eq(1).text(value['name']);
                                                    rowNew.children().eq(2).text(value['commits']);
                                                    rowNew.children().eq(3).text(value['payments']);
                                                   
                                                    rowNew.appendTo(table1);
                                                });
                                            }
                                        });

                                    });

                                });

                           
                            </script>

                            <div id="dynamic">
                                <table cellpadding="0" cellspacing="0" border="0" class="table table-bordered table-responsive"
                                       id="assigned_students">
                                    <thead>
                                        <tr>
                                            <th >Student ID</th>
                                            <th >Surname</th>
                                            <th>First Name</th>
                                            <th >Last Name</th>
                                            <th >Number</th>
                                            <th>Location</th>
                                            <th >Age</th>
                                            <th>Gender</th>
                                            <th> Add Report</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--        REPORT RECORDS SECTION-->
        <div id="my_reports" class="col s12">
            <form id="searchstudent_report" name="searchstudent_report">
                <div class="container">
                    <div class="row" style="margin-top: 5%;">
                        <div class="input-field col s6 offset-l2">
                            <i class="material-icons prefix">search</i>
                            <input  id="serch_student_field" name="serch_student_field" type="text" class="validate" >
                            <label for="serch_student_field">Search Student by id</label>
                        </div>       
                    </div>

                    <div id="feedback_area" class="col s6 offset-l2">

                    </div>
                </div>
            </form>
        </div> 



        <!-- Modal Trigger -->
        <!--        <a class="modal-trigger waves-effect waves-light btn" href="#modal1">Modal</a>-->

        <!-- Modal Structure For assigning accounts-->
        <div id="modal45" class="modal modal-lg modal-fixed-footer" data-backdrop="static" data-keyboard="false" style="height: 90%; width: 80%; max-height: 100%; top: 5%; bottom: 5%;">
            <form id="selcted_student_form">
                <div class="modal-content">
                    <div class="row">
                        <div class="input-field col s6">
                            <input type="hidden" id="acc_stud_id">
                            <input disabled placeholder="Placeholder" id="atud_first_name" type="text">
                            <label for="atud_first_name" class="active">First Name</label>
                        </div>
                        <div class="input-field col s6">
                            <input disabled id="stud_last_name" type="text" >
                            <label for="stud_last_name" class="active">Last Name</label>
                        </div>
                    </div>

                    <script type="text/javascript" charset="utf-8">



                    </script>

                    <div class="row">
                        <div class="col s12">
                            <div class="col s6">
                                <div class="row alert grey white-text">
                                    <h6>SCHOOL INFORMATION</h6>
                                </div>
                                <div class="row">
                                    <table  class="table centered striped" id="school_table">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Name</th>
                                                <th>First Term</th>
                                                <th>Second Term</th>
                                                <th>Third Term</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="col s6">

                                <div class="row alert grey white-text">
                                    <h6>SPONSOR INFORMATION</h6>
                                </div>
                                <div class="row">
                                    <table  class="table centered striped" id="adminTable">
                                        <thead>
                                            <tr>
                                                <th>Sponsor id</th>
                                                <th>Sponsor Name</th>
                                                <th>Sponsor Commitments</th>
                                                <th>Sponsor Payments</th>
                                            </tr>
                                        </thead>

                                    </table>
                                </div>


                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col s6 offset-l3">
                            <div class="row alert alert-info">
                                <h6>Allocation details</h6>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="amount_school" type="number" class="">
                                    <label for="amount_school">Amount to School Fees</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="amount_upkeep" type="number" class="">
                                    <label for="number">Amount to upkeep</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="amount_others" type="number" class="">
                                    <label for="email">Amount to others</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s6 offset-l4">
                            <div class="row">
                                <button style="width: 50%;" class="btn btn-large waves-effect waves-light" type="button" name="action">Save
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                            <div class="row">
                                <a href="#!" style="width: 50%;" class=" btn-large modal-action modal-close waves-effect waves-green btn-flat ">close</a>
                            </div>
                        </div>
                    </div>

                    <!--            <div class="modal-footer">
                                    <button id="btn_submit_report" class="btn waves-effect waves-light" type="button" name="action">Submit
                                        <i class="material-icons right">send</i> </button>
                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">close</a>
                                </div>-->
            </form>
        </div>
    </body>
</html>
