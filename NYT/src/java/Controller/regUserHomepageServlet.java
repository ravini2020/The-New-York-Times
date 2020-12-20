/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Book;
import Model.DAO;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;




public class regUserHomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String command = request.getParameter("command");
            if (command == null) {
                command = "VIEWLIST";
            }
            switch (command) {

                    case "VIEWLIST":
                    viewList(request, response);
                    break;
                    
                    case "VIEWSINGLEBOOK":
                    viewSingleBook(request, response);
                    break;
                    
                    default:
                    viewList(request, response);
                    break;
            }
            

        } catch (Exception ex) {
            System.out.println(ex);
        }
    
    }
    private void viewList(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            DAO dao = new DAO();
            
            Users user = dao.userDetails(email);
            String firstName = user.getFirstname();
            String lastName = user.getLastname();
            
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            
            session.setAttribute("email", email);
            
            List<Book> book = dao.bookDetails();
            request.setAttribute("BOOKS", book);
            
             RequestDispatcher dispatcher = request.getRequestDispatcher("regUserHomePage.jsp");
            dispatcher.include(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    

    private void viewSingleBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            DAO dao = new DAO();
            
            Users user = dao.userDetails(email);
            String firstName = user.getFirstname();
            String lastName = user.getLastname();
            
             request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);

            session.setAttribute("email", email);

            int ID = Integer.parseInt(request.getParameter("ID"));
            List<Book> book = dao.singleBook(ID);
            request.setAttribute("SINGLE", book);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewSingleBook.jsp");
            dispatcher.include(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
