package mz.pitech.training.emsbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mz.pitech.training.emsbackend.dto.EmployeeDto;
import mz.pitech.training.emsbackend.entity.Employee;
import mz.pitech.training.emsbackend.exception.ResourceNotFoundException;
import mz.pitech.training.emsbackend.mapper.EmployeeMapper;
import mz.pitech.training.emsbackend.repository.EmployeeRepository;
import mz.pitech.training.emsbackend.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = findEmployeeById(employeeId);
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeRepository.findAll().stream()
				.map(EmployeeMapper::mapToEmployeeDto)
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee) {
		Employee employee = findEmployeeById(id);

		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());

		Employee updatedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = findEmployeeById(employeeId);
		employeeRepository.delete(employee);
	}

	private Employee findEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Funcionário não existe com o ID fornecido: " + employeeId));
	}
}
