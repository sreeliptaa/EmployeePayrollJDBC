package com.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class EmployeePayrollTest {
    EmployeePayrollService employeePayrollService;

    @Test
    public void ifData_FromDataBase_ShouldReturnSize() throws SQLException {
        employeePayrollService = new EmployeePayrollService();
        String query = "select * from employee_payroll;";
        Integer result = employeePayrollService.getQuery(query);
        Assertions.assertEquals((Integer) 4, result);
    }
}
