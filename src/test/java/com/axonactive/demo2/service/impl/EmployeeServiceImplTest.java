package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.services.DepartmentService;
import com.axonactive.demo2.services.EmployeeService;
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
class EmployeeServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;
    Department deptDemo1= new Department("Marketing", LocalDate.parse("2011-02-10"));
    Department deptDemo2= new Department("Sale",LocalDate.parse("2011-02-10"));
    Employee empDemo1= new Employee("A001",LocalDate.parse("1993-01-10"),"Dino","male","Lee","De",2000,deptDemo1);
    Employee empDemo2= new Employee("A002",LocalDate.parse("1998-01-10"),"Ginkel","male","Van","De",4000,deptDemo2);

    EmployeeServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllEmployee_shouldReturnNoData_whenJustCreateTable(){
        List<Employee> employees = employeeService.getAll();
        assertEquals(0,employees.size());
    }


    @Test
    void testAddEmployee_shouldAddNewEmployee_whenInputNewEmployee()  {
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        assertEquals(1,employeeService.getAll().size());
    }

    @Test
    void testDeleteEmployee_shouldDeleteEmployee_whenInputAEmployeeId()  {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(2,employeeService.getAll().size());
        employeeService.deleteEmployee(1);
        assertEquals(1,employeeService.getAll().size());
        assertEquals(Optional.empty(),employeeService.findEmployeeById(1));

    }

    @Test
    void testFindEmployeeById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId()  {
        assertEquals(Optional.empty(),employeeService.findEmployeeById(1));
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(empDemo2,employeeService.findEmployeeById(2).get());
    }

    @Test
    void testFindByDepartmentId_shouldReturnCorrespondingEmployee_whenInputDepartmentId() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(empDemo1,employeeService.findByDepartmentId(1).get(0));
    }

    @Test
    void testFindByDateOfBirth_shouldReturnCorrespondingEmployee_whenInputDateOfBirth() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(empDemo2,employeeService.findByDateOfBirth(LocalDate.parse("1998-01-10")).get(0));
    }

    @Test
    void testFindBySalaryGreaterThan_shouldReturnCorrespondingEmployee_whenInputMinSalary() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(1,employeeService.findBySalaryGreaterThan(3000).size());
    }

    @Test
    void testFindByDepartmentIdAndSalaryLessThan_shouldReturnCorrespondingEmployee_whenInputDepartmentIdAndMinSalary() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(1,employeeService.findByDepartmentIdAndSalaryLessThan(1,3000).size());
    }

    @Test
    void testFindByLastNameStartingWithAndDepartmentIdNot_shouldReturnCorrespondingEmployee_whenInputLastNameAndId() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(1,employeeService.findByLastNameStartingWithAndDepartmentIdNot("L",2).size());
    }

    @Test
    void testFindBySalaryBetween_shouldReturnCorrespondingEmployee_whenInputMinAndMaxSalary() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(1,employeeService.findBySalaryBetween(1000,2500).size());
        assertEquals(2,employeeService.findBySalaryBetween(1000,25000).size());

    }


    @Test
    void testFindByDateOfBirthBeforeAndDepartmentId_shouldReturnCorrespondingEmployee_whenInputADateAndDepartmentId() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        assertEquals(1,employeeService.findByDateOfBirthBeforeAndDepartmentId(LocalDate.parse("1999-10-10"),1).size());
    }
}
