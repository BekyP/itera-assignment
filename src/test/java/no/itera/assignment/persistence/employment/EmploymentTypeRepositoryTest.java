package no.itera.assignment.persistence.employment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmploymentTypeRepositoryTest {

  @Autowired
  private EmploymentTypeRepository repository;

  @Test
  void persistTest() {
    EmploymentTypeEntity entity = EmploymentTypeEntity.builder()
        .name("type123")
        .build();

    assertThat(entity.getId()).isNull();

    repository.saveAndFlush(entity);

    assertThat(entity.getId()).isNotNull().isPositive();

    assertThat(repository.findById(entity.getId())).contains(entity);
  }
}