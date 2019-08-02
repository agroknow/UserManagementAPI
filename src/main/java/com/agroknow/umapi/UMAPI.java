package com.agroknow.umapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UMAPI {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/foodakai", "root", "mihalis");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from taxonomy_term_data");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
