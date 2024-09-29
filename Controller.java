
import java.util.ArrayList;

/* 
 * class Controller represents a controller between the MyLibrary view and the
 * Library model. 
 * 
 * Authors: Elle Knapp(dmknapp2385) and Eman Ayaz(emanayaz)
 * 
 * Encapsulation is maintained in this class as no code has direct access to the
 * library object stored in this class and cannot make calls to the library class
 * without first going through the various methods in the Controller class.
 *  Additionally, any list returned via these methods are copies
 * of the origina data stored in library. 
 */
public class Controller {

    private Library library;

    public Controller() {
        this.library = new Library();
    }

    /*
     * Method calls the library search methods based on input
     * 
     * Parameters: searchP is a string that is either "title" or "author"
     *             toSearch is a string to look for
     * 
     * Returns: an ArrayList of book objects matching the specified inputs or
     *          an empty list.
     */
    public ArrayList<Book> search(String searchP, String toSearch) {
        if (searchP.equals("title")) {
            return library.searchByTitle(toSearch);
        }
        return library.searchByAuthor(toSearch);
    }

    /*
     * Method calls the library search methods based on rating
     * 
     * Parameters: rating is an integer value between 1 and 5.
     * 
     * Returns: an ArrayList of book objects matching the specified rating or
     *          an empty list.
     */
    public ArrayList<Book> search(int rating) {
        return library.searchByRating(rating);

    }

    /*
     * Method adds book to the library
     * 
     * Parameters: title is a string 
     *             author is a string
     * 
     * @throws an IllegalArgumentException is title or author is empty or if
     *         library does not contain the specified book.
     */
    public void addBook(String title, String author) throws IllegalArgumentException {
        if (library.contains(title, author)) {
            throw new IllegalArgumentException("This book already exists.");
        }
        if (title.equals("") || author.equals("")) {
            throw new IllegalArgumentException("Must have both title and author.");
        }
        library.addBook(title, author);

    }

    /*
     * Method reads a book in the library if it exists, otherwise alerts the user
     * that no such book exists.
     * 
     * Parameters: title is a string
     *             author is a string
     * 
     * @throws an IllegalArgumentException if library does not contain the specified book.
     *         
     */
    public void setToRead(String title, String author) throws IllegalArgumentException {
        if (!library.contains(title, author)) {
            throw new IllegalArgumentException("Sorry no such book found");
        }
        library.updateReadBook(title, author);
    }

    /*
     * Method reads a book in the library if it exists, otherwise alerts the user
     * that no such book exists.
     * 
     * Parameters: title is a string
     *             author is a string
     *             rating is an integer between 1 and 5
     * 
     * @throws an IllegalArgumentException if library does not contain the specified book.
     *         
     */
    public void rate(String title, String author, int rating) throws IllegalArgumentException {
        if (!library.contains(title, author)) {
            throw new IllegalArgumentException("Sorry no such book found");
        }
        library.rateBook(title, author, rating);
    }

    /* 
     * Method gets sorted list of books from the library based on the input integer.
     * Books can be sorted alphabetically by title or author. Or they can be sorted
     * by read or unread. 
     * 
     * @pre sortMeth is an integer between 1 and 4. 
     * 
     * Parameters: sortMeth is an integer between 1 and 4
     * 
     * Returns: an ArrayList of book objects sorted by input method, or an 
     *          empty list if not books exist.
     */
    public ArrayList<Book> getBooks(int sortMeth) {
        if (sortMeth == 1) {
            return library.sortTitle();
        }
        if (sortMeth == 2) {
            return library.sortAuthor();
        }
        if (sortMeth == 3) {
            return library.sortRead();
        }
        return library.sortUnread();
    }

    /*
     * Method gets a random book from the library that is unread
     * 
     * Parameters: None
     * 
     * Returns: a book object
     */
    public Book suggestRead() {
        return library.suggestRead();
    }

}
