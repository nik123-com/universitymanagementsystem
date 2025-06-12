package university.management.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection (adjust username and password if needed)
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "");

            // Create Statement object for executing SQL
            s = c.createStatement();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to DB failed");
            e.printStackTrace();
        }
    }
}
