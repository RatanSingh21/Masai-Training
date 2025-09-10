package in.ratansgh.EMS_Assignment.service;

import in.ratansgh.EMS_Assignment.entity.Employee;
import in.ratansgh.EMS_Assignment.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

        @Autowired
        private EmployeeRepo employeeRepository;

        // Create
        @Override
        public Employee addEmployee(Employee employee) {
            return employeeRepository.save(employee);
        }


        // Read all
        @Override
        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }

        // Read by ID
        @Override
        public Optional<Employee> getEmployeeById(Long id) {
            return employeeRepository.findById(id);
        }

        // Update
        @Override
        public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
            return employeeRepository.findById(id).map(existing -> {
                existing.setFirstName(updatedEmployee.getFirstName());
                existing.setLastName(updatedEmployee.getLastName());
                existing.setEmail(updatedEmployee.getEmail());
                existing.setDepartment(updatedEmployee.getDepartment());
                existing.setSalary(updatedEmployee.getSalary());
                return employeeRepository.save(existing);
            });
        }

        // Delete
        @Override
        public boolean deleteEmployee(Long id) {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return true;
            }
            return false;
        }

        // Find by department
        @Override
        public List<Employee> getEmployeesByDepartment(String department) {
            return employeeRepository.findByDepartment(department);
        }

        // Find with salary > min
        @Override
        public List<Employee> getEmployeesWithMinSalary(double minSalary) {
            return employeeRepository.findBySalaryGreaterThan(minSalary);
        }

        // Search by name (partial match)
        @Override
        public List<Employee> searchEmployeesByName(String name) {
            return employeeRepository.searchByName(name);
        }

        // Get all emails
        @Override
        public List<String> getAllEmployeeEmails() {
            return employeeRepository.findAllEmails();
        }

        // Get average salary
        @Override
        public double getAverageSalary() {
            Double avg = employeeRepository.getAverageSalary();
            return avg != null ? avg : 0.0;
        }

}
