package in.ratansgh.security.service;

import java.util.List;

public interface Student {

    List<Student> getallStudents();
    Student saveStudent(Student student);
}
