package demo.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEmployee {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			//load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection with database
			String url="jdbc:mysql://localhost:3306/016_25batch";//url https://www.nehapune.com/016_Batch
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected Successfully !!");
			
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter the EMP ID : ");
			int empId = scanner.nextInt();
			
			//write a Query
			String sql = "select * from employee where id=?";
			
			//Associate with Query
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//Execute Query
			preparedStatement.setInt(1, empId);
			
			//Iterate the data
			  ResultSet resultSet = preparedStatement.executeQuery();
			  
			  if(resultSet.next()) {
				  String update = "update employee set salary=salary+100000 where id=?";
				  PreparedStatement ps = connection.prepareStatement(update);
				  ps.setInt(1, empId);
				  int resultValue = ps.executeUpdate();
				  
				  if(resultValue>0) {
					  System.out.println("updated ....");
				  }else {
					  System.out.println("does not updated...");
				  }
			  }else {
				  System.out.println("Empolyee ID "+empId+ "does not found");
			  }
		}catch(ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
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
