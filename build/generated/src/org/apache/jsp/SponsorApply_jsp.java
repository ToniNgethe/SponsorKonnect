package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class SponsorApply_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }

        
      out.write("\n");
      out.write("        <link href=\"iconfont/material-icons.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/icons.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Sponsor </title>\n");
      out.write("        <link href=\"css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/sponsor.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/materialize.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/sweetalert2.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- Compiled and minified CSS -->\n");
      out.write("        <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <!-- Compiled and minified JavaScript -->\n");
      out.write("        <script src=\"js/materialize.js\" type=\"text/javascript\"></script>\n");
      out.write("     \n");
      out.write("        <script src=\"js/sweetalert2.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body class=\"sp\">\n");
      out.write("        <nav style=\"background-color: white;\">\n");
      out.write("            <div style class=\"nav-wrapper\">\n");
      out.write("                <a href=\"../intro/index.jsp\" style=\"color: black;\" class=\"brand-logo\">SponsorKonnect</a>\n");
      out.write("                <ul id=\"nav-mobile\" class=\"right hide-on-med-and-down\">\n");
      out.write("\n");
      out.write("                    <li><a style=\"color: black;\" href=\"Register.jsp\"></a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"section\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row center\">\n");
      out.write("                    <div class=\"col s12\">\n");
      out.write("                        <div class=\"alert alert-info\" style=\"margin-top: 5%;\">\n");
      out.write("                            <h5>Give us your details and will get back to you</h5>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"card\">\n");
      out.write("                            <div class=\"card-title\">\n");
      out.write("                                <img src=\"img/sponsor_logo.png\" alt=\"\" style=\"margin-bottom: -98px; width: 295px; margin-top: -58px;\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"card-content\">\n");
      out.write("\n");
      out.write("                                <form id=\"new_sponsor_info\" method=\"post\">\n");
      out.write("                                    <div id=\"new_sponsor_fb\"></div>\n");
      out.write("\n");
      out.write("                                    <div class=\"col s12\">\n");
      out.write("                                        <div class=\"row\">\n");
      out.write("                                            <div class=\"col s6\">\n");
      out.write("                                                <div class=\"\">\n");
      out.write("                                                    <div class=\"\" style=\"margin: 5%;\">\n");
      out.write("                                                        <div class=\"row\">\n");
      out.write("                                                            <script>\n");
      out.write("                                                                $(document).ready(function () {\n");
      out.write("\n");
      out.write("                                                                    document.getElementById('new_sponsor_mobile').addEventListener('input', function (e) {\n");
      out.write("                                                                        var x = e.target.value.replace(/\\D/g, '').match(/(\\d{0,3})(\\d{0,3})(\\d{0,4})/);\n");
      out.write("                                                                        e.target.value = !x[2] ? x[1] : '(' + x[1] + ') ' + x[2] + (x[3] ? '-' + x[3] : '');\n");
      out.write("                                                                    });\n");
      out.write("\n");
      out.write("\n");
      out.write("                                                                    $(\"#btn_add_newSponsor\").click(function () {\n");
      out.write("                                                                        event.preventDefault();\n");
      out.write("                                                                        $.ajax({\n");
      out.write("                                                                            type: 'POST',\n");
      out.write("                                                                            data: $(\"#new_sponsor_info\").serialize(),\n");
      out.write("                                                                            url: \"NewSponsorApplication\",\n");
      out.write("                                                                            success: function (result) {\n");
      out.write("                                                                                swal(\n");
      out.write("                                                                                        'Server Feedback',\n");
      out.write("                                                                                        result,\n");
      out.write("                                                                                        'info'\n");
      out.write("                                                                                        );\n");
      out.write("                                                                            },\n");
      out.write("                                                                            error: function (result) {\n");
      out.write("                                                                                alert(\"error\" + result);\n");
      out.write("                                                                            }\n");
      out.write("\n");
      out.write("                                                                        });\n");
      out.write("                                                                    });\n");
      out.write("\n");
      out.write("                                                                });\n");
      out.write("                                                            </script>\n");
      out.write("                                                            <div class=\"input-field col s12\">\n");
      out.write("                                                                <input required=\"true\" name=\"new_sponsor_name\" id=\"sponsor_name\" type=\"text\" class=\"validate\">\n");
      out.write("                                                                <label for=\"sponsor_name\">Your Names</label>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <div class=\"row\">\n");
      out.write("                                                            <div class=\"input-field col s12\">\n");
      out.write("                                                                <input required=\"true\" name=\"new_sponsor_mobile\" id=\"new_sponsor_mobile\" type=\"text\" class=\"validate\" placeholder=\"(072) 999-9999\">\n");
      out.write("                                                                <label data-error=\"Not a number\" for=\"sponsor_number\">Your Mobile Number</label>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <div class=\"row\">\n");
      out.write("                                                            <div class=\"input-field col s12\">\n");
      out.write("                                                                <input required=\"true\" name=\"new_sponsor_email\" id=\"sponsor_email\" type=\"email\" class=\"validate\">\n");
      out.write("                                                                <label data-error=\"Invalid Email\" for=\"sponsor_email\">Your Email</label>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>\n");
      out.write("                                            </div>\n");
      out.write("                                            <div class=\"col s6\">\n");
      out.write("                                                <div class=\"row\">                                 \n");
      out.write("                                                    <div class=\"input-field col s12\">\n");
      out.write("                                                        <select name=\"sponsor_means\">\n");
      out.write("                                                            <option value=\"\" disabled selected>Choose your communication means</option>\n");
      out.write("                                                            <option value=\"email\">Email</option>\n");
      out.write("                                                            <option value=\"phonenumber\">Phone Number</option>\n");
      out.write("                                                            <option value=\"skype\">Skype</option>\n");
      out.write("                                                        </select>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>\n");
      out.write("                                                <div class=\"row\">\n");
      out.write("                                                    <div class=\"input-field col s12\">\n");
      out.write("                                                        <select name=\"sponsor_type\">\n");
      out.write("                                                            <option value=\"\" disabled selected>Type of Sponsorship</option>\n");
      out.write("                                                            <option value=\"primary\">Primary</option>\n");
      out.write("                                                            <option value=\"secondary\">Secondary</option>\n");
      out.write("                                                            <option value=\"vocational\">Vocational</option>\n");
      out.write("                                                        </select>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>\n");
      out.write("                                                <div class=\"row\">\n");
      out.write("                                                    <div class=\"input-field col s12\">\n");
      out.write("                                                        <input required=\"true\" name=\"new_sponsor_company\" id=\"sponsor_company\" type=\"text\" class=\"validate\">\n");
      out.write("                                                        <label  for=\"sponsor_company\">Your Company</label>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>\n");
      out.write("\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>  \n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"row \">\n");
      out.write("                                        <div class=\"col s6 offset-l4\">\n");
      out.write("                                            <button id=\"btn_add_newSponsor\" style=\"width: 50%;\" class=\"btn waves-effect waves-light btn-large\" type=\"submit\" name=\"action\">Submit\n");
      out.write("                                                <i class=\"material-icons right\">send</i>\n");
      out.write("                                            </button>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!-- Modal Structure -->\n");
      out.write("                <div id=\"modal1\" class=\"modal\">\n");
      out.write("                    <div class=\"modal-content\">\n");
      out.write("\n");
      out.write("                        <div class=\"input-field col s6 offset-l3\">\n");
      out.write("\n");
      out.write("                            <input id=\"email\" type=\"email\" required=\"true\" class=\"validate\" name=\"student_email_reset\">\n");
      out.write("                            <label for=\"email\" data-error=\"Wrong email format\">Enter email to send password</label>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"modal-footer\">\n");
      out.write("                        <a href=\"#!\" class=\"modal-action modal-close waves-effect waves-green btn-flat\">Reset</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
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
