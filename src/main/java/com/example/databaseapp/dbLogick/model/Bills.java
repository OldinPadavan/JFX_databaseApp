package com.example.databaseapp.dbLogick.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Setter
@Getter
@Builder
@ToString
public class Bills {
    private Integer id;
    private OffsetDateTime date;
    private Double amount;
    private String link;
    private Integer flat_id;
}
