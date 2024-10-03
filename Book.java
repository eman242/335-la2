
import java.util.Comparator;

/*
 * Class Book: represents a book object with a title and author
 * Authors: Elle Knapp(dmknapp2385) and Eman Ayaz(emanayaz)
 * 
 * Encapsulation is achieved in the immutability of the object and scope of the
 * instance variables. All fields are private and information in the fields is
 * only returned in the classes toString method this makes the class  immutable
 * as once a book object is craeted, none of the internal data can be altered. 
 */
public final class Book {

    private String author;
    private String title;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /*
     * Getters
     */
    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }


    /*
     * Class CompareByTitle: Compares two book objects based on their titles.
     *                       Comparison is not case sensitive
     */
    public static class CompareByTitle implements Comparator<Book> {

        public int compare(Book bOne, Book bTwo) {
            return bOne.title.compareToIgnoreCase(bTwo.title);
        }
    }

    /*
     * Class CompareByAuthor: Compares two book objects based on their authors.
     *                       Comparison is not case sensitive.
     */
    public static class CompareByAuthor implements Comparator<Book> {

        public int compare(Book bOne, Book bTwo) {
            return bOne.author.compareToIgnoreCase(bTwo.author);
        }
    }

    /*
     * Returns a string with title : author
     */
    public String toString() {
        return getTitle() + " : " + getAuthor();
    }

    /*
     * Equals method compares book object with another. Title and author are
     * compared and not case sensitive
     */
    public boolean equals(Book other) {

        return this.title.toLowerCase().equals(other.title.toLowerCase())
                && this.author.toLowerCase().equals(other.author.toLowerCase());

    }
}
