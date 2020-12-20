/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    
        private final Connection connection;
        
        public DAO() {
        connection = Database.getConnection();
    }
        
        public String authenticateUser(Users User) {
        String email = User.getEmail();
        String password = User.getPassword();

        try {
            PreparedStatement ps = connection.prepareStatement("select email,password,role from users");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String EMAIL = rs.getString("email");
                String PASSWORD = rs.getString("password");
                String ROLE = rs.getString("role");


                if (email.equals(EMAIL)&& password.equals(PASSWORD) && ROLE.equals("Administrator")  ) {
                    return "Administrator";
                } else if (email.equals(EMAIL) && password.equals(PASSWORD) && ROLE.equals("user")  ) {
                    return "user";
                }
            }
        } catch (SQLException e) {
        }
        return "invalid";
    }

    public boolean existingUser(String email) {
        boolean user = false;

        try {
            PreparedStatement ps = connection.prepareStatement("select email from users where email=?");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            user = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void registerUser(Users User) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users(firstname, lastname, email, password, role)"
                    + "values (?, ?, ?, ?, ?)");
            ps.setString(1, User.getFirstname());
            ps.setString(2, User.getLastname());
            ps.setString(4, User.getEmail());
            ps.setString(5, User.getPassword());
            ps.setString(6, User.getRole());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users userDetails(String email) {
     Users user = null;
     try {
            PreparedStatement ps = connection.prepareStatement("select firstname, lastname from users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String FNAME = rs.getString("firstName");
                String LNAME = rs.getString("lastName");

                //set user details using the email,firstName,lastName constructor in users class
                user = new Users(FNAME, LNAME);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public List<Book> bookDetails() {
        List<Book> bookDetails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select ID ,title, author from books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ID = rs.getString("ID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                

                Book book = new Book(Integer.parseInt(ID), title, author);
                bookDetails.add(book);

            }
        } catch (Exception e) {
            System.out.println("Hit Error Here: " + e);
        }
        return bookDetails;
    }

    public List<Book> singleBook(int ID) {
        List<Book> bookDetails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select ID ,title, author from books where ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String BID = rs.getString("ID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                

                Book book = new Book(Integer.parseInt(BID), title, author);
                bookDetails.add(book);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return bookDetails;
    }
    
}
