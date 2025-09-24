package in.ratansgh.Assignment_LMS.repository;

import in.ratansgh.Assignment_LMS.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
