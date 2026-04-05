package com.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaDataDemo {
	public static void main(String[] args) {
		Connection connection =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/016_25batch";
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected");
			
			DatabaseMetaData data=connection.getMetaData();
			String name = data.getDatabaseProductName();
			System.out.println("Database Product Name : "+name);
			
			System.out.println("Database Product Version : "+data.getDatabaseProductVersion());
			System.out.println("Database Driver Name : "+data.getDriverName());
			System.out.println("Databse Driver Version : "+data.getDriverVersion());
			System.out.println("URL : "+data.getURL());
			System.out.println("Username : "+data.getUserName());
			System.out.println("KeyWord: "+ data.getSQLKeywords());
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
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
