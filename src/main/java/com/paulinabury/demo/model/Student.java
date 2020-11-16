package com.paulinabury.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private Long id;
    private String name;
    private String lastName;
    private String indexNumber;
    private String field;
    private LocalDate startDate;
    private LocalDate graduationDate;

}
