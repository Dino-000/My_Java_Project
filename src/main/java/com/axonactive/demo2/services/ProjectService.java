package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Project;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAll();

    Project addProject(Project project);

    void deleteProject(Integer id);

    Optional<Project> findProjectById(Integer id);
    Project updateProject( Integer id, Project project) throws ResourceNotFoundException;

    List<Project> findByDepartmentId(Integer deptId);
    List<Project> findByArea(String area);
    List<Project> findByName (String name);
    List<Project> findByIdNotAndAreaNot (Integer id, String area);
    List<Project> findByAreaStartingWith ( String containWord);
    List<Project> findByNameEndingWithAndArea (String endingOfName, String area);
    List<Project> findByIdLessThan (Integer id);
    List<Project> findByIdGreaterThanAndAreaNot (Integer id, String area);
    List<Project> findByIdLessThanAndDepartmentIdNot(Integer idProject, Integer idDept);
    List<Project> findByNameAndArea ( String name, String area);}
