package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Owner {
    private Integer id;
    private String last_name;
    private String first_name;
    private String middle_name;
}
