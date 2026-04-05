package com.demo;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetUpdateDemo {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID which you want to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Employee Salary: ");
        float salary = sc.nextFloat();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            RowSetFactory rsf = RowSetProvider.newFactory();
            JdbcRowSet jrs = rsf.createJdbcRowSet();


            jrs.setUrl("jdbc:mysql://localhost:3306/016_25Batch");
            jrs.setUsername("root");
            jrs.setPassword("local");


            jrs.setCommand("SELECT * FROM employee WHERE id = ?");
            jrs.setInt(1, id);
            jrs.execute();

            boolean updateStatus = false;

            if (jrs.next()) {
                System.out.print("Do you want to update this employee (yes/no)? ");
                String input = sc.next();

                if (input.equalsIgnoreCase("yes")) {
                    jrs.updateString("name", name);
                    jrs.updateFloat("salary", salary);
                    jrs.updateRow(); // apply update
                    updateStatus = true;
                }
            }

            if (updateStatus) {
                System.out.println("Row Updated Successfully.");
            } else {
                System.out.println("Employee ID not found or update cancelled.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
