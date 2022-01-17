package no.itera.assignment.persistence.department;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository repository;

  @Test
  void persistTest() {
    DepartmentEntity entity = DepartmentEntity.builder()
        .name("departmentName")
        .build();

    assertThat(entity.getId()).isNull();

    repository.saveAndFlush(entity);

    assertThat(entity.getId()).isNotNull().isPositive();
    assertThat(repository.findById(entity.getId())).contains(entity);
  }
}