package com.nagarro.communication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.communication.models.Employee;
import com.nagarro.communication.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping(value = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		System.out.println(employee.toString());
		if (employeeService.findEmployee(employee)) {
			return new ResponseEntity<>("Data is already present!", HttpStatus.ALREADY_REPORTED);
		}
		employeeService.saveEmployee(employee);
		return ResponseEntity.ok("SuccessFully added..!");
	}

	@PutMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
//		System.out.println(id));
		if (!employeeService.findById(id)) {
			return new ResponseEntity<>("Data is not present into out database", HttpStatus.BAD_REQUEST);
		}
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok("Data is successfully updated");
	}

}
