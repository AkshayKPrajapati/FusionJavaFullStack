package com.demo;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetInsert {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create RowSet
            RowSetFactory factory = RowSetProvider.newFactory();
            JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();

            // Set DB Connection
            jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/016_25Batch");
            jdbcRowSet.setUsername("root");
            jdbcRowSet.setPassword("local");

            // IMPORTANT: Execute query first
            jdbcRowSet.setCommand("SELECT * FROM employee"); // your table name
            jdbcRowSet.execute();

            // Take input
            System.out.print("Enter Employee ID : ");
            int id = sc.nextInt();

            System.out.print("Enter Employee Name : ");
            String name = sc.next();

            System.out.print("Enter Employee Salary : ");
            double salary = sc.nextDouble();

            // Move to insert row
            jdbcRowSet.moveToInsertRow();

            jdbcRowSet.updateInt(1, id);
            jdbcRowSet.updateString(2, name);
            jdbcRowSet.updateDouble(3, salary);

            // Insert row
            jdbcRowSet.insertRow();

            // Move back to current row
            jdbcRowSet.moveToCurrentRow();

            System.out.println("✅ Record inserted successfully!");

            // Close resources
            jdbcRowSet.close();
            sc.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database error!");
            e.printStackTrace();
        }
    }
}