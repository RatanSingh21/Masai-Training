package in.ratansgh.Assignment_UT_02.service;

import in.ratansgh.Assignment_UT_02.entities.Employee;
import in.ratansgh.Assignment_UT_02.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getEmployeeById_ReturnsEmployee_WhenFound() {
        Employee emp = new Employee(1L, "John", "IT");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        Employee result = employeeService.getEmployeeById(1L);
        assertEquals("John", result.getName());
        assertEquals("IT", result.getDepartment());
    }

    @Test
    void getEmployeeById_ThrowsException_WhenNotFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(2L));
    }

    @Test
    void getEmployeesByDepartment_ReturnsList() {
        List<Employee> emps = Arrays.asList(
                new Employee(1L, "John", "IT"),
                new Employee(2L, "Jane", "IT")
        );
        when(employeeRepository.findByDepartment("IT")).thenReturn(emps);
        List<Employee> result = employeeService.getEmployeesByDepartment("IT");
        assertEquals(2, result.size());
    }
}
