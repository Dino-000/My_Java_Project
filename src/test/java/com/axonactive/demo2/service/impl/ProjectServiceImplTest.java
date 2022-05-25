package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Project;
import com.axonactive.demo2.services.DepartmentService;
import com.axonactive.demo2.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    ProjectService projectService;

    Department deptDemo1= new Department("Marketing", LocalDate.parse("2011-02-10"));
    Department deptDemo2= new Department("Marketing",LocalDate.parse("2011-02-10"));

    Project projDemo1 = new Project("HongKong","Leap Expert",deptDemo1);
    Project projDemo2= new Project("Japan","Tiny Pulse",deptDemo2);


    ProjectServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllProject_shouldReturnNoData_whenJustCreateTable(){
        List<Project> projects = projectService.getAll();
        assertEquals(0,projects.size());
    }


    @Test
    void testAddProject_shouldAddNewProject_whenInputNewProject()  {
        departmentService.addDepartment(deptDemo1);
        projectService.addProject(projDemo1);
        assertEquals(1,projectService.getAll().size());
    }

    @Test
    void testDeleteRelatives_shouldDeleteRelatives_whenInputARelativesId()  {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(2,projectService.getAll().size());
        projectService.deleteProject(1);
        assertEquals(1,projectService.getAll().size());
        assertEquals(Optional.empty(),projectService.findProjectById(1));

    }

    @Test
    void testFindProjectById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo2,projectService.findProjectById(2).get());
    }

    @Test
    void testFindByDepartmentId_shouldReturnAProject_whenInputAnId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo2,projectService.findByDepartmentId(2).get(0));
    }

    @Test
    void testFindByArea_shouldReturnAProject_whenInputAnArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByArea("HongKong").get(0));
        assertEquals(0,projectService.findByArea("HN").size());
    }

    @Test
    void testFindByName_shouldReturnAProject_whenInputAnName()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByName("Leap Expert").get(0));
        assertEquals(0,projectService.findByName("HN").size());
    }

    @Test
    void testFindByIdNotAndAreaNot_shouldReturnAProject_whenInputAnIdAndArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByIdNotAndAreaNot(2,"HCM").get(0));
        assertEquals(1,projectService.findByIdNotAndAreaNot(1,"HongKong").size());
    }

    @Test
    void testFindByAreaStartingWith_shouldReturnAProject_whenInputAnArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByAreaStartingWith("Ho").get(0));
//        assertEquals(0,projectService.findByAreaStartingWith("Hog").size());
    }

    @Test
    void testFindByNameEndingWithAndArea_shouldReturnAProject_whenInputAnArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
//        assertEquals(projDemo1,projectService.findByNameEndingWithAndArea("pert","HongKong").get(0));
        assertEquals(0,projectService.findByNameEndingWithAndArea("Hog","ok").size());
    }

    @Test
    void testFindByIdLessThan_shouldReturnAProject_whenInputAnId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByIdLessThan(2).get(0));
        assertEquals(0,projectService.findByIdLessThan(0).size());
    }

    @Test
    void testFindByIdGreaterThanAndAreaNot_shouldReturnAProject_whenInputAnIdAndArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByIdGreaterThanAndAreaNot(0,"Hcm").get(0));
        assertEquals(1,projectService.findByIdGreaterThanAndAreaNot(0,"HongKong").size());
    }

    @Test
    void testFindByIdLessThanAndDepartmentIdNot_shouldReturnAProject_whenInputAnIdAndDepartmentId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByIdLessThanAndDepartmentIdNot(2,2).get(0));
        assertEquals(2,projectService.findByIdLessThanAndDepartmentIdNot(3,0).size());
    }

    @Test
    void testFindByNameAndArea_shouldReturnAProject_whenInputAnIdAndDepartmentId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByIdLessThanAndDepartmentIdNot(2,2).get(0));
        assertEquals(2,projectService.findByIdLessThanAndDepartmentIdNot(3,0).size());
    }

    @Test
    void testFindByNameAndArea_shouldReturnAProject_whenInputANameAndArea()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assertEquals(projDemo1,projectService.findByNameAndArea("Leap Expert","HongKong").get(0));
        assertEquals(0,projectService.findByNameAndArea("HongKong"," Expert").size());
    }



}
