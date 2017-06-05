package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AdminLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Login </title>\n");
      out.write("        <link href=\"css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- Compiled and minified CSS -->\n");
      out.write("        <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link href=\"css/materialize.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- Compiled and minified JavaScript -->\n");
      out.write("        <script src=\"js/materialize.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/custom.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body >\n");
      out.write("\n");
      out.write("        <div class=\"section center-align\" style=\"margin-top:100dp;\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"row center\">\n");
      out.write("                    <div class=\"col s5 offset-l4\" style=\"margin-bottom:  -8%;\">\n");
      out.write("                        <img src=\"img/sponsor_logo.png\" alt=\"\" style=\"width: 70%;\"/>\n");
      out.write("                    </div>  \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"row center\">\n");
      out.write("                    <div class=\"col s5 offset-l4\">\n");
      out.write("                        <div class=\"card-panel\" >\n");
      out.write("                            <form action=\"./AdminLoginServ\" method=\"POST\" >\n");
      out.write("                                <div class=\"card-content black-text\">\n");
      out.write("                                    <span class=\"card-title\"><h3>Admin Login</h3></span>\n");
      out.write("\n");
      out.write("                                    <div>");
      out.print( msg);
      out.write("</div>\n");
      out.write("\n");
      out.write("                                    <div class=\"col s12 offset-l1\">\n");
      out.write("\n");
      out.write("                                        <div class=\"row \">\n");
      out.write("                                            <div class=\"input-field col s10\">\n");
      out.write("                                                <i class=\"material-icons prefix\">account_circle</i>\n");
      out.write("                                                <input id=\"email\" type=\"email\" required=\"true\" class=\"validate\" name=\"admin_email\">\n");
      out.write("                                                <label for=\"email\" data-error=\"Wrong email format\">Email</label>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"row center\">\n");
      out.write("                                            <div class=\"input-field col s10\">\n");
      out.write("                                                <i class=\"material-icons prefix\">lock</i>\n");
      out.write("                                                <input id=\"pass\" type=\"password\" required=\"true\"class=\"validate\" name=\"admin_pass\">\n");
      out.write("                                                <label for=\"pass\">Password</label>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"card-action\">\n");
      out.write("\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("\n");
      out.write("                                        <input class=\"btn waves-effect waves-light\" type=\"submit\" value=\"Submit\"/>\n");
      out.write("                                    </div>\n");
      out.write("                                    <!-- Modal Trigger -->\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <a class=\"waves-effect waves-light \" href=\"#modal1\">Forgot password?</a>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </form>\n");
      out.write("\n");
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
