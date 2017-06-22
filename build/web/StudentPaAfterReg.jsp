
<%@page import="dao.Student"%>
<%@page import="Model.StudentPersonalModel"%>
<%@page import="java.sql.PreparedStatement"%>
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
            StudentPersonalModel spm = null;
            Student stude = new Student();
            String id = null;
            String email = null;
            String studName = null;
            String number = null;
            String location = null;

            try {
                if (session.getAttribute("STUDENT_MODEL") == null || session.getAttribute("STUDENT_MODEL") == "") {
                    response.sendRedirect("login.jsp");
                } else {
                    studentModel = (StudentModel) session.getAttribute("STUDENT_MODEL");
                    id = String.valueOf(studentModel.getId());
                    spm = stude.retrievePersonalDetaisl(id);
                    email = studentModel.getEmail();

                    studName = spm.getS_name() + " " + spm.getF_name() + " " + spm.getL_name();
                    number = spm.getNumber();
                    location = spm.getLocation();
                }
            } catch (Exception e) {
                response.sendRedirect("login.jsp");
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
                    <div class="col s7 offset-l2">
                        <div class="card white">
                            <div class="card-content">
                                <div class="row">
                                    <div class="col s4 blue-text">
                                        <img src="ImageServlet?id=<%= id%>" class="circle" alt="" style="width: 170px; height: 170px;"/>
                                    </div>
                                    <div class="col s4">
                                        <p style="margin: 5%;"><%= studName%></p>
                                        <p style="margin: 5%;"><%= email%></p>
                                        <p style="margin: 5%;"><%= number%></p>
                                        <p style="margin: 5%;"><%= location%></p>
                                    </div>
                                </div>

                            </div>
                            <div>
                            </div>
                        </div>
                    </div>
                </div> 

                <div  class="row" >
                    <div id="allocationMsg" class="col s7 offset-l2">

                    </div>
                </div>
                <div id="allocationDetails" class="row ">
                    <div class="col s12 offset-l1">
                        <ul class="tabs">
                            <li class="tab col s3"><a class="active" href="#sponsorsTab">SPONSORS</a></li>
                            <li class="tab col s3"><a href="#socialWokrers">SOCIAL WORKER</a></li>
                            <li class="tab col s3"><a href="#accountantTab">ACCOUNTANT</a></li>

                        </ul>
                    </div>
                    <div id="sponsorsTab" class="col s7 offset-l2" style="margin-top: 5%;">

                        <div class="card-panel white black-text">
                            <div class="row">
                                <h6 class="alert alert-info">Assigned Sponsor Details</h6>
                            </div>
                            <div class="row">
                                <div class="col s4 white-text"> 
                                    <span class="new badge cyan" data-badge-caption="Sponsor Name"></span>
                                </div>
                                <div class="col s4">
                                    <span id="sposnsorName" class=""></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s4 white-text"> 
                                    <span class="new badge cyan" data-badge-caption="Sponsor Email"></span>
                                </div>
                                <div class="col s4">
                                    <span id="sponsorEmail" class=""></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s4 white-text"> 
                                    <span  class="new badge cyan" data-badge-caption="Sponsor Mobile"></span>
                                </div>
                                <div class="col s4">
                                    <span id="sponsorMobile" class=""></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--                    SOCIAL WORKER NAME-->
                    <div id="socialWokrers" class="col s12">

                        <div class="col s4" style="margin-top: 5%;">
                            <div class="card-panel white black-text">
                                <div class="row">
                                    <h6 class="alert alert-info">Assigned Social Worker Details</h6>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span class="new badge cyan" data-badge-caption="Name :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="socialName" class=""></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span class="new badge cyan" data-badge-caption="Email :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="socialEmail" class=""></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span  class="new badge cyan" data-badge-caption="Mobile :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="socialMobile" class=""></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col s8" style="margin-top: 5%;">
                            <div class="card-panel white black-text">
                                <ul class="collapsible popout" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header" onclick="getFirsTermReport()">First Term</div>
                                        <div id="social_reports" class="collapsible-body">

                                        </div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header" onclick="getSecondTermReport()">Second Term</div>
                                        <div id="socialSecondTerm" class="collapsible-body"></div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header" onclick="getThirdTermReport()">Third Term</div>
                                        <div id="socialThirdTerm" class="collapsible-body"></div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                    </div>
                    <div id="accountantTab" onclick="getAccountatAllocation()" class="col s12">
                        <div class="col s4" style="margin-top: 5%;">
                            <div class="card-panel white black-text">
                                <div class="row">
                                    <h6 class="alert alert-info">Assigned Accountant Details</h6>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span class="new badge cyan" data-badge-caption="Name :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="accName" class=""></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span class="new badge cyan" data-badge-caption="Email :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="accEmail" class=""></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s4 white-text"> 
                                        <span  class="new badge cyan" data-badge-caption="Mobile :"></span>
                                    </div>
                                    <div class="col s4">
                                        <span id="accMobile" class=""></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col s8" style="margin-top: 5%;">
                            <div class="card-panel">
                                <div id="account_reports">

                                </div>
                            </div>

                        </div>
                    </div>

                </div>

                <script>

                    function getAccountatAllocation() {

                    }

                    function getFirsTermReport() {
                        //SOCIAL WORKER REPOSRTS.....FIRST TERM
                        $.ajax({
                            type: 'POST',
                            data: {stud_id: '<%= id%>', term: 1},
                            url: "TermlyReportsServlet",
                            success: function (result) {
                                //Materialize.toast(result, 3000);
                                $("#social_reports").html(result);
                            },
                            error: function (result) {
                                swal('oops', result, 'error');
                            }

                        });


                    }

                    function getSecondTermReport() {
                        //SOCIAL WORKER REPOSRTS.....FIRST TERM
                        $.ajax({
                            type: 'POST',
                            data: {stud_id: '<%= id%>', term: 2},
                            url: "TermlyReportsServlet",
                            success: function (result) {
                                //Materialize.toast(result, 3000);
                                $("#socialSecondTerm").html(result);
                            },
                            error: function (result) {
                                swal('oops', result, 'error');
                            }

                        });
                    }

                    function getThirdTermReport() {
                        //SOCIAL WORKER REPOSRTS.....FIRST TERM
                        $.ajax({
                            type: 'POST',
                            data: {stud_id: '<%= id%>', term: 3},
                            url: "TermlyReportsServlet",
                            success: function (result) {
                                //Materialize.toast(result, 3000);
                                $("#socialThirdTerm").html(result);
                            },
                            error: function (result) {
                                swal('oops', result, 'error');
                            }

                        });


                    }
                    $(document).ready(function () {

                        var isAllocated;
                        //make ajax requests to check if student is assigned
                        $.ajax({
                            type: 'POST',
                            data: {id: '<%= id%>'},
                            url: "StudentPanelServlets?action=allocations",
                            success: function (response) {

                                if (response !== "true") {
                                    isAllocated = 0;
                                    $('#allocationDetails').hide();
                                    $('#allocationMsg').show().html(response);

                                } else {
                                    isAllocated = 1;
                                    $('#allocationMsg').hide();
                                    $('#allocationDetails').show();

                                }
                            },
                            error: function (response) {
                                alert("error" + response);
                            }

                        });

                        if (isAllocated !== 0) {
                            //SPONSOR DETAILS....
                            $.get('StudentPanelServlets?action=sponsor&id=<%=id%>', function (responseJson) {

                                if (responseJson !== null) {
                                    $.each(responseJson, function (key, value) {

                                        $('#sposnsorName').html(value['name']);
                                        $('#sponsorEmail').html(value['email']);
                                        $('#sponsorMobile').html(value['number']);

                                    });
                                }
                            });

                            //SOCIAL WORKER DETAILS....
                            $.get('StudentPanelServlets?action=social&id=<%=id%>', function (responseJson) {

                                if (responseJson !== null) {
                                    $.each(responseJson, function (key, value) {

                                        $('#socialName').html(value['name']);
                                        $('#socialEmail').html(value['email']);
                                        $('#socialMobile').html(value['mobile']);

                                    });
                                }
                            });

                            //ACCOUNTANT DETAILS....
                            $.get('StudentPanelServlets?action=acc&id=<%=id%>', function (responseJson) {

                                if (responseJson !== null) {
                                    $.each(responseJson, function (key, value) {

                                        $('#accName').html(value['name']);
                                        $('#accEmail').html(value['email']);
                                        $('#accMobile').html(value['mobile']);

                                    });
                                }
                            });

//ACCOUNT ALLOCATIONS
                            $.ajax({
                                type: 'POST',
                                data: {stud_id: '<%= id%>'},
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
                    });
                </script>
            </div>

    </body>
</html>
