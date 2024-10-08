package mz.pitech.training.emsbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import mz.pitech.training.emsbackend.dto.EmployeeDto;
import mz.pitech.training.emsbackend.service.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeDto> criarFuncionario(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto funcionarioSalvo = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> obterFuncionarioPorId(@PathVariable("id") Long funcionarioId) {
		EmployeeDto employeeDto = employeeService.getEmployeeById(funcionarioId);
		return ResponseEntity.ok(employeeDto);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> obterTodosFuncionarios() {
		List<EmployeeDto> funcionarios = employeeService.getAllEmployees();
		return ResponseEntity.ok(funcionarios);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> atualizarFuncionario(@PathVariable("id") Long funcionarioId,
			@RequestBody EmployeeDto funcionarioAtualizado) {
		EmployeeDto employeeDto = employeeService.updateEmployee(funcionarioId, funcionarioAtualizado);
		return ResponseEntity.ok(employeeDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirFuncionario(@PathVariable("id") Long funcionarioId) {
		employeeService.deleteEmployee(funcionarioId);
		return ResponseEntity.ok("Funcionário excluído com sucesso");
	}
}