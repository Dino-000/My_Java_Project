package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(EmployeeRestController.PATH)

public class EmployeeRestController {
    private final EmployeeService employeeService;
    public static final String PATH = "/api/employees";

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such employee"));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestParam Integer Id, @RequestBody Employee newEmployee) {
        Employee addedEmployee = employeeService.addEmployee(newEmployee);
        return ResponseEntity.created(URI.create(PATH+"/"+addedEmployee.getId())).body(addedEmployee);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestParam Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestParam Integer id,@RequestBody Employee employee) throws ResourceNotFoundException {
        Employee updatedEmployee = employeeService.updateEmployee(id,employee);
        return ResponseEntity.created(URI.create(PATH+"/"+updatedEmployee.getId())).body(updatedEmployee);
    }
}
