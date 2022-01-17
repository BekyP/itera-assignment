package no.itera.assignment.controller;

import lombok.SneakyThrows;
import no.itera.assignment.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private EmployeeService employeeService;

  @DisplayName("should retrieve single employee by ID")
  @Test
  public void testRetrievingOfSingleEmployee() throws Exception {
    mockMvc.perform(get("/employee/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Hasnain Frame")));
  }

  @DisplayName("should retrieve all active employees")
  @Test
  public void testRetrievingOfActiveEmployees() throws Exception {
    mockMvc.perform(get("/employee/active")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(17)));
  }

  @DisplayName("should retrieve all active employees grouped by department")
  @Test
  public void testRetrievingOfActiveEmployeesGroupedByDepartment() throws Exception {
    mockMvc.perform(get("/employee/active/by-department")).andDo(print()).andExpect(status().isOk())

        .andExpect(jsonPath("$.length()", is(5)))

        .andExpect(jsonPath("$['Management'].length()", is(1)))
        .andExpect(jsonPath("$['Technology consulting'].length()", is(9)))
        .andExpect(jsonPath("$['Quality assurance'].length()", is(3)))
        .andExpect(jsonPath("$['Managed services'].length()", is(3)))
        .andExpect(jsonPath("$['Human resources'].length()", is(1)));
  }

  @Test
  @SneakyThrows
  void errorHandlingTest() {
    String errorMsg = "errorMsg";

    when(employeeService.fetchAllActiveEmployees()).thenThrow(new IllegalStateException(errorMsg));

    mockMvc.perform(get("/employee/active")).andDo(print()).andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.status", is("500 INTERNAL_SERVER_ERROR")))
        .andExpect(jsonPath("$.message", is(errorMsg)));
  }
}