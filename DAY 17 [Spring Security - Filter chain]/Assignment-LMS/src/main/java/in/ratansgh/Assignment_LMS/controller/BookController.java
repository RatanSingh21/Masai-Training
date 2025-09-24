package in.ratansgh.Assignment_LMS.controller;

import in.ratansgh.Assignment_LMS.entities.Book;
import in.ratansgh.Assignment_LMS.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/public")
    public List<Book> publicBooks() {
        return bookRepository.findAll();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{id}/reserve")
    public String reserveBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.isAvailable()) {
                book.setAvailable(false);
                bookRepository.save(book);
                return "Book reserved";
            } else {
                return "Book is already reserved";
            }
        }
        return "Book not found";
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book added";
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
            return "Book updated";
        }
        return "Book not found";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted";
        }
        return "Book not found";
    }
}
