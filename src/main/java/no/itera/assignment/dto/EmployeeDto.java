package no.itera.assignment.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {

  private String name;
  private int age;
  private String departmentName;
  private Instant startDate;
  private Instant endDate;
}
