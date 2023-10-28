package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Flat {
    private Integer id;
    private String flat_address;
    private Integer flat_owner_id;
    private Double flat_square;
    private Integer building_id;
    private Integer flat_number;
}
