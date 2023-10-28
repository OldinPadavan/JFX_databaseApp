package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Employees {
    private Integer id;
    private String password;
    private String last_name;
    private String first_name;
    private String middle_name;
    private Integer company_id;
    private String worker_role;
}
