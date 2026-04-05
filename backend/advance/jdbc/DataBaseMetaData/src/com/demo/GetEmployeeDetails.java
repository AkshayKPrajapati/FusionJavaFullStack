package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetEmployeeDetails {
	public static void main(String[] args) {
        Connection connection = null;
        try {
            // load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("loader ................");

            //Establish to the database
            String url = "jdbc:mysql://localhost:3306/016_25Batch";
            String username = "root";
            String password = "local";
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Database connection established ...");


            //Query
            String sql = "select * from employee";

            //Associate query with connection
            Statement statement = connection.createStatement();

            // Execute Query
            ResultSet resultSet = statement.executeQuery(sql);

            // iterate through the Result Set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println("id : " + id);
                String name = resultSet.getString("name");
                System.out.println("name : " + name);
                float salary = resultSet.getFloat("salary");
                System.out.println("salary : " + salary);
                System.out.println("-------------------------------");
            }
        } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
        }
        finally {
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
