package in.ratansgh.security.service;

import java.util.List;

public interface Student {

    List<Student> getAllStudents();
    Student saveStudent(Student student);
}
