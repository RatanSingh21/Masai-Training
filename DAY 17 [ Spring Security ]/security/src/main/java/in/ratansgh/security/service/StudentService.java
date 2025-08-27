package in.ratansgh.security.service;

import in.ratansgh.security.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements Student {

    private static final List<Student> student = new ArrayList<>();

    public Student addStudent(Student student) {
        student.;
        return student;
    }

    public List<Student> getAllStudents(){
        return student;
    }

}
