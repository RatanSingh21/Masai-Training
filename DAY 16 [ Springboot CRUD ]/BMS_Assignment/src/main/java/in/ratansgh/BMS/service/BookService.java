package in.ratansgh.BMS.service;

import in.ratansgh.BMS.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final List<Book> books = new ArrayList<>();

    // Create
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    // Read All
    public List<Book> getAllBooks() {
        return books;
    }

    // Read by ID
    public Book getBookById(Integer id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update
    public Book updateBook(Integer id, Book updatedBook) {
        Optional<Book> existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            return book;
        }
        return null;
    }

    // Delete
    public boolean deleteBook(Integer id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    // Filter by author
    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    // Filter by price
    public List<Book> getBooksCheaperThan(double price) {
        return books.stream()
                .filter(book -> book.getPrice() < price)
                .collect(Collectors.toList());
    }

    // Bonus: Total number of books
    public int getTotalBooks() {
        return books.size();
    }

    // Bonus: Most expensive book
    public Book getMostExpensiveBook() {
        return books.stream()
                .max(Comparator.comparing(Book::getPrice))
                .orElse(null);
    }
}

