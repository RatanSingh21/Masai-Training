package in.ratansgh.Enrollment.service;


import in.ratansgh.Enrollment.entity.Enrollment;
import in.ratansgh.Enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repository;
    private final RestTemplate restTemplate;

    public EnrollmentService(EnrollmentRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // Optionally validate student and course via REST calls
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        return repository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }
}

