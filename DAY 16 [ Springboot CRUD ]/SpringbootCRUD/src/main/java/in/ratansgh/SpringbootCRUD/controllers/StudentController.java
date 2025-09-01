package in.ratansgh.SpringbootCRUD.controllers;

import in.ratansgh.SpringbootCRUD.models.Student;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> student = new ArrayList<>();

    public StudentController(){
        student.add(new Student("Sdsds", 1,"23"));
        student.add(new Student("Vig", 2,"23"));
        student.add(new Student("Nash", 3,"23"));
        student.add(new Student("Nevil", 4,"223"));
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return student;
    }

    @GetMapping("/student/{rollno}")
    public ResponseEntity< List<Student>> getStudentRoll(@PathVariable("rollno") Integer roll){
        List<Student> list = student.stream()
                .filter(s-> s.getRollNo()==roll)
                .toList();

        if (list.size()==0){
            throw new IllegalArgumentException("Student does not have student with roll no: " + roll);
        }

        // passed value with headers, happens only because of response entity..
        HttpHeaders hh = new HttpHeaders();
        hh.add("jwt", "qwertyuiop");
        hh.add("user", "admin");

        return new ResponseEntity<List<Student>>(list, hh, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudents(@RequestBody Student newStudent){
        student.add(newStudent);
        return new ResponseEntity<String>("Student added successfully...", HttpStatus.OK);
    }

    @PutMapping("/student/{rollNo}")
    public ResponseEntity<Student> editStudents(@RequestBody Student updateStudent,
                                               @PathVariable("rollNo") Integer roll){

        boolean flag = true;

        for (Student st : student) {
            if (st.getRollNo() == roll) {
                st.setName(updateStudent.getName());
                st.setMarks(updateStudent.getMarks());

                flag = false;

                return new ResponseEntity<Student>(updateStudent, HttpStatus.OK);
            }
        }
        throw new IllegalArgumentException("Student not found");
    }

    @DeleteMapping("/student/{rollNo}")
    public ResponseEntity<String> deleteUser(@PathVariable("rollNo") Integer roll){
        boolean flag = student.removeIf(s -> s.getRollNo() == roll);

        if(flag){
            return new ResponseEntity<String>("Student deleted sucessfully..", HttpStatus.OK);
        }

        throw new IllegalArgumentException("Student not deleted...");
    }
}
