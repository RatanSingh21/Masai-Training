package in.ratansgh.Student.service;

import in.ratansgh.Student.entity.Student;
import in.ratansgh.Student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student student) {
        return repository.findById(id).map(existing -> {
            existing.setName(student.getName());
            existing.setAge(student.getAge());
            existing.setCourse(student.getCourse());
            return repository.save(existing);
        });
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
