package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEmpolyee {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        System.out.println("enter the id  of the employee you want to delete");
        int id = scanner.nextInt();


        try {
            String url = "jdbc:mysql://localhost:3306/016_25Batch";
            String user = "root";
            String password = "local";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully");

            String sql = "Delete from employee  WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            System.out.println("Are you sure you want to delete employee details? (yes/no)");

            if (scanner.next().equalsIgnoreCase("yes")) {
                // correct parameter indexes

                pstmt.setInt(1, id);

                int rows = pstmt.executeUpdate();


                if (rows > 0) {
                    System.out.println("status :"+ "OK");
                    System.out.println("message : "+ "Employee details deleted successfully");
                    System.out.println("employeeId : "+ id);
                } else {
                    System.out.println("status"+ "FAILED");
                    System.out.println("message " + "Employee not found or delete failed");
                    System.out.println("employeeId "+ id);
                }



            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (scanner != null) scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
