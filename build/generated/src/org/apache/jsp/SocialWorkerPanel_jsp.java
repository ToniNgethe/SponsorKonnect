package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import Model.SocialLoginModel;

public final class SocialWorkerPanel_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>SponsorKonnect || Social Worker Panel</title>\n");
      out.write("        <link href=\"iconfont/material-icons.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("        <link href=\"css/demo_table.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- Compiled and minified CSS -->\n");
      out.write("        <script src=\"js/jquery-1.12.4.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link href=\"css/materialize.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- Compiled and minified JavaScript -->\n");
      out.write("\n");
      out.write("        <script src=\"js/materialize.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery.dataTables.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"js/social.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");

            SocialLoginModel sm = null;
            String email = null;
            //check for session
            if (session.getAttribute("SOCIAL_WORKER") == null || session.getAttribute("SOCIAL_WORKER") == "") {
                response.sendRedirect("SocialWorkerLogin.jsp");
            } else {
                sm = (SocialLoginModel) session.getAttribute("SOCIAL_WORKER");
                email = sm.getEmail();
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <nav class=\"nav-extended teal\">\n");
      out.write("            <div class=\"nav-wrapper\">\n");
      out.write("                <a href=\"#\" class=\"brand-logo\">SpnsorKonnect</a>\n");
      out.write("                <a href=\"#\" data-activates=\"mobile-demo\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\n");
      out.write("                <ul id=\"nav-mobile\" class=\"right hide-on-med-and-down\">\n");
      out.write("                    <li><a href=\"sass.html\">Sass</a></li>\n");
      out.write("                    <li><a href=\"badges.html\">Components</a></li>\n");
      out.write("                    <li><a href=\"collapsible.html\">JavaScript</a></li>\n");
      out.write("                </ul>\n");
      out.write("                <ul class=\"side-nav\" id=\"mobile-demo\">\n");
      out.write("                    <li><a href=\"sass.html\">Sass</a></li>\n");
      out.write("                    <li><a href=\"badges.html\">Components</a></li>\n");
      out.write("                    <li><a href=\"collapsible.html\">JavaScript</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-content\">\n");
      out.write("                <ul class=\"tabs tabs-transparent\">\n");
      out.write("\n");
      out.write("                    <li class=\"tab\"><a class=\"active\" href=\"#file_report\"><i class=\"material-icons left\">content_paste</i>File Student Report</a></li>\n");
      out.write("                    <li class=\"tab\"><a href=\"#my_reports\"><i class=\"material-icons left\">library_books</i>My Reports</a></li>\n");
      out.write("\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!--        FILE REPORT SECTION-->\n");
      out.write("        <div id=\"file_report\" class=\"col s12\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("\n");
      out.write("                <div class=\"row\" style=\"margin-top: 5%;\">\n");
      out.write("                    <div class=\"col s12\">\n");
      out.write("                        <div class=\"card-title alert alert-info\">\n");
      out.write("                            <h5>Students Assigned to you</h5>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card-panel\">\n");
      out.write("                            <script type=\"text/javascript\" charset=\"utf-8\">\n");
      out.write("\n");
      out.write("                                $(document).ready(function () {\n");
      out.write("                                    var table = $(\"#assigned_students\").DataTable({\n");
      out.write("                                        \"bProcessing\": false,\n");
      out.write("                                        \"bServerSide\": false,\n");
      out.write("                                        \"sAjaxSource\": \"SocialWorkerStudents?email=");
      out.print(email);
      out.write("\",\n");
      out.write("                                        \"bJQueryUI\": true,\n");
      out.write("                                        \"aoColumns\": [\n");
      out.write("                                            {\"mData\": \"stud_id\"},\n");
      out.write("                                            {\"mData\": \"s_name\"},\n");
      out.write("                                            {\"mData\": \"f_name\"},\n");
      out.write("                                            {\"mData\": \"l_name\"},\n");
      out.write("                                            {\"mData\": \"number\"},\n");
      out.write("                                            {\"mData\": \"location\"},\n");
      out.write("                                            {\"mData\": \"age\"},\n");
      out.write("                                            {\"mData\": \"gender\"},\n");
      out.write("                                            {\"mData\": \"button\"}\n");
      out.write("                                        ]\n");
      out.write("                                    });\n");
      out.write("\n");
      out.write("\n");
      out.write("                                    $('#assigned_students tbody').on('click', 'tr', function () {\n");
      out.write("                                        var data = table.row(this).data();\n");
      out.write("                                        //alert('You clicked on ' + data.f_name + '\\'s row');\n");
      out.write("                                        document.getElementById('selected_stud_id').value = data.stud_id;\n");
      out.write("                                        document.getElementById('stud_first_name').value = data.f_name;\n");
      out.write("                                        document.getElementById('stud_last_name').value = data.l_name;\n");
      out.write("                                    });\n");
      out.write("                                });\n");
      out.write("\n");
      out.write("                            </script>\n");
      out.write("\n");
      out.write("                            <div id=\"dynamic\">\n");
      out.write("                                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"table table-bordered table-responsive\"\n");
      out.write("                                       id=\"assigned_students\">\n");
      out.write("                                    <thead>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <th >Student ID</th>\n");
      out.write("                                            <th >Surname</th>\n");
      out.write("                                            <th>First Name</th>\n");
      out.write("                                            <th >Last Name</th>\n");
      out.write("                                            <th >Number</th>\n");
      out.write("                                            <th>Location</th>\n");
      out.write("                                            <th >Age</th>\n");
      out.write("                                            <th>Gender</th>\n");
      out.write("                                            <th width> Add Report</th>\n");
      out.write("                                        </tr>\n");
      out.write("                                    </thead>\n");
      out.write("                                </table>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!--        REPORT RECORDS SECTION-->\n");
      out.write("        <div id=\"my_reports\" class=\"col s12\">\n");
      out.write("            <form id=\"searchstudent_report\" name=\"searchstudent_report\">\n");
      out.write("                <div class=\"container\">\n");
      out.write("                    <div class=\"row\" style=\"margin-top: 5%;\">\n");
      out.write("                        <div class=\"input-field col s6 offset-l2\">\n");
      out.write("                            <i class=\"material-icons prefix\">search</i>\n");
      out.write("                            <input id=\"serch_student_field\" name=\"serch_student_field\" type=\"text\" class=\"validate\" onkeyup=\"studentReport()\">\n");
      out.write("                            <label for=\"serch_student_field\">Search Student by id</label>\n");
      out.write("                        </div>       \n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div id=\"feedback_area\" class=\"col s6 offset-l2\">\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div> \n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Modal Trigger -->\n");
      out.write("<!--        <a class=\"modal-trigger waves-effect waves-light btn\" href=\"#modal1\">Modal</a>-->\n");
      out.write("\n");
      out.write("<!-- Modal Structure For filing reports-->\n");
      out.write("<div id=\"modal1\" class=\"modal modal-fixed-footer\" data-backdrop=\"static\" data-keyboard=\"false\">\n");
      out.write("    <form id=\"selcted_student_form\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"row alert alert-info\">\n");
      out.write("                <h6>Social Assesment For <div class=\"selected_stud_name\"></div><div class=\"selected_stud_name_2\"></div></h6>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s6\">\n");
      out.write("                    <input disabled=\"\" placeholder=\"Placeholder\" id=\"stud_first_name\" type=\"text\"  >\n");
      out.write("                    <label for=\"first_name\" class=\"active\">First Name</label>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"input-field col s6\">\n");
      out.write("                    <input disabled id=\"stud_last_name\" type=\"text\" class=\"validate\" placeholder=\"Placeholder\">\n");
      out.write("                    <label for=\"last_name\" class=\"active\">Last Name</label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s8\">\n");
      out.write("                    <input name=\"social_worker\" id=\"social_worker\" value=\"");
      out.print(email);
      out.write("\" type=\"hidden\">\n");
      out.write("                    <input name=\"selected_stud_id\" id=\"selected_stud_id\" type=\"hidden\">\n");
      out.write("                    <i class=\"material-icons prefix\">today</i>\n");
      out.write("                    <input name=\"dateOfVisit\" id=\"dateOfVisit\" type=\"date\" class=\"datepicker\">\n");
      out.write("                    <label class=\"active\" for=\"dateOfVisit\">Date of visit</label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s12\">\n");
      out.write("                    <i class=\"material-icons prefix\">mode_edit</i>\n");
      out.write("                    <textarea name=\"students_family_bg\" id=\"students_family_bg\" class=\"materialize-textarea\"></textarea>\n");
      out.write("                    <label for=\"students_family_bg\">Student’s family background Condition</label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s12\">\n");
      out.write("                    <i class=\"material-icons prefix\">mode_edit</i>\n");
      out.write("                    <textarea name=\"current_compo\" id=\"icon_prefix2\" class=\"materialize-textarea\"></textarea>\n");
      out.write("                    <label for=\"icon_prefix2\">Current family composition and/or household membership</label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s12\">\n");
      out.write("                    <i class=\"material-icons prefix\">mode_edit</i>\n");
      out.write("                    <textarea name=\"student_ethnicity\" id=\"icon_prefix2\" class=\"materialize-textarea\"></textarea>\n");
      out.write("                    <label for=\"icon_prefix2\">Ethnicity, religion, and spirituality </label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"input-field col s12\">\n");
      out.write("                    <i class=\"material-icons prefix\">accessible</i>\n");
      out.write("                    <textarea name=\"student_health\" id=\"icon_prefix2\" class=\"materialize-textarea\"></textarea>\n");
      out.write("                    <label for=\"icon_prefix2\">Physical functioning, health concerns, illness, disabilities, medications</label>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div class=\"modal-footer\">\n");
      out.write("            <button id=\"btn_submit_report\" class=\"btn waves-effect waves-light\" type=\"button\" name=\"action\">Submit\n");
      out.write("                <i class=\"material-icons right\">send</i> </button>\n");
      out.write("            <a href=\"#!\" class=\"modal-action modal-close waves-effect waves-green btn-flat \">close</a>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
