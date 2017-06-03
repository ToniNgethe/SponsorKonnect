<%-- 
    Document   : SocialWorkerPanel
    Created on : May 28, 2017, 8:29:01 AM
    Author     : toni
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.SocialLoginModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SponsorKonnect || Social Worker Panel</title>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <link href="css/demo_table.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified CSS -->
        <script src="js/jquery-1.12.4.js" type="text/javascript"></script>
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->

        <script src="js/materialize.min.js" type="text/javascript"></script>

        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>

        <script src="js/social.js" type="text/javascript"></script>
    </head>
    <body>

        <%
            SocialLoginModel sm = null;
            String email = null;
            //check for session
            if (session.getAttribute("SOCIAL_WORKER") == null || session.getAttribute("SOCIAL_WORKER") == "") {
                response.sendRedirect("SocialWorkerLogin.jsp");
            } else {
                sm = (SocialLoginModel) session.getAttribute("SOCIAL_WORKER");
                email = sm.getEmail();
            }
        %>

        <nav class="nav-extended teal">
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

                    <li class="tab"><a class="active" href="#file_report"><i class="material-icons left">content_paste</i>File Student Report</a></li>
                    <li class="tab"><a href="#my_reports"><i class="material-icons left">library_books</i>My Reports</a></li>

                </ul>
            </div>
        </nav>

        <!--        FILE REPORT SECTION-->
        <div id="file_report" class="col s12">
            <div class="container">

                <div class="row" style="margin-top: 5%;">
                    <div class="col s12">
                        <div class="card-title alert alert-info">
                            <h5>Students Assigned to you</h5>
                        </div>
                        <div class="card-panel">
                            <script type="text/javascript" charset="utf-8">

                                $(document).ready(function () {
                                    var table = $("#assigned_students").DataTable({
                                        "bProcessing": false,
                                        "bServerSide": false,
                                        "sAjaxSource": "SocialWorkerStudents?email=<%=email%>",
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
                                        document.getElementById('selected_stud_id').value = data.stud_id;
                                        document.getElementById('stud_first_name').value = data.f_name;
                                        document.getElementById('stud_last_name').value = data.l_name;
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
                                            <th width> Add Report</th>
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
                            <input id="serch_student_field" name="serch_student_field" type="text" class="validate" onkeyup="studentReport()">
                            <label for="serch_student_field">Search Student by id</label>
                        </div>       
                        <div id="feedback_area" class="col s6 offset-l2">

                        </div>
                    </div>


                </div>
            </form>
        </div> 

    </div>

</div>


<!-- Modal Trigger -->
<!--        <a class="modal-trigger waves-effect waves-light btn" href="#modal1">Modal</a>-->

<!-- Modal Structure For filing reports-->
<div id="modal1" class="modal modal-fixed-footer" data-backdrop="static" data-keyboard="false" style="max-width: 1500px;width:1400px; max-height: 1700px; height: 900px;">
    <form id="selcted_student_form">
        <div class="modal-content">
            <div class="row alert alert-info">
                <h6>Social Assesment For <div class="selected_stud_name"></div><div class="selected_stud_name_2"></div></h6>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input disabled="" placeholder="Placeholder" id="stud_first_name" type="text"  >
                    <label for="first_name" class="active">First Name</label>
                </div>
                <div class="input-field col s6">
                    <input disabled id="stud_last_name" type="text" class="validate" placeholder="Placeholder">
                    <label for="last_name" class="active">Last Name</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s8">
                    <input name="social_worker" id="social_worker" value="<%=email%>" type="hidden">
                    <input name="selected_stud_id" id="selected_stud_id" type="hidden">
                    <i class="material-icons prefix">today</i>
                    <input name="dateOfVisit" id="dateOfVisit" type="date" class="datepicker">
                    <label class="active" for="dateOfVisit">Date of visit</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">mode_edit</i>
                    <textarea name="students_family_bg" id="students_family_bg" class="materialize-textarea"></textarea>
                    <label for="students_family_bg">Student’s family background Condition</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">mode_edit</i>
                    <textarea name="current_compo" id="icon_prefix2" class="materialize-textarea"></textarea>
                    <label for="icon_prefix2">Current family composition and/or household membership</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">mode_edit</i>
                    <textarea name="student_ethnicity" id="icon_prefix2" class="materialize-textarea"></textarea>
                    <label for="icon_prefix2">Ethnicity, religion, and spirituality </label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">accessible</i>
                    <textarea name="student_health" id="icon_prefix2" class="materialize-textarea"></textarea>
                    <label for="icon_prefix2">Physical functioning, health concerns, illness, disabilities, medications</label>
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button id="btn_submit_report" class="btn waves-effect waves-light" type="button" name="action">Submit
                <i class="material-icons right">send</i> </button>
            <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">close</a>
        </div>
    </form>
</div>
</body>
</html>
