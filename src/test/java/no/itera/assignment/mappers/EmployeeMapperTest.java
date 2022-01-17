package no.itera.assignment.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.persistence.department.DepartmentEntity;
import no.itera.assignment.persistence.employee.EmployeeEntity;
import no.itera.assignment.persistence.person.PersonEntity;
import org.junit.jupiter.api.Test;

class EmployeeMapperTest {

  private EmployeeMapper mapper = new EmployeeMapperImpl();

  @Test
  void entityToDtoTest() {

    EmployeeEntity entity = getTestEntity();

    EmployeeDto dto = mapper.mapEntityToDto(entity);

    assertThat(dto.getAge()).isEqualTo(entity.getPerson().getAge());
    assertThat(dto.getName()).isEqualTo(entity.getPerson().getName());
    assertThat(dto.getDepartmentName()).isEqualTo(entity.getDepartment().getName());
    assertThat(dto.getEndDate()).isEqualTo(entity.getEndDate());
    assertThat(dto.getStartDate()).isEqualTo(entity.getStartDate());
  }

  private EmployeeEntity getTestEntity() {
    return EmployeeEntity.builder()
        .id(10)
        .endDate(Instant.now())
        .startDate(Instant.now())
        .person(PersonEntity.builder()
            .name("ferry")
            .age(40)
            .build())
        .department(DepartmentEntity.builder()
            .name("department1")
            .build())
        .build();
  }
}