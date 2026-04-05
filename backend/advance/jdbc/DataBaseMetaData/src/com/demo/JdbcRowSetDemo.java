package com.demo;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            JdbcRowSet jdbcRowSet = rowSetFactory.createJdbcRowSet();

            jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/016_25Batch");
            jdbcRowSet.setUsername("root");
            jdbcRowSet.setPassword("local");

            jdbcRowSet.setCommand("SELECT * FROM employee");
            jdbcRowSet.execute();
            
           
            System.out.println("Employee Details");//0-1
            while (jdbcRowSet.next()) {//forward direction
            	System.out.println("Id : "+jdbcRowSet.getInt(1));
            	System.out.println("Name : "+jdbcRowSet.getString(2) );
            	System.out.println("salary : "+jdbcRowSet.getFloat(3));
            	System.out.println("------------------------");
               
            }
            System.out.println("Employee Details from the last (9-1)");
            jdbcRowSet.afterLast();//TO move remove pointer the last records
            while (jdbcRowSet.previous()) {//Backward direction
            	System.out.println("Id : "+jdbcRowSet.getInt(1));
            	System.out.println("Name : "+jdbcRowSet.getString(2) );
            	System.out.println("salary : "+jdbcRowSet.getFloat(3));
            	System.out.println("------------------------");
               
            }
            
            jdbcRowSet.absolute(5);//To move rowset pointer to the 5th record.
            System.out.println("5th Record Details");
            System.out.println("Id : "+jdbcRowSet.getInt(1));
        	System.out.println("Name : "+jdbcRowSet.getString(2) );
        	System.out.println("salary : "+jdbcRowSet.getFloat(3));
            System.out.println("Application End ..");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}