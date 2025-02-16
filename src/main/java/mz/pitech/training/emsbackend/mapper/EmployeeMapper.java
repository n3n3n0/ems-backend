package mz.pitech.training.emsbackend.mapper;

import org.springframework.stereotype.Component;

import mz.pitech.training.emsbackend.dto.EmployeeDto;
import mz.pitech.training.emsbackend.entity.Employee;

@Component
public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		if (employee == null) {
			return null;
		}
		return EmployeeDto.builder()
				.id(employee.getId())
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.email(employee.getEmail())
				.build();
	}

	public static Employee mapToEmployee(EmployeeDto dto) {
		if (dto == null) {
			return null;
		}
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());
		return employee;
	}
}
