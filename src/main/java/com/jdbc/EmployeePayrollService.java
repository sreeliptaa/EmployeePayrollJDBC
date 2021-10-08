package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EmployeePayrollService {
    EmployeePayrollJdbc employeePayrollJdbc;

    public int getQuery(String query) throws SQLException {
        ResultSet querries = getQuerries(query);
        return printSet(querries);
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
