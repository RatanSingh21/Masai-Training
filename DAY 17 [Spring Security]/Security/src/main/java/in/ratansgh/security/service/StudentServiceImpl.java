package in.ratansgh.security.service;

import in.ratansgh.security.repository.StudentRepo;
import in.ratansgh.security.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends Student {

    @Autowired
    private StudentRepo stdrepo;

    public List<Student> getAllStudents(){
        return stdrepo.findAll();
    }

    public Student saveStudent(Student student){
        return stdrepo.save(student);
    }

}
