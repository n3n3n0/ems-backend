package mz.pitech.training.emsbackend.service;

import java.util.List;

import mz.pitech.training.emsbackend.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(Long employeeId);
	List<EmployeeDto> getAllEmployees();
	EmployeeDto updateEmployee(Long id,EmployeeDto updateEmployee);
	void deleteEmployee(Long employeeId);
}
