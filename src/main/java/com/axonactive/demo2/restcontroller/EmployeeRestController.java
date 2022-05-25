package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")

public class EmployeeRestController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getAllEmployee(){
        return employeeService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such employee"));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/add")
    public void createEmployee(@RequestBody Employee dept) {
        employeeService.addEmployee(dept);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public void updateEmployee(@PathVariable(value = "id") Integer id,@RequestBody Employee employee) throws ResourceNotFoundException {
        employeeService.updateEmployee(id,employee);
    }
}
