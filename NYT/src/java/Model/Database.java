/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() {
        String URL = "jdbc:mysql://localhost:3306/myrecipes";
        String username = "root";
        String password = "Mysqlpassword123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, username, password);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }
    
}
