package in.ratansgh.Assignment_UT_02.controller;

import in.ratansgh.Assignment_UT_02.entities.Employee;
import in.ratansgh.Assignment_UT_02.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeById_ReturnsEmployeeJson() throws Exception {
        Employee emp = new Employee(1L, "John", "IT");
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(emp);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    void getEmployeeById_ReturnsNotFound_WhenNotFound() throws Exception {
        Mockito.when(employeeService.getEmployeeById(anyLong())).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/employees/99"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getEmployeesByDepartment_ReturnsListJson() throws Exception {
        Mockito.when(employeeService.getEmployeesByDepartment("IT"))
                .thenReturn(Arrays.asList(
                        new Employee(1L, "John", "IT"),
                        new Employee(2L, "Jane", "IT")
                ));

        mockMvc.perform(get("/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }
}
