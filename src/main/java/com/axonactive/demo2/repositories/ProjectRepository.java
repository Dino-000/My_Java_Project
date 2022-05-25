package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
   List<Project> findByDepartmentId(Integer deptId);

    List<Project> findByArea(String area);
    List<Project> findByName (String name);
    List<Project> findByIdNotAndAreaNot (Integer id, String area);
    List<Project> findByAreaStartingWith ( String containWord);
    List<Project> findByNameEndingWithAndArea (String endingOfName, String area);
    List<Project> findByIdLessThan (Integer id);
    List<Project> findByIdGreaterThanAndAreaNot (Integer id, String area);
    List<Project> findByIdLessThanAndDepartmentIdNot(Integer idProject, Integer idDept);
    List<Project> findByNameAndArea ( String name, String area);
}
