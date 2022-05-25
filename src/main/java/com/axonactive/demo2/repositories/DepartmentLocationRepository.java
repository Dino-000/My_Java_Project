package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.DepartmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation,Integer> {
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
