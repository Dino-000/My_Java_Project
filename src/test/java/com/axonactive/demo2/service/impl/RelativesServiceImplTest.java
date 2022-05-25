package com.axonactive.demo2.service.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.entities.Relatives;
import com.axonactive.demo2.services.DepartmentService;
import com.axonactive.demo2.services.EmployeeService;
import com.axonactive.demo2.services.RelativesService;
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
class RelativesServiceImplTest {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RelativesService relativesService;

    Department deptDemo1= new Department("Marketing", LocalDate.parse("2011-02-10"));
    Department deptDemo2= new Department("Marketing",LocalDate.parse("2011-02-10"));
    Employee empDemo1= new Employee("A001","Dino","male","Lee","De",2000,deptDemo1);
    Employee empDemo2= new Employee("A002","Ginkel","male","Van","De",4000,deptDemo2);
    Relatives relDemo1 = new Relatives("Van De Beck","38754834783647",1,"Cousin",empDemo1);
    Relatives relDemo2 = new Relatives("Van De Hell","83263637643",1,"Father",empDemo2);


    RelativesServiceImplTest() throws ParseException {
    }

    @Test
    void testGetAllRelatives_shouldReturnNoData_whenJustCreateTable(){
        List<Relatives> relatives = relativesService.getAll();
        assertEquals(0,relatives.size());
    }


    @Test
    void testAddRelatives_shouldAddNewRelatives_whenInputNewRelatives()  {
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        relativesService.addRelatives(relDemo1);
        assertEquals(1,relativesService.getAll().size());
    }

    @Test
    void testDeleteRelatives_shouldDeleteRelatives_whenInputARelativesId()  {
        departmentService.addDepartment(deptDemo2);
        departmentService.addDepartment(deptDemo1);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(2,relativesService.getAll().size());
        relativesService.deleteRelatives(1);
        assertEquals(1,relativesService.getAll().size());
        assertEquals(Optional.empty(),relativesService.findRelativesById(1));

    }

    @Test
    void testFindRelativesById_shouldReturnObjectMatchWithGivenId_whenIsGivenAnId()  {
        assertEquals(Optional.empty(),relativesService.findRelativesById(1));
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(relDemo2,relativesService.findRelativesById(2).get());
    }


    @Test
    void testFindByRelationShip_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(0,relativesService.findByRelationShip("Cousn").size());
        assertEquals(1,relativesService.findByRelationShip("Cousin").size());

    }

    @Test
    void testFindByFullNameContaining_shouldReturnRelative_whenInputAWord()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(0,relativesService.findByFullNameContaining("Dino").size());
//        assertEquals(2,relativesService.findByFullNameContaining("De").size());

    }

    @Test
    void testFindByGender_shouldReturnRelative_whenInputGender()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(2,relativesService.findByGender(1).size());
        assertEquals(0,relativesService.findByGender(2).size());
    }

    @Test
    void testFindByGenderAndRelationship_shouldReturnRelative_whenInputRelationshipAndGender()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(1,relativesService.findByGenderAndRelationship(1,"Cousin").size());
        assertEquals(0,relativesService.findByGenderAndRelationship(1,"Hr").size());
    }

    @Test
    void testFindByPhoneNumberContaining_shouldReturnRelative_whenInputANumber()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(1,relativesService.findByPhoneNumberContaining("363").size());
//        assertEquals(0,relativesService.findByPhoneNumberContaining("00000").size());
    }

    @Test
    void testFindByRelationshipNot_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(1,relativesService.findByRelationshipNot("Cousin").size());
        assertEquals(2,relativesService.findByRelationshipNot("00000").size());
    }

    @Test
    void testFindByPhoneNumberEndingWithAndRelationship_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
//        assertEquals(1,relativesService.findByPhoneNumberEndingWithAndRelationship("647","Cousin").size());
        assertEquals(0,relativesService.findByPhoneNumberEndingWithAndRelationship("00000","Hr").size());
    }

    @Test
    void testFindByFullNameStartingWithAndGender_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
//        assertEquals(2,relativesService.findByFullNameStartingWithAndGender("Van",1).size());
        assertEquals(0,relativesService.findByFullNameStartingWithAndGender("00000",1).size());
    }
    @Test
    void testFindByIdNot_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(2,relativesService.findByIdNot(0).size());
        assertEquals(1,relativesService.findByIdNot(1).size());
    }

    @Test
    void testfindByRelationshipNotAndGenderNot_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(1,relativesService.findByRelationshipNotAndGenderNot("Cousin",0).size());
        assertEquals(0,relativesService.findByRelationshipNotAndGenderNot("Cousin",1).size());
    }

    @Test
    void testFindByEmployeeId_shouldReturnRelative_whenInputRelationship()  {
        departmentService.addDepartment(deptDemo1);
        departmentService.addDepartment(deptDemo2);
        employeeService.addEmployee(empDemo1);
        employeeService.addEmployee(empDemo2);
        relativesService.addRelatives(relDemo1);
        relativesService.addRelatives(relDemo2);
        assertEquals(1,relativesService.findByEmployeeId(1).size());
        assertEquals(0,relativesService.findByEmployeeId(0).size());
    }

}
