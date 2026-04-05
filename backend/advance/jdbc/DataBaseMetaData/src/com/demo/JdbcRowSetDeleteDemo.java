package com.demo;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetDeleteDemo {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Employee ID which row you want to delete: ");
        int id = sc.nextInt();

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

            System.out.println("Do you want to delete this employee (y/n)? ");
            String delete = sc.next();
            boolean deleted = false;
            if(delete.equalsIgnoreCase("y"))
            {

                while (jrs.next()) {
                    jrs.deleteRow();
                    deleted = true;
                }
            }


            if (deleted) {
                System.out.println("Row Deleted Successfully");
            } else {
                System.out.println("Employee ID NOT Found.");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
