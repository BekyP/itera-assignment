package no.itera.assignment.persistence.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

  List<EmployeeEntity> findAllByEndDateIsNull();
}
