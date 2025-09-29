package in.ratansgh.Gradle_Build_Tool.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {

    private String message;
    private String details;
    private LocalDateTime timestamp;
}
