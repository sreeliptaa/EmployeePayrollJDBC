package com.jdbc;

import java.sql.*;
import java.time.LocalDate;

public class EmployeePayrollService {
    EmployeePayrollJdbc employeePayrollJdbc;

    public int getQuery(String query) throws SQLException {
        ResultSet queries = getQuerries(query);
        return printSet(queries);
    }

    public int updateData(String name,double value) throws SQLException {
        employeePayrollJdbc = EmployeePayrollJdbc.getInstance();
        Connection connection = employeePayrollJdbc.dbConnect();
        Statement statement = connection.createStatement();
        String query = String.format("update payroll set basic_pay = '%.2f' where emp_id IN (select emp_id from employee where name = '%s');",value,name);
        return statement.executeUpdate(query);
    }

    public int updatePreparedData(String name,double value) throws SQLException {
        employeePayrollJdbc = EmployeePayrollJdbc.getInstance();
        Connection connection = employeePayrollJdbc.dbConnect();
        String query = String.format("update payroll set basic_pay = '%.2f' where emp_id IN (select emp_id from employee where name = '%s');",value,name);
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

    public int retrieveDate() throws SQLException {
        String query = String.format("Select * from employee_payroll where start between '%s' and '%s');", Date.parse("2018-01-01"),Date.parse("2021-12-30"));
        ResultSet queries = getQuerries(query);
        return printSet(queries);
    }
}

