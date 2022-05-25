package com.axonactive.demo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private Integer gender;
    private String phoneNumber;
    private String relationship;

    @ManyToOne
    private  Employee employee;

    public Relatives(String fullName,String phoneNumber ,Integer gender,String relationship, Employee employee) {
        this.fullName = fullName;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.relationship = relationship;
        this.employee = employee;
    }
}
