package in.ratansgh.Assignment_UT_01.service;

import in.ratansgh.Assignment_UT_01.entity.Employee;
import in.ratansgh.Assignment_UT_01.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployee_validInput_success() {
        Employee emp = new Employee(null, "John", "IT", 5000);
        Employee saved = new Employee(1L, "John", "IT", 5000);
        when(employeeRepository.save(emp)).thenReturn(saved);

        Employee result = employeeService.createEmployee(emp);

        assertEquals(saved.getId(), result.getId());
        assertEquals(saved.getName(), result.getName());
        verify(employeeRepository).save(emp);
    }

    @Test
    void createEmployee_nullName_throwsException() {
        Employee emp = new Employee(null, null, "IT", 5000);
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(emp));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void createEmployee_invalidSalary_throwsException() {
        Employee emp = new Employee(null, "John", "IT", 0);
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(emp));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void getEmployeeById_validId_success() {
        Employee emp = new Employee(1L, "John", "IT", 5000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals(emp.getId(), result.getId());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeById_invalidId_throwsException() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> employeeService.getEmployeeById(1L));
    }

    @Test
    void updateEmployee_validId_success() {
        Employee existing = new Employee(1L, "John", "IT", 5000);
        Employee updated = new Employee(null, "Jane", "HR", 6000);
        Employee saved = new Employee(1L, "Jane", "HR", 6000);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenReturn(saved);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertEquals("Jane", result.getName());
        assertEquals("HR", result.getDepartment());
        assertEquals(6000, result.getSalary());
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(existing);
    }

    @Test
    void updateEmployee_invalidId_throwsException() {
        Employee updated = new Employee(null, "Jane", "HR", 6000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> employeeService.updateEmployee(1L, updated));
    }

    @Test
    void deleteEmployee_validId_success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteEmployee_invalidId_throwsException() {
        when(employeeRepository.existsById(1L)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> employeeService.deleteEmployee(1L));
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
