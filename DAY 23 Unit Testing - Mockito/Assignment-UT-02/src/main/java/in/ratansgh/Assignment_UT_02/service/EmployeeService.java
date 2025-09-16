package in.ratansgh.Assignment_UT_02.service;


import in.ratansgh.Assignment_UT_02.entities.Employee;
import in.ratansgh.Assignment_UT_02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

   @Autowired
    private EmployeeRepository employeeRepository;

   public ResponseEntity<Employee> getEmployeeById (Long id ){
       return employeeRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
   }

   public List<Employee> getByDepartment (String department) {
       return employeeRepository.findByDepartment(department);
   }

}
