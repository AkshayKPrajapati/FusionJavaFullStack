package com.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataDemo {
	public static void main(String[] args) {
		Connection connection =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/016_25batch";
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected");
			
			String sql="select * from employee";
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			System.out.println(("Column Count : "+resultSetMetaData.getColumnCount()));
			for(int i=1;i<resultSetMetaData.getColumnCount();i++) {
				System.out.println(resultSetMetaData.getColumnName(i)+" : "+resultSetMetaData.getColumnTypeName(i));
			}
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
