package com.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;


public class EmployeePayrollJdbc {
    private static EmployeePayrollJdbc employeePayrollJdbc;

    private EmployeePayrollJdbc() {
    }

    public static EmployeePayrollJdbc getInstance() {
        if (employeePayrollJdbc == null)
            employeePayrollJdbc = new EmployeePayrollJdbc();
        return employeePayrollJdbc;
    }

    Connection connection;

    public Connection dbConnect() {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_services";
        String userName = "root";
        String password = "8093751498";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find driver in classpath", e);
        }

        try {
            System.out.println("Connecting to database:" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful" + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}