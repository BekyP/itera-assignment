package no.itera.assignment.controller;

import lombok.RequiredArgsConstructor;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.mappers.EmployeeMapper;
import no.itera.assignment.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/{personId}")
  public EmployeeDto fetchEmployeeByPersonId(@PathVariable Integer personId) {
    return employeeService.fetchEmployeeByPersonId(personId);
  }

  @GetMapping("/active")
  public List<EmployeeDto> fetchAllActiveEmployees() {
    return employeeService.fetchAllActiveEmployees();
  }

  @GetMapping("/active/by-department")
  public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
    return employeeService.fetchActiveEmployeesByDepartment();
  }

}
