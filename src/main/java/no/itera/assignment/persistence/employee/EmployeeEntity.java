package no.itera.assignment.persistence.employee;

import com.sun.istack.NotNull;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.itera.assignment.persistence.department.DepartmentEntity;
import no.itera.assignment.persistence.employment.EmploymentTypeEntity;
import no.itera.assignment.persistence.person.PersonEntity;

@Entity
@Table(name = "EMPLOYEE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

  @Id
  // might not work on older databases (oracle11, etc.) - if that`s the case use GenerationType.SEQUENCE
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSON_ID")
  private Integer id;

  @NotNull
  private Instant startDate;

  @NotNull
  private Instant endDate;

  // employee could be only in one department?
  @ManyToOne
  @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
  private DepartmentEntity department;

  // employee could have only one employment type?
  @ManyToOne
  @JoinColumn(name = "EMPLOYMENT_TYPE_ID", nullable = false)
  private EmploymentTypeEntity employmentType;

  @OneToOne
  @JoinColumn(name = "PERSON_ID", nullable = false)
  private PersonEntity person;

}
