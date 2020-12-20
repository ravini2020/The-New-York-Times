/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author MyCom
 */
public class logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        session.invalidate();

        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"CSS/messageBox.css\" />");

        out.println("<div class=\"alert\">");
        out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
        out.println("<strong>Logged out successfully</strong>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        request.getRequestDispatcher("index.html").include(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
