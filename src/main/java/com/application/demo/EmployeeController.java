package com.application.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	 @Autowired
	  EmployeeRepository employeeRepository;

		 
		  @GetMapping("/employees")
		  public List<Employee> getAllEmployees() { 
			  return employeeRepository.findAll(); }
		 
	    @GetMapping("/employees/{id}")
	    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	    {
	        Optional<Employee> employee = employeeRepository.findById(employeeId);
	        return ResponseEntity.ok().body(employee);
	    }
	    
	    @PostMapping("/insertemployees")
	    public Employee createEmployee( @RequestBody Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    @PutMapping("/putemployees/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @RequestBody Employee employeeDetails) {
	        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
	        employee.setEmailId(employeeDetails.getEmailId());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        final Employee updatedEmployee = employeeRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee);
	    }

	    @DeleteMapping("/delemployees/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
	    {
	        Employee employee = (Employee) employeeRepository.findById(employeeId).orElseThrow();

	        employeeRepository.delete(employee);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
