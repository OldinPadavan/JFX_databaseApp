package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Employees;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesMapper {
    public Employees mapResultSet(ResultSet rs_set) throws SQLException {
        return Employees.builder()
                .id(rs_set.getInt("id"))
                .password(rs_set.getString("password"))
                .last_name(rs_set.getString("last_name"))
                .first_name(rs_set.getString("first_name"))
                .middle_name(rs_set.getString("middle_name"))
                .company_id(rs_set.getInt("company_id"))
                .worker_role(rs_set.getString("worker_role"))
                .build();
    }
}
