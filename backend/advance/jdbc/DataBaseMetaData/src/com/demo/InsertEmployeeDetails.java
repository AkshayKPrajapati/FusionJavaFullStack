package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertEmployeeDetails {
	public static void main(String[] args) {
        Connection conn=null;
        try {
            // 1. load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            //2. Data Base Connection
            String url = "jdbc:mysql://localhost:3306/016_25Batch";
            String user = "root";
            String password = "local";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully");

            // scanner

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Employee ID : ");
            int id = sc.nextInt();
            System.out.print("Enter Employee Name : ");
            String  name = sc.next();
            System.out.print("Enter Employee Salary : ");
            double salary = sc.nextDouble();


            // write a query
            String sql = "insert into employee values (" + id + ", '" + name + "', " + salary + ")";
            //4. Associate with query connection
            Statement stmt = conn.createStatement();
            //5. execute Query
            int rows= stmt.executeUpdate(sql);

            if (rows > 0) {
                String status = "OK";
                String message = "Employee ID inserted successfully";
                int employeeId = id;

                System.out.println("{");
                System.out.println("  \"status\": \"" + status + "\",");
                System.out.println("  \"message\": \"" + message + "\",");
                System.out.println("  \"employeeId\": " + employeeId);
                System.out.println("}");
            }
            else{
                System.out.println("status : failed ");
                System.out.println("message : employee ID inserted failed");
            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn!=null){
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
