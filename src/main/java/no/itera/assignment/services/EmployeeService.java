package no.itera.assignment.services;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.mappers.EmployeeMapper;
import no.itera.assignment.persistence.employee.EmployeeEntity;
import no.itera.assignment.persistence.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository repository;
  private final EmployeeMapper mapper;

  /**
   * Returns a dto representation of employee with specified department
   *
   * @param personId id of requested employee
   * @return employee (EmployeeDto) for requested personId
   */
  public EmployeeDto fetchEmployeeByPersonId(Integer personId) {
    log.debug("requesting employee with id: {}", personId);

    EmployeeEntity employee = repository.findById(personId)
        .orElseThrow(
            () -> new IllegalStateException("no employee with id: " + personId + " found"));

    log.trace("found employee='{}'", employee);

    return mapper.mapEntityToDto(employee);
  }

  /**
   * Returns list of all active employees - employee is active when he does not have endDate set
   *
   * @return list of all active employees (EmployeeDto)
   */
  public List<EmployeeDto> fetchAllActiveEmployees() {
    List<EmployeeEntity> activeEmployees = repository.findAllByEndDateIsNull();

    log.debug("found {} activeEmployees, mapping to dtos", activeEmployees.size());
    log.trace("activeEmployees='{}'", activeEmployees);

    return mapper.mapEntitiesToDtos(activeEmployees);
  }

  /**
   * Returns list of all active employees - employee is active when he does not have endDate set -
   * grouped by department
   *
   * @return map of all active employees (EmployeeDto) grouped by department
   */
  public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
    return fetchAllActiveEmployees()
        .stream()
        .collect(groupingBy(EmployeeDto::getDepartmentName));
  }
}
