package no.itera.assignment.persistence.person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository repository;

  @Test
  void persistTest() {
    PersonEntity entity = PersonEntity.builder()
        .age(33)
        .name("ferry")
        .build();

    assertThat(entity.getId()).isNull();

    repository.saveAndFlush(entity);

    assertThat(entity.getId()).isNotNull().isPositive();
    assertThat(repository.findById(entity.getId())).contains(entity);

  }
}