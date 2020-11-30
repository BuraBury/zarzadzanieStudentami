package com.paulinabury.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity (name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
//    @Length(min=2, max=25)
    private String name;
//    @Length(min=2, max=25)
    private String surname;
    private String indexNumber;
    private String field;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
