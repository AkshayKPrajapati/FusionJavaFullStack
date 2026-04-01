package demo.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertEmployeeDetailsPreparedStatement {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter the Empolyee Details : ");
			System.out.println("Enter the Emp Id : ");
			int empId = scanner.nextInt();
			
			System.out.println("Enter the Name: ");
			scanner.nextLine();
			String name = scanner.nextLine();
			
			System.out.println("Enter the salary");
			float salary = scanner.nextFloat();
			
			//load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection with database
			String url="jdbc:mysql://localhost:3306/016_25batch";//url https://www.nehapune.com/016_Batch
			String username="root";
			String password="local";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("db connected Successfully !!");
			
			//write SQL Query
			String sql = "insert into employee(id, name, salary) values(?,?,?)";
			
			//Associate Query with connection
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//Execute Query
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, name);
			preparedStatement.setFloat(3, salary);
			int row = preparedStatement.executeUpdate();
			if(row>0) {
				System.out.println("Employee Details inserted Successfully  !!");
			}
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
