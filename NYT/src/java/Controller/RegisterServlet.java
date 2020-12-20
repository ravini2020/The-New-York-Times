/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author MyCom
 */
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "user";


        
        if (dao.existingUser(email)) {
            
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"CSS/errorMessage.css\" />");

            out.println("<div class=\"alert\">");
            out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
            out.println("<strong>User already registered!</strong>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            request.getRequestDispatcher("register.html").include(request, response);
        } else {
            Users User = new Users(firstname, lastname, email, password,role);

            dao.registerUser(User);
           
    }
        out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"CSS/messageBox.css\" />");

            out.println("<div class=\"alert\">");
            out.println("<span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> ");
            out.println("<strong>Registration successful.</strong>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            RequestDispatcher rs = request.getRequestDispatcher("/Login.html");
            rs.include(request, response);

    }
}

