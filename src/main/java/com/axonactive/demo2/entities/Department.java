package com.axonactive.demo2.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "departmentid")
    private Integer id;

    @Size(max=200)
    @Column(length = 200)
    @NotNull
    private String name;

    private LocalDate startDate;

    public Department(String department_name, LocalDate start_date) {
        this.name = department_name;
        this.startDate = start_date;
    }
}
