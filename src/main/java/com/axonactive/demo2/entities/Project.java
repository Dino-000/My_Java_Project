package com.axonactive.demo2.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String area;

    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "managed_department")
    private Department department;

    public Project(String area, String project_name, Department department) {
        this.area = area;
        this.name = project_name;
        this.department = department;
    }

}
