package no.itera.assignment.mappers;

import java.util.List;
import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.persistence.employee.EmployeeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

  @Mapping(source = "department.name", target = "departmentName")
  @Mapping(source = "person.name", target = "name")
  @Mapping(source = "person.age", target = "age")
  EmployeeDto mapEntityToDto(EmployeeEntity entity);

  List<EmployeeDto> mapEntitiesToDtos(List<EmployeeEntity> entities);
}
