package in.ratansgh.security.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // maps this to database automatically
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    // primary key with auto incremental value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long STDId;

    private String name;
    private String email;
    private String age;


}
