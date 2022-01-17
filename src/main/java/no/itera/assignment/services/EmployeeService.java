package no.itera.assignment.services;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.mappers.EmployeeMapper;
import no.itera.assignment.persistence.employee.EmployeeRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
// TODO
// TODO java docs https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
// TODO unit tests
public class EmployeeService {

  private final EmployeeRepository repository;
  private final EmployeeMapper mapper;

  public EmployeeDto fetchEmployeeByPersonId(Integer personId) {
    throw new NotYetImplementedException(
        "method fetchEmployeeByPersonId(Integer personId) not implemented");
  }

  public List<EmployeeDto> fetchAllActiveEmployees() {
    throw new NotYetImplementedException("method fetchAllActiveEmployees() not implemented");
  }

  public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
    throw new NotYetImplementedException(
        "method fetchActiveEmployeesByDepartment() not implemented");
  }
}
