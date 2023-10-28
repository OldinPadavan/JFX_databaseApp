package com.example.databaseapp.dbLogick.mapper;

import com.example.databaseapp.dbLogick.model.Bills;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;

public class BillsMapper {
    public Bills mapResultSet(ResultSet rs_set) throws SQLException {
        return Bills.builder()
                .id(rs_set.getInt("id"))
                .date(rs_set.getDate("date").toInstant().atOffset(ZoneOffset.UTC))
                .amount(rs_set.getDouble("amount"))
                .link(rs_set.getString("link"))
                .flat_id(rs_set.getInt("flat_id"))
                .build();
    }
}
