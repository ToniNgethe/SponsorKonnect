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
                                var pays;
                                var total;
                                var sponsor_id;
                                var bal;
                                
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
                                        stud_id = data.stud_id;
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

                                                    total = value['total'];
                                                });
                                            }
                                        });

                                        //AJAX FOR GETTING STUDENT SPONSOR
                                        $.get('AccGetSponsorsDetails?id=' + data.stud_id, function (responseJson) {

                                            if (responseJson != null) {
                                                $("#adminTable").find("tr:gt(0)").remove();

                                                var table1 = $("#adminTable");

                                                $.each(responseJson, function (key, value) {

                                                    var rowNew = $('<tr><td></td><td></td><td></td><td></td><td></td></tr>');
                                                    rowNew.children().eq(0).text(value['sponsor_id']);
                                                    rowNew.children().eq(1).text(value['name']);
                                                    rowNew.children().eq(2).text(value['payments']);
                                                    rowNew.children().eq(3).text(value['commits']);
                                                    rowNew.children().eq(4).text(value['bal']);

                                                    rowNew.appendTo(table1);

                                                    sponsor_id = value['sponsor_id'];
                                                    bal = value['bal'];
                                                    pays = value['payments'];
                                                });
                                            }
                                        });

                                    });

                                    $('#alocate_btn').click(function () {
                                        var amount = $("#amount_school").val();
                                        var upkeep = $("#amount_upkeep").val();
                                        var others = $("#amount_others").val();

                                        if (amount !== "") {
                                            if (upkeep !== "") {

                                                if (others !== "") {


                                                    //all is good compare amount to school
                                                    if ((Number(amount) + Number(upkeep) + Number(others)) > pays) {
                                                        swal(
                                                                'Oops...',
                                                                'Amount cannot be greater than sponsor total payments',
                                                                'error'
                                                                );
                                                    } else {

                                                        if (Number(amount) > total) {
                                                            swal(
                                                                    'Oops...',
                                                                    'Amount allocated to fees cannot be greater than the students School fees',
                                                                    'error'
                                                                    );
                                                        } else {

                                                            swal({
                                                                title: 'Confirm before submitting',
                                                                text: "You won't be able to revert this!",
                                                                type: 'warning',
                                                                showCancelButton: true,
                                                                confirmButtonColor: '#3085d6',
                                                                cancelButtonColor: '#d33',
                                                                confirmButtonText: 'Confirm'
                                                            }).then(function () {
                                                                //all is good perfom ajax
                                                                $.ajax({
                                                                    type: 'POST',
                                                                    data: $("#allocation_table").serialize(),
                                                                    url: "AccStudentAllocation?id=" + stud_id + "&sponsor=" + sponsor_id,
                                                                    success: function (result) {
                                                                        // $('#admin_feedb').html(result).show().delay(3000).fadeOut('slow');
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
                                                        }
                                                    }

                                                } else {
                                                    swal(
                                                            'Oops...',
                                                            'Enter amount to allocate to others',
                                                            'error'
                                                            );
                                                }

                                            } else {
                                                swal(
                                                        'Oops...',
                                                        'Enter amount to allocate to upkeep',
                                                        'error'
                                                        );
                                            }
                                        } else {
                                            swal(
                                                    'Oops...',
                                                    'Enter amount to allocate to school',
                                                    'error'
                                                    );
                                            return false;
                                        }
                                    });


                                });

                                function allocateAdditional() {
                                    swal({
                                        title: 'Additional Allocation',
                                        html:   '<form id="student_additional_allocation">'+
                                                '<div style="margin:4%;">' +
                                                '<div class="input-field col s12">' +
                                                '<input name="add_school" id="add_school" type="number" class="validate">' +
                                                '<label data-error="Not a number" for="add_school">Amount to school</label>' +
                                                '</div>' +
                                                '<div class="input-field col s12">' +
                                                '<input name="add_upkeep" id="add_upkeep" type="number" class="validate">' +
                                                '<label data-error="Not a number" for="add_upkeep">Amount to Upkeep</label>' +
                                                '</div>' +
                                                '<div class="input-field col s12">' +
                                                '<input name="add_other" id="add_others" type="number" class="validate">' +
                                                '<label data-error="Not a number" for="add_others">Amount to Others</label>' +
                                                '</div>' +
                                                '</div>'+
                                                '</form>',
                                        preConfirm: function () {
                                            return new Promise(function (resolve) {
                                                resolve([
                                                    $('#add_school').val(),
                                                    $('#add_upkeep').val(),
                                                    $('#add_others').val()
                                                ]);
                                            });
                                        },
                                        onOpen: function () {
                                            $('#add_school').focus();
                                        }
                                    }).then(function (result) {
                                        // swal(JSON.result[0], JSON.result[1]);
                                        var add_skul = result[0];
                                        var add_upkeep = result[1];
                                        var add_other = result[2];

                                        if (isNaN(add_skul) && isNaN(add_upkeep) && isNaN(add_other)) {
                                            swal(
                                                    'Oops...',
                                                    'Wrong number inputs',
                                                    'error'
                                                    );
                                        } else {
                                            if (add_skul === "" && add_upkeep === "" && add_other === "") {
                                                swal(
                                                        'Oops...',
                                                        'Fields cannot be empty',
                                                        'error'
                                                        );
                                            } else {
                                                //check if total is above bal
                                                var t = +add_skul + +add_upkeep + +add_other;
                                                if (t > bal) {
                                                    swal(
                                                            'Oops...',
                                                            'Amount indicated is above sponsor acc balance'+t,
                                                            'error'
                                                            );
                                                } else {

                                                    //submit to server...
                                                      //all is good perfom ajax
                                                                $.ajax({
                                                                    type: 'POST',
                                                                    data: $("#student_additional_allocation").serialize(),
                                                                    url: "AccStudentAdditional?id=" + stud_id + "&sponsor=" + sponsor_id + "&fees=" + total,
                                                                    success: function (result) {
                                                                        // $('#admin_feedb').html(result).show().delay(3000).fadeOut('slow');
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

                                                }
                                            }
                                        }

                                    }).catch(swal.noop);
                                }
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
                            <input  id="serch_student_field" name="serch_student_field" type="text" class="validate" onkeyup="studentFinance()">
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

<!-- Modal Structure For assigning accounts-->
<div id="modal45" class="modal modal-lg modal-fixed-footer" data-backdrop="static" data-keyboard="false" style="height: 90%; width: 80%; max-height: 100%; top: 5%; bottom: 5%;">

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
                                    <th>Sponsor Payments</th>
                                    <th>Sponsor Used</th>
                                    <th>Sponsor Balance</th>
                                </tr>
                            </thead>

                        </table>
                    </div>


                </div>
            </div>

        </div>
        <div class="row">
            <form id="allocation_table">
                <div class="col s6 offset-l3">
                    <div class="row alert alert-info">
                        <h6>Allocation details</h6>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="amount_school" id="amount_school" type="number" class="">
                            <label for="amount_school">Amount to School Fees</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="amount_upkeep" id="amount_upkeep" type="number" class="">
                            <label for="number">Amount to upkeep</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="amount_others" id="amount_others" type="number" class="">
                            <label for="email">Amount to others</label>
                        </div>
                    </div>
                    <div class="row col s6 offset-l4">
                        <button id="alocate_btn" style="width: 50%;" class="btn btn-large waves-effect waves-light" type="button" name="action">Save
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                    <div class="row col s6 offset-l4">
                        <a href="#!" style="width: 50%;" class=" btn-large modal-action modal-close waves-effect waves-green btn-flat ">close</a>
                    </div>
                </div>
            </form>
        </div>

        <div class="row">
            <div class="col s6 offset-l4">

            </div>
        </div>
    </div>
</div>
</body>
</html>
