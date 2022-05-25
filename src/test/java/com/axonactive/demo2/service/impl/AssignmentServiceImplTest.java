package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.*;
import com.axonactive.demo2.services.AssignmentService;
import com.axonactive.demo2.services.DepartmentService;
import com.axonactive.demo2.services.EmployeeService;
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
class AssignmentServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AssignmentService assignmentService;

    Department deptDemo1= new Department("Marketing", LocalDate.parse("2011-02-10"));
    Department deptDemo2= new Department("Marketing",LocalDate.parse("2011-02-10"));
    Project projDemo1 = new Project("HongKong","Leap Expert",deptDemo1);
    Project projDemo2= new Project("Japan","Tiny Pulse",deptDemo2);
    Employee empDemo1= new Employee("A001","Dino","male","Lee","De",2000,deptDemo1);
    Employee empDemo2= new Employee("A002","Ginkel","male","Van","De",4000,deptDemo2);
    Assignment assDemo1 = new Assignment(100,empDemo1,projDemo1);
    Assignment assDemo2 = new Assignment(200,empDemo2,projDemo2);



    AssignmentServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllAssignment_shouldReturnNoData_whenJustCreateTable(){
        List<Assignment> assignments = assignmentService.getAll();
        assertEquals(0,assignments.size());
    }


    @Test
    void testAddAssignment_shouldAddNewAssignment_whenInputNewAssignment()  {
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        projectService.addProject(projDemo1);
        assignmentService.addAssignment(assDemo1);
        assertEquals(1, assignmentService.getAll().size());
    }

    @Test
    void testDeleteAssignment_shouldDeleteAssignment_whenInputAAssignmentId()  {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(2, assignmentService.getAll().size());
        assignmentService.deleteAssignment(1);
        assertEquals(1, assignmentService.getAll().size());
        assertEquals(Optional.empty(), assignmentService.findAssignmentById(1));

    }

    @Test
    void testFindAssignmentById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId()  {
        assertEquals(Optional.empty(), assignmentService.findAssignmentById(1));
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo2, assignmentService.findAssignmentById(2).get());
    }

    @Test
    void testFindByNumberOfHour_shouldReturnObjectMatchWithGivenId_whenInputNumberOfHour()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo2, assignmentService.findByNumberOfHour(200).get(0));
        assertEquals(1, assignmentService.findByNumberOfHour(100).size());

    }

    @Test
    void testFindByEmployeeId_shouldReturnObjectMatchWithGivenId_whenInputNumberOfHour()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByEmployeeId(1).get(0));
        assertEquals(1, assignmentService.findByEmployeeId(2).size());

    }

    @Test
    void testFindByProjectId_shouldReturnAssignment_whenInputProjectId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByProjectId(1).get(0));
        assertEquals(1, assignmentService.findByProjectId(2).size());

    }

    @Test
    void testFindByNumberOfHourLessThan_shouldReturnAssignment_whenInputProjectId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByNumberOfHourLessThan(150).get(0));
        assertEquals(2, assignmentService.findByNumberOfHourLessThan(300).size());
        assertEquals(0, assignmentService.findByNumberOfHourLessThan(50).size());

    }

    @Test
    void testFindByNumberOfHourGreaterThanAndEmployeeIdNot_shouldReturnAssignment_whenInputEmployeeIdAndNumberOfHour()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo2, assignmentService.findByNumberOfHourGreaterThanAndEmployeeIdNot(150,1).get(0));
        assertEquals(2, assignmentService.findByNumberOfHourGreaterThanAndEmployeeIdNot(50,0).size());
        assertEquals(0, assignmentService.findByNumberOfHourGreaterThanAndEmployeeIdNot(400,1).size());

    }

    @Test
    void testFindByNumberOfHourLessThanAndProjectId_shouldReturnAssignment_whenInputProjectIdAndNumberOfHour()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByNumberOfHourLessThanAndProjectId(150,1).get(0));
        assertEquals(0, assignmentService.findByNumberOfHourLessThanAndProjectId(50,0).size());
        assertEquals(1, assignmentService.findByNumberOfHourLessThanAndProjectId(400,2).size());
    }

    @Test
    void testFindByIdNot_shouldReturnAssignment_whenInputId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByIdNot(2).get(0));
        assertEquals(2, assignmentService.findByIdNot(50).size());
    }

    @Test
    void testFindByNumberOfHourNot_shouldReturnAssignment_whenInputNumberOfHour()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByNumberOfHourNot(2).get(0));
        assertEquals(1, assignmentService.findByNumberOfHourNot(100).size());
    }

    @Test
    void testFindByEmployeeIdNot_shouldReturnAssignment_whenInputEmployeeId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByEmployeeIdNot(2).get(0));
        assertEquals(2, assignmentService.findByEmployeeIdNot(100).size());
    }

    @Test
    void testFindByProjectIdNot_shouldReturnAssignment_whenInputProjectId()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        projectService.addProject(projDemo1);
        projectService.addProject(projDemo2);
        assignmentService.addAssignment(assDemo1);
        assignmentService.addAssignment(assDemo2);
        assertEquals(assDemo1, assignmentService.findByProjectIdNot(2).get(0));
        assertEquals(2, assignmentService.findByProjectIdNot(100).size());
    }


}
