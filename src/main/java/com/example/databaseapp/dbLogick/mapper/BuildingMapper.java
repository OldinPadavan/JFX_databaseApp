package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Building;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingMapper {
    public Building mapResultSet(ResultSet rs_set) throws SQLException {
        return Building.builder()
                .id(rs_set.getInt("id"))
                .building_address(rs_set.getString("building_address"))
                .square(rs_set.getDouble("square"))
                .floors_number(rs_set.getInt("floors_number"))
                .management_company_id(rs_set.getInt("management_company_id"))
                .build();
    }
}
