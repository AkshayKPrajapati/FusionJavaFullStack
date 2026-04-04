package demo.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEmployee {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter the Employee ID you want to delete");
			int empId = scanner.nextInt();
			
			//load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection with database
			String url="jdbc:mysql://localhost:3306/016_25batch";//url https://www.nehapune.com/016_Batch
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected Successfully !!");
			
			//write a query
			String sql = "DELETE FROM employee WHERE id = ?";
			
			//Associate with Query
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//Execute Query
			preparedStatement.setInt(1, empId);
			int row = preparedStatement.executeUpdate();
			
			if(row>0) {
				System.out.println("Employee id "+ empId + "has been deleted ..");
			}
			
		} catch (ClassNotFoundException |SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//close the resource
			try {
				if(connection!=null) {
					connection.close();//close the connection
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
