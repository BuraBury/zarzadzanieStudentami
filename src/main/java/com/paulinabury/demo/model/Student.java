package com.paulinabury.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Length(min=2, max=25)
    private String name;
    @NotNull
    @Length(min=2, max=25)
    private String surname;
    private String indexNumber;
    private String field;
    private LocalDate startDate;
}
