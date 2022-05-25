package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DepartmentServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    Department deptDemo1= new Department("Marketing", LocalDate.parse("2011-02-10"));
    Department deptDemo2= new Department("Sale",LocalDate.parse("2011-02-11"));

    DepartmentServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllDepartment_shouldReturnNoData_whenJustCreateTable(){
        List<Department> departments = departmentService.getAll();
        assertEquals(0,departments.size());
    }


    @Test
    void testAddDepartment_shouldAddNewDepartment_whenInputNewDepartment() throws ParseException {
        departmentService.addDepartment(deptDemo1);
        assertEquals(1,departmentService.getAll().size());
    }

    @Test
    void testDeleteDepartment_shouldDeleteDepartment_whenInputADepartmentId() throws ParseException {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        departmentService.deleteDepartment(1);
        assertEquals(1,departmentService.getAll().size());
        assertEquals(Optional.empty(),departmentService.findDepartmentById(1));
    }

    @Test
    void testFindDepartmentById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId() throws ParseException {
        assertEquals(Optional.empty(),departmentService.findDepartmentById(1));
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(deptDemo2,departmentService.findDepartmentById(2).get());
    }

    @Test
    void testFindByName_shouldReturnListOfDepartment_whenInputADate () {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(deptDemo1,departmentService.findByStartDate(LocalDate.parse("2011-02-10")).get(0));
        assertEquals(1,departmentService.findByStartDate(LocalDate.parse("2011-02-10")).size());
    }

    @Test
    void testFindByStartDateBetween_shouldReturnListOfDepartment_whenInputARangeOfDate () {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByStartDateBetween(LocalDate.parse("2011-02-11"),LocalDate.parse("2011-02-12")).size());
        assertEquals(deptDemo2,departmentService.findByStartDateBetween(LocalDate.parse("2011-02-11"),LocalDate.parse("2011-02-12")).get(0));
    }

    @Test
    void testFindByStartDateBefore_shouldReturnListOfDepartment_whenInputADate(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByStartDateBefore(LocalDate.parse("2011-02-11")).size());
        assertEquals(deptDemo1,departmentService.findByStartDateBefore(LocalDate.parse("2011-02-11")).get(0));
    }

    @Test
    void testFindByStartDateAfter_shouldReturnListOfDepartment_whenInputADate(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByStartDateAfter(LocalDate.parse("2011-02-10")).size());
        assertEquals(deptDemo2,departmentService.findByStartDateAfter(LocalDate.parse("2011-02-10")).get(0));
    }

    @Test
    void testFindByNameLike_shouldReturnListOfDepartment_whenInputAName(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByNameLike("Sale").size());
    }

    @Test
    void testFindByNameNotLike_shouldReturnListOfDepartment_whenInputAName(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByNameNotLike("Sale").size());
//        assertEquals(2,departmentService.findByNameNotLike("purchasing").size()); //InvalidDataAccessApiUsageException: Parameter value [\] did not match expected type [java.lang.String (n/a)]; nested exception is java.lang.IllegalArgumentException: Parameter value [\] did not match expected type [java.lang.String (n/a)]
    }

    @Test
    void testFindByNameEndingWith_shouldReturnListOfDepartment_whenInputAnEndingWords(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByNameEndingWith("le").size());
    }

    @Test
    void testFindByNameContaining_shouldReturnListOfDepartment_whenInputAWords(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
//        assertEquals(0,departmentService.findByNameContaining("te").size());
        assertEquals(1,departmentService.findByNameContaining("rket").size());
    }


    @Test
    void testFindByNameIgnoreCase_shouldReturnListOfDepartment_whenInputAName(){
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        assertEquals(1,departmentService.findByNameIgnoreCase("sale").size());
        assertEquals(0,departmentService.findByNameIgnoreCase("Male").size());
    }




}
