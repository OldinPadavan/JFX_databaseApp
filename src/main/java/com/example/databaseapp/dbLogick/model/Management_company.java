package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Management_company {
    private Integer id;
    private String name;
    private Integer inn;
    private String address;
    private String phone;
}
