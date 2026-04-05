package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertEmployeeDetailsPreparedStatement {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee ID");
        int id = sc.nextInt();

        System.out.println("Enter Employee Name");
        String name = sc.next();
        System.out.println("Enter Employee Salary");
        float salary = sc.nextFloat();

        Connection conn=null;
        // load Driver
        try {
            // load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            // 2. Data Base connection
            String url = "jdbc:mysql://localhost:3306/016_25Batch";
            String user = "root";
            String password = "local";
            conn = DriverManager.getConnection(url, user, password);

            //3. query
            String query = "insert into employee values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setFloat(3, salary);
            int rows= pstmt.executeUpdate();

            if(rows>0){
                System.out.println("{\n" +
                        "Message : "+"Employee Details Inserted Successfully\n" +
                        "Employee id : "+ id+"\n"+
                        "Employee Name : "+ name+"\n"+
                        "Employee Salary : "+ salary +"\n"+
                        "}");

            }
            else{
                System.out.println("Employee Details Not Inserted Successfully");
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
