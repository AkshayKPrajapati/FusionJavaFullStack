package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter the Empolyee Details : ");
			System.out.println("Enter the Emp Id : ");
			int empId = scanner.nextInt();
			
			
			
			//load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection with database
			String url="jdbc:mysql://localhost:3306/016_25batch";//url https://www.nehapune.com/016_Batch
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected Successfully !!");
			
			String sql="{CALL getemployeeInfoById(?,?,?)}";
			
			CallableStatement callableStatement = connection.prepareCall(sql);
			
			callableStatement.setInt(1, empId);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.FLOAT);
			
			callableStatement.execute();
			
			System.out.println("Employee Id "+empId);
			System.out.println("Name "+callableStatement.getString(2));
			System.out.println("salary: "+callableStatement.getFloat(3));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null) {
					connection.close();//close the resource 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
