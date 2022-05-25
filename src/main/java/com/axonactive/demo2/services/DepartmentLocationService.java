package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.DepartmentLocation;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentLocationService {
    List<DepartmentLocation> getAll();

    void addDepartmentLocation(DepartmentLocation departmentLocation);

    void deleteDepartmentLocation(Integer id);

    Optional<DepartmentLocation> findDepartmentLocationById(Integer id);

    void updateDepartmentLocation( Integer id, DepartmentLocation departmentLocation) throws ResourceNotFoundException;

    List<DepartmentLocation> findByLocation(String location);

    List<DepartmentLocation> findByLocationNotLike(String location);

    List<DepartmentLocation> findByLocationStartingWith(String startingWord);

    List<DepartmentLocation> findByLocationEndingWith(String endingWord);

    List<DepartmentLocation> findByLocationContaining(String containingWord);

    List<DepartmentLocation> findByLocationIgnoreCase(String location);


    List<DepartmentLocation> findByDepartmentIdIsNull();


    List<DepartmentLocation> findByDepartmentIdIsNotNull();

    List<DepartmentLocation> findByLocationContainingAndDepartmentIdGreaterThan(String containingWord, Integer idGreaterThan);

    List<DepartmentLocation> findByLocationEndingWithAndDepartmentIdLessThan(String containingWord, Integer idLessThan);
}
