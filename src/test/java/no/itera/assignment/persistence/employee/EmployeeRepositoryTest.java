package no.itera.assignment.persistence.employee;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository repository;

  @Test
  void findTest() {
    assertThat(repository.findAll()).hasSize(20);

    EmployeeEntity entity = repository.getById(1);

    assertThat(entity.getDepartment()).isNotNull();
    assertThat(entity.getPerson()).isNotNull();
    assertThat(entity.getEmploymentType()).isNotNull();
  }
}