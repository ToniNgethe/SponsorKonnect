<%-- 
    Document   : StudentPanel
    Created on : Apr 22, 2017, 4:35:15 PM
    Author     : toni
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
        <link href="css/sweetalert2.css" rel="stylesheet" type="text/css"/>
        <link href="iconfont/material-icons.css" rel="stylesheet" type="text/css"/>
        <!-- Compiled and minified JavaScript -->
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script src="js/sweetalert2.js" type="text/javascript"></script>
        <script src="js/custom.js" type="text/javascript"></script>
    </head>
    <body>

        <% StudentModel studentModel = null;
            String id = null;
            String email = null;
            String pass = null;

            try {

                if (session.getAttribute("STUDENT_MODEL") == null || session.getAttribute("STUDENT_MODEL") == "") {
                    response.sendRedirect("login.jsp");
                } else {
                    studentModel = (StudentModel) session.getAttribute("STUDENT_MODEL");
                    id = request.getParameter("id");
                    email = studentModel.getEmail();
                    pass = studentModel.getPass();
                    if (studentModel.getEmail() == null) {
                        response.sendRedirect("login.jsp");
                    }

                }
            } catch (NullPointerException e) {
                response.sendRedirect("login.jsp");
            }

        %>


        <!-- Dropdown Structure -->

        <nav>
            <div class="nav-wrapper">
                <a href="#!" class="brand-logo" style="margin-top: -3%;" > <img src="img/sponsor_logo.png" alt=""/></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">         
                    <li><a href="./StudentLogout">Log Out</a></li>
                </ul>
            </div>
        </nav>


        <div class="container" style="margin-top: 1%;">
            <div class="row center">
                <div class="col s12 ">
                    <div class="">
                        <div class="">

                            <img src="ImageServlet?id=<%=id%>" class="circle" alt="" style="width: 170px; height: 170px;"/>
                            <p class="teal-text text-lighten-1"><%= email%></p>

                            <div id="continur_msg" style="margin-top: 10px;"><%= pass%></div>
                        </div>
                        <div class="" style="margin-top: 2%;">
                            <ul class="tabs tabs-fixed-width">
                                <li class="tab"><a class="active" href="#personal">Personal Information</a></li>
                                <li class="tab"><a href="#parental">Parental Information</a></li>
                                <li class="tab"><a href="#sibling">Sibling Information</a></li>
                                <li class="tab"><a href="#school">School Information</a></li>
                            </ul>
                        </div>

                        <div class="" style="margin-top: 2%;">
                            <div id="personal">
                                <form id="personalDetailsTable" action="./StudentPersonalServlet" method="POST" enctype="multipart/form-data">       
                                    <div class="row">
                                        <div class="col s6 offset-l3" id="err_p">

                                        </div>
                                        <div class="col s12">

                                            <div class="col s6">
                                                <div class="card  black-text">
                                                    <div class="card-content">
                                                        <div class="row">
                                                            <div class="input-field">
                                                                <input name="id" id="stud_surname" type="hidden" value="<%=id%>" />
                                                                <input required="true" name="stud_surname" id="stud_surname" type="text" class="validate" >
                                                                <label for="surname">Surnanme </label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field">
                                                                <input required="true" name="first_name" id="first_name" type="text" class="validate">
                                                                <label for="first_name">First Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field  ">
                                                                <input required="true" name="last_name" id="last_name" type="text" class="validate">
                                                                <label for="last_name">Last Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field">
                                                                <select name="stud_gender">
                                                                    <option value="" disabled selected>Choose your option</option>
                                                                    <option value="Male">Male</option>
                                                                    <option value="Female">Female</option>
                                                                </select>
                                                                <label>Gender</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col s6">
                                                <div class="card white">
                                                    <div class="card-content black-text">
                                                        <div class="row">

                                                            <div class="row">
                                                                <div class="input-field  ">
                                                                    <input required="true" name="mobile" id="mobile" type="text" placeholder="(072) 666-6666">
                                                                    <label data-error="Not a number" for="mobile">Phone Number</label>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <input required="true" name="famLocation" id="fam_loc" type="text" class="validate">
                                                                    <label for="fam_loc">Family Location</label>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <input required="true" name="dob" id="dob" type="date" class="datepicker" >
                                                                    <label for="fam_loc" class="active">Date of birth</label>
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                        </div>

                                    </div>
                                    <div class="row center">
                                        <button id="save_personal" style="width: 40%;" type="button" class="btn btn-large waves-effect waves-light"> <i class="material-icons right">save</i>Save Personal Details</button>
                                    </div>
                                </form>
                            </div>

                            <div id="parental">
                                <form id="parentalDetails" action="./StudentParentalServlets" method="POST">
                                    <div class="row">
                                        <div class="col s12">
                                            <div class="col s6 offset-l3" id="parent_feedb">

                                            </div>
                                            <div class="col s6">
                                                <div class="card  black-text">
                                                    <div class="card-content">
                                                        <div class="row">
                                                            <div class="input-field">
                                                                <input name="p_id" id="stud_surname" type="hidden" value="<%=id%>" />
                                                                <input name="father_name" required="true" id="father_name" type="text" class="validate">
                                                                <label for="father_name">Father Name </label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field">
                                                                <input name="father_occupation" required="true" id="father_occupation" type="text" class="validate">
                                                                <label for="father_occupation">Father Occupation</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field  ">
                                                                <input name="f_mobile" required="true" id="f_mobile" type="text" placeholder="(072) 666-6666" >
                                                                <label  data-error="Not a number" for="f_mobile">Mobile Number</label>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col s6">
                                                <div class="card white">
                                                    <div class="card-content black-text">
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="mother_name" required="true" id="mother_name" type="text" class="validate">
                                                                <label for="mother_name">Mother Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="mother_occupation" required="true" id="mother_occupation" type="text" class="validate">
                                                                <label for="mother_occupation">Mother Occupation</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="m_mobile" required="true" id="m_mobile" type="text" placeholder="(072) 666-6666">
                                                                <label data-error="Not a number " for="m_mobile">Mobile Number</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row center">
                                        <div>
                                            <!-- Modal Trigger -->
                                            <button id="btn_saveparents" class="waves-effect waves-light btn"><i class="material-icons right">save</i>Save Parent Details</button>
                                        </div>
                                    </div>
                                </form>
                                <div class="row center">
                                    <div>
                                        <!-- Modal Trigger -->
                                        <a class="waves-effect waves-light" href="#addGurdian">Add Gurdian Instead</a>
                                    </div>
                                </div>


                            </div>



                            <!-- Modal Structure -->
                            <div id="addGurdian" class="modal myModelStyle white" style="max-width:  550px;">
                                <div class="modal-content">
                                    <h4>Gurdian Details</h4>
                                    <div class="row">
                                        <div class="col s12">
                                            <div class=" ">
                                                <div class="black-text">
                                                    <form id="studentGurdianDetails" action="./StudentGurdianServlet" method="post">
                                                        <div  id="g_feedb">

                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="g_id" id="stud_surname" type="hidden" value="<%=id%>" />
                                                                <input name="g_name" id="g_name" type="text" class="validate">
                                                                <label for="g_name">Gurdian Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="g_occupation" id="g_occupation" type="text" class="validate">
                                                                <label for="g_occupation">Gurdian Occupation</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input name="g_number" id="g_number" type="text" placeholder="(072) 666-6666">
                                                                <label data-error="Not a number" for="g_number">Gurdian Number</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <button id="btn_gurdian" type="button" class="validate btn btn-info"><i class="material-icons right">save</i>Save gurdian details</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Close</a>
                                </div>
                            </div>




                            <div id="sibling">
                                <div class="row">
                                    <div class="col s6 offset-l3">
                                        <form action="./StudentSiblingServlet" id="siblingTable" method="post">
                                            <div  id="s_feedb">
                                            </div>
                                            <div class="card  black-text">
                                                <div class="card-content">
                                                    <div class="row">
                                                        <div class="input-field">
                                                            <input name="s_id" id="stud_surname" type="hidden" value="<%=id%>" />
                                                            <input name="sibling_name" id="sibling_name" type="text" class="validate">
                                                            <label for="sibling_name">Sibling Full Name </label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field">

                                                            <select name="sibling_edu" >
                                                                <option value="" disabled selected>Choose your option</option>
                                                                <option value="Primary">Primary</option>
                                                                <option value="Secondary">Secondary</option>
                                                                <option value="Unversity">University</option>

                                                            </select>
                                                            <label>Sibling Education Level</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field">
                                                            <input name="sibling_school" id="sibling_school" type="text" class="validate" placeholder="Write null if not in school">
                                                            <label for="sibling_school">Sibling School</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field">
                                                            <input name="sibling_age" id="sibling_age" type="number" class="validate">
                                                            <label data-error="Not a number" for="sibling_age">Age</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <button id="btn_sibling" style="width: 50%;" class="btn btn-large btn-success waves-effect waves-light" type="button"><i class="material-icons right">save</i>Save sibling</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div id="school">
                                <div class="row">
                                    <div class="col s8 offset-l2">
                                        <div id="selectedFB">

                                        </div>
                                        <form id="selectedSchoolForm">
                                            <div class="row">

                                                <div style="margin: 5%;">
                                                    <%
                                                        Connection conn = DBUtils.DBUtil.getConnection();
                                                        String query = "SELECT name FROM school";
                                                        PreparedStatement pst = conn.prepareStatement(query);

                                                        ResultSet rs = pst.executeQuery();

                                                    %>
                                                    <label>Choose your School</label>
                                                    <select id="selectedSchool" name="selectedSchool">
                                                        <option value="" disabled selected>Choose your option</option>
                                                        <%  while (rs.next()) {%>
                                                        <option value="<%= rs.getString(1)%>"><%= rs.getString(1)%></option>
                                                        <% }%>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <input name="student_id" id="stud_surname" type="hidden" value="<%=id%>" />
                                            </div>
                                            <div id="selcetdSchoolInfo" class="row">

                                            </div>
                                            <div id ="schoolDetails" class="row">
                                                <div class='row'>
                                                    <div class='input-field col s12'>
                                                        <input name='selected_school_class' id='school_class' type='number' class='validate'>
                                                        <label for='school_class'>Class|Form|University level </label>
                                                    </div>
                                                </div>

                                                <div class='row'>
                                                    <div class='input-field col s12'>
                                                        <input name='selected_school_reg' id='school_reg' type='text' class=''>
                                                        <label for='school_reg'>Registration Number</label>
                                                    </div>
                                                </div>

                                                <div class='row'>
                                                    <div class='input-field col s12'>
                                                        <button id='btnSelectedSchool' type='button' class='btn btn-large waves-effect waves-light validate'>Save details</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">

                                                <div class="alert alert-info">
                                                    <h6>Not Found your school ? Click <a href="#extraSchools">here</a></h6>
                                                </div>

                                            </div>
                                        </form>
                                    </div>

                                </div>  
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>



        <!-- Modal Structure for Schools -->
        <div id="extraSchools" class="modal myModelStyle white" style="max-width: 500px;">
            <form id="schoolInfo" action="./StudentSchoolServlet" method="post">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <h6>Give us your School Information will get back to you</h6>
                    </div>
                </div>
                <div class="modal-content ">
                    <div class="col s6" style="margin: 2%;">
                        <div class="card-content">
                            <div  id="skul_feedb">
                            </div>
                            <div class="row">
                                <div class="input-field">
                                    <input name="sib_id" id="stud_surname" type="hidden" value="<%=request.getParameter("id")%>" />
                                    <input name="school_name" id="school_name" type="text" class="validate">
                                    <label for="school_name">School Name</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field">

                                    <select name="school_edu">
                                        <option value="" disabled selected>Choose your option</option>
                                        <option value="Primary">Primary</option>
                                        <option value="Secondary">Secondary</option>
                                        <option value="University">University</option>

                                    </select>
                                    <label>Education Level</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field">

                                    <select name="school_mode">
                                        <option value="" disabled selected>Choose your option</option>
                                        <option value="Private">Private</option>
                                        <option value="Public">Public</option>

                                    </select>
                                    <label>School Mode</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field">

                                    <select name="school_type">
                                        <option value="" disabled selected>Choose your option</option>
                                        <option value="Day">Day</option>
                                        <option value="Boarding">Boarding</option>
                                        <option value="Day&Boarding">Day & Boarding</option>

                                    </select>
                                    <label>School Type</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field  ">
                                    <input name="school_class" id="school_class" type="number" class="validate">
                                    <label for="school_class">Class|Form|University level </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field" >
                                    <div class="">
                                        <button id="btn_school" style="width: 60%; position: relative" type="button" class="btn btn-large btn-success waves-effect waves-light" >  <a class="material-iconss right">save</a> Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
