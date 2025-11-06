package in.ratansgh.Course.repository;

import in.ratansgh.Course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
