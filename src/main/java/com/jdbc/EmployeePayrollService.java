package com.jdbc;

import java.sql.*;
import java.time.LocalDate;

public class EmployeePayrollService {
    EmployeePayrollJdbc employeePayrollJdbc;

    public int getQuery(String query) throws SQLException {
        ResultSet queries = getQuerries(query);
        return printSet(queries);
    }

    public int updateData(String name, double value) throws SQLException {
        employeePayrollJdbc = EmployeePayrollJdbc.getInstance();
        Connection connection = employeePayrollJdbc.dbConnect();
        Statement statement = connection.createStatement();
        String query = String.format("UPDATE employee_payroll SET salary = %.2f WHERE name = '%s';", value, name);
        return statement.executeUpdate(query);
    }

    public int updatePreparedData(String name,double value) throws SQLException {
        employeePayrollJdbc = EmployeePayrollJdbc.getInstance();
        Connection connection = employeePayrollJdbc.dbConnect();
        String query = String.format("UPDATE employee_payroll SET Basic_pay = %.2f WHERE name = '%s';", value, name);
        PreparedStatement statement =  connection.prepareStatement(query);
        return statement.executeUpdate();
    }

    public ResultSet getQuerries(String query) throws SQLException {
        employeePayrollJdbc = EmployeePayrollJdbc.getInstance();
        Connection connection = employeePayrollJdbc.dbConnect();
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    public int printSet(ResultSet queries) throws SQLException {
        int i = 0;
        while (queries.next()) {
            i++;
            int id = queries.getInt("id");
            String name = queries.getString("name");
            double salary = queries.getDouble("salary");
            LocalDate start = queries.getDate("start").toLocalDate();
            System.out.println(id + " " + name + " " + salary + " " + start + " ");
        }
        return i;
    }
}

