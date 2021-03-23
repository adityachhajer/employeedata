package com.nagarro.communication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.communication.models.Employee;
import com.nagarro.communication.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return repository.findById(id);
	}

	public List<Employee> saveEmployees(List<Employee> employees) {
		return repository.saveAll(employees);
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public String deleteEmployee(Long id) {
		repository.deleteById(id);
		return "Successfully deleted";
	}

	public Employee updateEmployee(Employee employee) {
		Employee existingEmployee = repository.findById(employee.getEmployeeId()).orElse(null);
		existingEmployee.setDateOfBirth(employee.getDateOfBirth());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setEmployeeName(employee.getEmployeeName());
		existingEmployee.setLocation(employee.getLocation());
		return repository.save(existingEmployee);
	}

	public boolean findEmployee(Employee employee) {
		List<Employee> allEmployees = getEmployees();
		for (Employee data : allEmployees) {
			if (employee.getEmployeeName().equals(data.getEmployeeName())
					&& employee.getDateOfBirth().equals(data.getDateOfBirth())
					&& employee.getEmail().equals(data.getEmail())
					&& employee.getLocation().equals(data.getLocation())) {
				return true;
			}
		}
		return false;
	}

	public boolean findById(Long id) {
		return repository.findById(id) != null;
	}
}
