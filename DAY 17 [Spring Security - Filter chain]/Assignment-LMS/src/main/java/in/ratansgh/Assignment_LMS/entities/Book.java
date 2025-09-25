package in.ratansgh.Assignment_LMS.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private boolean available;

}
