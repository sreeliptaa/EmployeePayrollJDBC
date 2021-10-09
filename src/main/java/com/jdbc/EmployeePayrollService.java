package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EmployeePayrollService {
    EmployeePayrollJdbc employeePayrollJdbc;

    public int getQuery(String query) throws SQLException {
        ResultSet queries = getQuerries(query);
        return printSet(queries);
    }

    public int updateData(String name, double value) throws SQLException {
        employeePayrollJdbc = new EmployeePayrollJdbc();
        Connection connection = employeePayrollJdbc.dbConnect();
        Statement statement = connection.createStatement();
        String query = String.format("UPDATE employee_payroll SET salary = %.2f WHERE name = '%s';", value, name);
        return statement.executeUpdate(query);
    }

    public ResultSet getQuerries(String query) throws SQLException {
        employeePayrollJdbc = new EmployeePayrollJdbc();
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