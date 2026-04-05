package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionManagementDemo {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter Employee Details");
			System.out.println("Enter ID : ");
			int empId = scanner.nextInt();
			
			scanner.nextLine();
			System.out.println("Enter Employee Name :");
			String empName=scanner.nextLine();
			
			System.out.println("Enter Salary : ");
			float empSalary=scanner.nextFloat();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/016_25Batch";
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			//set Auto Commit false
			connection.setAutoCommit(false);
			
			String sql="insert into employee(id, name, salary) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, empName);
			preparedStatement.setFloat(3, empSalary);
			
			preparedStatement.executeUpdate();
			
			System.out.println("Do you really want to insert a record (yes/no) ?");
			String input=scanner.next();
			
			if(input.equalsIgnoreCase("yes")) {
				connection.commit();
				System.out.println("Employee Record Inserted Successfully");
			} else {
				connection.rollback();
				System.out.println("failed");
			}
			
		} catch (ClassNotFoundException  | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
