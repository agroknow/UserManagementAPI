package com.agroknow.umapi.controller;

import com.agroknow.umapi.model.TalkToDB;
import com.agroknow.umapi.view.User;

import java.sql.*;
import java.util.Scanner;

public class Controller {
    static Connection con = null;
    static PreparedStatement PrepareStat = null;
    static String name = "";
    static String pass = "";
    static Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args) {
        try {

//            addDataToDB(3, "pass", "pass", "user", "email@emai.com");
            System.out.println("Login(1) or register(2) enter the number");
            int menuchoice = keyboard.nextInt();
            String c = keyboard.nextLine();

            if (menuchoice == 1) {
                int check = 0;
                //login part
//                System.out.println("LOGIN");
//                System.out.println("enter your name");
//                String nameFromUser = keyboard.nextLine();
//                System.out.println(nameFromUser);
//                System.out.println("enter password");
//                String passFromUser = keyboard.nextLine();
//                System.out.println(passFromUser);


                check = login("kaiti", "kaitlli");
//                check = login(passFromUser, nameFromUser);


                if (check == 1) {
                    System.out.println("youAreIn");
                } else {
                    System.out.println("youAreOut");
                }


            } else {
                int flag = 0;
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
                    System.out.println(conPassFromUser);
                    Boolean conpassIsTrue = conPassFromUser.equals(passFromUser);
                    if (conpassIsTrue) {
                        flag = 0;
                    } else {
                        flag = 1;
                    }
                } while (flag == 1);
                System.out.println("enter email");
                String emailFromUser = keyboard.nextLine();
                System.out.println(emailFromUser);
                User user = new User(nameFromUser, passFromUser, emailFromUser);
                TalkToDB.addDataToDB(user);
            }
            Statement stmt;
            stmt = TalkToDB.conToDB();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
//            PrepareStat.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int login(String passFromUser, String nameFromUser) {

        int check = 0;
        String sql = "select* from user where username=\"" + nameFromUser + "\" and password=\"" + passFromUser + "\"";
        try {
            Statement stmt;
            stmt = TalkToDB.conToDB();
            check = 0;

            //elegxos an yparxei sth bash
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                check = 1;
            }

//            while (rs.next()) {
//
//                String password = rs.getString("password");
//                String username = rs.getString("username");
//
//                Boolean nameIsTrue = username.equals(nameFromUser);
//                Boolean passIsTrue = password.equals(passFromUser);
//                if ((nameIsTrue) && (passIsTrue)) {
//                    check = 1;

            else {
                check = 0;
            }

//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

}
