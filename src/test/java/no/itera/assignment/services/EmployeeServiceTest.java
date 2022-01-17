package no.itera.assignment.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.mappers.EmployeeMapper;
import no.itera.assignment.persistence.employee.EmployeeEntity;
import no.itera.assignment.persistence.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @InjectMocks
  private EmployeeService service;

  @Mock
  private EmployeeRepository repository;

  @Mock
  private EmployeeMapper mapper;

  @Test
  void fetchEmployeeByPersonIdTest() {
    int id = 1;
    EmployeeDto dto = new EmployeeDto();
    EmployeeEntity entity = new EmployeeEntity();

    when(repository.findById(id)).thenReturn(Optional.of(entity));
    when(mapper.mapEntityToDto(entity)).thenReturn(dto);

    EmployeeDto resultDto = service.fetchEmployeeByPersonId(id);

    assertThat(resultDto).isEqualTo(dto);
  }


  @Test
  void fetchEmployeeByPersonIdNoEmployeeFoundTest() {
    int id = 1;

    when(repository.findById(id)).thenReturn(Optional.empty());

    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> service.fetchEmployeeByPersonId(id));

    assertThat(e).hasMessageStartingWith("no employee with id");
  }
}