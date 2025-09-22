package in.ratansgh.Assignment_LMS.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<String> getAllUsers() {
        return Arrays.asList("admin", "librarian", "student", "guest");
    }

    @PostAuthorize("returnObject != null && authentication.name == 'admin'")
    @GetMapping("/admin/reports")
    public String getReports() {
        return "Admin reports";
    }
}

