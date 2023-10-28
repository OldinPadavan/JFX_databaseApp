package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Management_company;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Management_companyMapper {

    public Management_company mapResultSet(ResultSet rs_set) throws SQLException {
        return Management_company.builder()
                .id(rs_set.getInt("id"))
                .name(rs_set.getString("name"))
                .inn(rs_set.getInt("inn"))
                .address(rs_set.getString("address"))
                .phone(rs_set.getString("phone"))
                .build();
    }
}
