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
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {

       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //get email and password variables from index.html
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //create new user object with email and password (Using constructor with email and password from users class)
        Users users = new Users(email, password);
        
        DAO dao = new DAO();

        String role = null;
        try {
            role = dao.authenticateUser(users);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        switch (role) {
            case "Administrator": {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("adminHomePageServlet");
                break;
            }
            case "user": {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("regUserHomePageServlet");
                break;
            }
    }

    }
}
   

