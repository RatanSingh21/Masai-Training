package in.ratansgh.security.controller;

import in.ratansgh.security.exception.InvalidException;
import in.ratansgh.security.model.Student;
import in.ratansgh.security.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl stdservice;

    @PostMapping("/student")
    public ResponseEntity<Map<String, Object> > createStd(@RequestBody Student student){

        // posting user
        Student savedStudent =  stdservice.saveStudent(student);

        // Wrapping metadata for response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("hashcode", savedStudent.hashCode());
        response.put("data",savedStudent);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<Map<String, Object> > getallStudent(){

        // fetching all the user
        List<Student> student =  stdservice.getAllStudents();

        // Wrapping metadata for response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", student.size());
        response.put("data", student);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello/{id}")
    public ResponseEntity<String> demoException(@PathVariable("id") Integer id){
        if (id> 10){
            int result = id/0; // always arithmetic exceptions
            return new ResponseEntity<String>("Arithmetic error" , HttpStatus.OK); // this call genric exception
            // handler for the same not Invalid one.
        }
        else {
            throw new InvalidException("Id less than 10 not acceptable");
        }
    }

}
