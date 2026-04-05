package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEmployeeDetails {
	public static void main(String[] args) {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Employee ID : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter the Employee Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter the Employee Salary : ");
        float salary = scanner.nextFloat();

        try {
            String url = "jdbc:mysql://localhost:3306/016_25Batch";
            String user = "root";
            String password = "local";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully");

            String sql = "UPDATE employee SET salary = ?, name = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            System.out.println("Are you sure you want to update employee details? (yes/no)");

            if (scanner.next().equalsIgnoreCase("yes")) {
                // correct parameter indexes
                pstmt.setFloat(1, salary);
                pstmt.setString(2, name);
                pstmt.setInt(3, id);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("status :"+ "OK");
                    System.out.println("message : "+ "Employee details updated successfully");
                    System.out.println("employeeId : "+ id);
                } else {
                    System.out.println("status"+ "FAILED");
                    System.out.println("message " + "Employee not found or update failed");
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
