
import java.util.Comparator;

public final class Book {

    //why not declared as private?, Eman declaring as private
    private String author;
    private String title;

    public Book(String title, String author) {
        //books initialized to be unread
        this.title = title;
        this.author = author;

    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }


    public static class CompareByTitle implements Comparator<Book> {

        public int compare(Book bOne, Book bTwo) {
            return bOne.title.compareToIgnoreCase(bTwo.title);
        }
    }

    public static class CompareByAuthor implements Comparator<Book> {

        public int compare(Book bOne, Book bTwo) {
            return bOne.author.compareToIgnoreCase(bTwo.author);
        }
    }

    public String toString() {
        return getTitle() + " : " + getAuthor();
    }

    public boolean equals(Book other) {
        return this.title.toLowerCase().equals(other.title.toLowerCase())
                && this.author.toLowerCase().equals(other.author.toLowerCase());
    }
}
