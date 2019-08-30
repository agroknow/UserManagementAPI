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
    static Scanner keyboard = new Scanner(System.in);



    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema", "root", "root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
//            addDataToDB(3, "pass", "pass", "user", "email@emai.com");
            System.out.println("Login(1) or register(2) enter the number");
            int menuchoice = keyboard.nextInt();
            String c = keyboard.nextLine();

            if (menuchoice==1) {
                //login part
                System.out.println("LOGIN");


                System.out.println("enter your name");
                String nameFromUser = keyboard.nextLine();
                System.out.println(nameFromUser);
                System.out.println("enter password");
                String passFromUser = keyboard.nextLine();
                System.out.println(passFromUser);

                getDataFromDB();
                //elegxos an yparxei sth bash
                ResultSet rs = stmt.executeQuery("select * from user");
                while (rs.next()) {
                    int id = rs.getInt("userid");
                    String password = rs.getString("password");
                    String passwordconfirm = rs.getString("passwordconfirm");
                    String username = rs.getString("username");
                    String email = rs.getString("email");

                    Boolean nameIsTrue = username.equals(nameFromUser);
                    Boolean passIsTrue = password.equals(passFromUser);
                    if ((nameIsTrue) && (passIsTrue)) {
                        System.out.println("youAreIn");
                    } else {
                        System.out.println("youAreOut");
                    }
                    System.out.println(nameIsTrue);
                    // print the results
                    System.out.format("%s, %s, %s, %s, %s\n", id, password, passwordconfirm, username, email);
                }
            }
            else
            {
                int flag=0;
                System.out.println("REGISTER");

                System.out.println("enter your name");
                String nameFromUser = keyboard.nextLine();
                System.out.println(nameFromUser);
                System.out.println("enter password");
                String passFromUser = keyboard.nextLine();
                System.out.println(passFromUser);
                String conPassFromUser;
                do {

                    System.out.println("confirm your password");
                     conPassFromUser = keyboard.nextLine();
                    System.out.println(conPassFromUser );
                    Boolean conpassIsTrue = conPassFromUser.equals(passFromUser);
                    if (conpassIsTrue){
                        flag=0;
                    }
                    else
                    {
                        flag=1;
                    }
                }while(flag==1);
                System.out.println("enter email");
                String emailFromUser = keyboard.nextLine();
                System.out.println(emailFromUser);

                addDataToDB(passFromUser, conPassFromUser, nameFromUser, emailFromUser);
            }
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            PrepareStat.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addDataToDB( String password, String passwordconfirm, String username, String email) {

        try {


            String insertQueryStatement = "INSERT  INTO  user  VALUES  (NULL,?,?,?,?)";
            PrepareStat = con.prepareStatement(insertQueryStatement);

//            PrepareStat.setInt(1, userid);
            PrepareStat.setString(1, password);
            PrepareStat.setString(2, passwordconfirm);
            PrepareStat.setString(3, username);
            PrepareStat.setString(4, email);
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
