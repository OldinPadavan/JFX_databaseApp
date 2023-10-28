package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Flat;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlatMapper {
    public Flat mapResultSet(ResultSet rs_set) throws SQLException {
        return Flat.builder()
                .id(rs_set.getInt("id"))
                .flat_address(rs_set.getString("flat_address"))
                .flat_owner_id(rs_set.getInt("flat_owner_id"))
                .flat_square(rs_set.getDouble("flat_square"))
                .building_id(rs_set.getInt("building_id"))
                .flat_number(rs_set.getInt("flat_number"))
                .build();
    }
}
