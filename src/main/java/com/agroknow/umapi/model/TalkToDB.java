package com.agroknow.umapi.model;

import com.agroknow.umapi.view.User;

import java.sql.*;

public class TalkToDB {
    static Connection con = null;
    static PreparedStatement PrepareStat = null;


    public static void addDataToDB(User user) {
        Statement stmt;
        try {
            con=conToDBcon();

            String insertQueryStatement = "INSERT  INTO  user  VALUES  (NULL,?,\" \",?,?)";
            PrepareStat = con.prepareStatement(insertQueryStatement);

//            PrepareStat.setInt(1, userid);
            PrepareStat.setString(1, user.getPassword());

            PrepareStat.setString(2, user.getUsername());
            PrepareStat.setString(3, user.getEmail());
            PrepareStat.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Statement conToDB() {
        Statement stmt = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema", "root", "root");
            //here sonoo is database name, root is username and password
            stmt = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return stmt;
    }
    public static Connection conToDBcon() {
        Statement stmt = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema", "root", "root");
            //here sonoo is database name, root is username and password
            stmt = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return con;
    }
    public static void getDataFromDB(){

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM user";
            PrepareStat = con.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

}
