package in.ratansgh;

public class Book {

    private final String id;
    private final String title;
    private final String Author;


    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        Author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }
}
