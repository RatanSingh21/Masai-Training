package in.ratansgh.Enrollment.repository;

import in.ratansgh.Enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByStudentId(Long studentId);
}
