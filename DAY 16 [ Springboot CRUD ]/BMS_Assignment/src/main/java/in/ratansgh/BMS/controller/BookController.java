package in.ratansgh.BMS.controller;

import in.ratansgh.BMS.model.Book;
import in.ratansgh.BMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // POST /books
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // GET /books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    // PUT /books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id) ? "Book deleted." : "Book not found.";
    }

    // GET /books/search?author=xyz
    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    // GET /books/filter?price=500
    @GetMapping("/filter")
    public List<Book> getBooksCheaperThan(@RequestParam double price) {
        return bookService.getBooksCheaperThan(price);
    }

    // Bonus: GET /books/count
    @GetMapping("/count")
    public int getTotalBooks() {
        return bookService.getTotalBooks();
    }

    // Bonus: GET /books/most-expensive
    @GetMapping("/most-expensive")
    public Book getMostExpensiveBook() {
        return bookService.getMostExpensiveBook();
    }
}

