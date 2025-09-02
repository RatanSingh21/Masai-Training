package in.ratansgh.security.repository;

import in.ratansgh.security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    // extends JpaRepository<entity_name, datatype_primary-key>
}
