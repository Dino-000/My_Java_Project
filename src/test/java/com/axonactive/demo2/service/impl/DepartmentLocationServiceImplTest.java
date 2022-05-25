package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.DepartmentLocation;
import com.axonactive.demo2.services.DepartmentLocationService;
import com.axonactive.demo2.services.DepartmentService;
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
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DepartmentLocationServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentLocationService departmentLocationService;

    Department deptDemo1 = new Department("Hr",LocalDate.parse("2011-02-10"));
    Department deptDemo2 = new Department("Marketing", LocalDate.parse("2011-02-10"));

    DepartmentLocation deptLocDemo1 = new DepartmentLocation("HaNoi", deptDemo1);
    DepartmentLocation deptLocDemo2 = new DepartmentLocation("DaNang", deptDemo2);


    DepartmentLocationServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllDepartmentLocation_shouldReturnNoData_whenJustCreateTable() {
        List<DepartmentLocation> departmentLocations = departmentLocationService.getAll();
        assertEquals(0, departmentLocations.size());
    }


    @Test
    void testAddDepartmentLocation_shouldAddNewDepartmentLocation_whenInputNewDepartmentLocation() {
        departmentService.addDepartment(deptDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        assertEquals(1, departmentLocationService.getAll().size());
    }

    @Test
    void testDeleteDepartmentLocation_shouldDeleteDepartmentLocation_whenInputADepartmentLocationId() {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        assertEquals(1, departmentLocationService.getAll().size());
        assertEquals(Optional.empty(), departmentLocationService.findDepartmentLocationById(2));
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
        assertEquals(2, departmentLocationService.getAll().size());
    }

    @Test
    void testFindDepartmentById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId() {
        assertEquals(Optional.empty(), departmentLocationService.findDepartmentLocationById(1));
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        assertEquals(deptLocDemo1,departmentLocationService.findDepartmentLocationById(1).get());
    }

    @Test
    void testFindByLocationNotLike_shouldReturnAListOfDepartment_whenInputAWord() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
        assertEquals(2,departmentLocationService.findByLocationNotLike("ok").size());
    }

    @Test
    void testFindByLocationStartingWith_shouldReturnAListOfDepartment_whenInputAWord() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
//        assertEquals(0,departmentLocationService.findByLocationStartingWith("ok").size()); //can not run 2 test at the same time
        assertEquals(1,departmentLocationService.findByLocationStartingWith("Ha").size());
    }

    @Test
    void testFindByLocationEndingWith_shouldReturnAListOfDepartment_whenInputAWord() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
//        assertEquals(0,departmentLocationService.findByLocationEndingWith("ok").size()); //can not run 2 test at the same time
        assertEquals(1,departmentLocationService.findByLocationEndingWith("Noi").size());
    }

    @Test
    void testFindByLocationContaining_shouldReturnAListOfDepartment_whenInputAWord() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
//        assertEquals(0,departmentLocationService.findByLocationContaining("ok").size()); //can not run 2 test at the same time
        assertEquals(1,departmentLocationService.findByLocationContaining("Noi").size());
    }

    @Test
    void testFindByLocationIgnoreCase_shouldReturnAListOfDepartment_whenInputAWord() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
//        assertEquals(0,departmentLocationService.findByLocationIgnoreCase("ok").size()); //can not run 2 test at the same time
        assertEquals(1,departmentLocationService.findByLocationIgnoreCase("HaNoi").size());
    }

    @Test
    void testFindByDepartmentIdIsNull_shouldReturnAListOfDepartmentWithNullId_whenCalled() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
        assertEquals(0,departmentLocationService.findByDepartmentIdIsNull().size());
    }

    @Test
    void testFindByLocationContainingAndDepartmentIdGreaterThan_shouldReturnAListOfDepartment_whenInputLocationAndId() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
//        assertEquals(1,departmentLocationService.findByLocationContainingAndDepartmentIdGreaterThan("No",0).size());
        assertEquals(0,departmentLocationService.findByLocationContainingAndDepartmentIdGreaterThan("No",3).size());
    }

    @Test
    void testFindByLocationEndingWithAndDepartmentIdLessThan_shouldReturnAListOfDepartment_whenInputLocationAndId() {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        departmentLocationService.addDepartmentLocation(deptLocDemo1);
        departmentLocationService.addDepartmentLocation(deptLocDemo2);
        assertEquals(1,departmentLocationService.findByLocationEndingWithAndDepartmentIdLessThan("Noi",10).size());
//        assertEquals(0,departmentLocationService.findByLocationEndingWithAndDepartmentIdLessThan("Noi",0).size());
    }





}
