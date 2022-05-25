package com.axonactive.demo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String location;

    @ManyToOne
    @JoinColumn(name="deptid")
    private Department department;

    public DepartmentLocation(String location, Department department) {
        this.location = location;
        this.department = department;
    }
}
