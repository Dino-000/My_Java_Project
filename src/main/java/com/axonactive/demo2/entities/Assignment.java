package com.axonactive.demo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numberOfHour;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private  Project project;

    public Assignment(Integer number_of_hour, Employee employee, Project project) {
        this.numberOfHour = number_of_hour;
        this.employee = employee;
        this.project = project;
    }
}
