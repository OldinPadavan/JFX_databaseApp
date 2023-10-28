package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Owner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper {
    public Owner mapResultSet(ResultSet rs_set) throws SQLException {
        return Owner.builder()
                .id(rs_set.getInt("id"))
                .last_name(rs_set.getString("last_name"))
                .first_name(rs_set.getString("first_name"))
                .middle_name(rs_set.getString("middle_name"))
                .build();
    }
}
