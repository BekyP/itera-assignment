package no.itera.assignment.persistence.employment;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYMENT_TYPE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentTypeEntity {

  @Id
  // might not work on older databases (oracle11, etc.) - if that`s the case use GenerationType.SEQUENCE
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  // TODO could be enum? - currently has only 3 types
  private String name;
}
