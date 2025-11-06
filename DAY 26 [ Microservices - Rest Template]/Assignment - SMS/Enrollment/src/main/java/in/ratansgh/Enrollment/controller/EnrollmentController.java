package in.ratansgh.Enrollment.controller;

import in.ratansgh.Enrollment.entity.Enrollment;
import in.ratansgh.Enrollment.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public Enrollment enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return service.enrollStudent(studentId, courseId);
    }

    @GetMapping("/{studentId}")
    public List<Enrollment> getEnrollments(@PathVariable Long studentId) {
        return service.getEnrollmentsByStudent(studentId);
    }
}

