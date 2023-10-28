package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Building {
    private Integer id;
    private Double square;
    private Integer floors_number;
    private String building_address;
    private Integer management_company_id;
}
