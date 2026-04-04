package demo.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetEmpolyeeRecoards {
	public static void main(String [] args) {
		Connection connection=null;
		try {
			//step 1.load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class loaded!!");
			
			//step 2.Connection Establish to the database
			String url="jdbc:mysql://localhost:3306/016_25batch";
			String username="root";
			String password="local";
			
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection establish successfully");
			
			//step 3. write a SQL Query
			String sql = "select * from employee";
			
			//step 4.Associate Query with connection
			Statement statement = connection.createStatement();
			
			//step 5.Execute Query
			ResultSet resultSet = statement.executeQuery(sql);
			
			//step 6. Iteration the data
			while(resultSet.next()) {
				System.out.println("Id: "+resultSet.getInt(1));//column Index
				System.out.println("Name : "+resultSet.getString(2));//column Index
				System.out.println("Salary : "+resultSet.getFloat("salary"));//Column Name
				System.out.println("--------------");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//step 7 .close the connection
		finally{
			try{
				if(connection!=null){
					connection.close();
				}
			}
			catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
