package com.agroknow.umapi;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;
import static java.rmi.server.LogStream.log;

public class UMAPI {
    static Connection con = null;
    static PreparedStatement PrepareStat = null;
    static String name="";
    static String pass="";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema", "root", "root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            addDataToDB(3, "pass", "pass", "user", "email@emai.com");
            //login part
            System.out.println("LOGIN");
            Scanner keyboard = new Scanner(System.in);

            System.out.println("enter your name");
            String nameFromUser = keyboard.nextLine();
            System.out.println(nameFromUser);
            System.out.println("enter password");
            String passFromUser = keyboard.nextLine();
            System.out.println(passFromUser);

            getDataFromDB();
            //elegxos an yparxei sth bash
            ResultSet rs = stmt.executeQuery("select * from user");

            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            PrepareStat.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addDataToDB(int userid, String password, String passwordconfirm, String username, String email) {

        try {


            String insertQueryStatement = "INSERT  INTO  user  VALUES  (?,?,?,?,?)";
            PrepareStat = con.prepareStatement(insertQueryStatement);

            PrepareStat.setInt(1, userid);
            PrepareStat.setString(2, password);
            PrepareStat.setString(3, passwordconfirm);
            PrepareStat.setString(4, username);
            PrepareStat.setString(5, email);
            PrepareStat.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM user";
            PrepareStat = con.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                name = rs.getString("username");
                pass = rs.getString("password");


                // Simply Print the results
                System.out.format("%s, %s\n", name, pass);
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

}
