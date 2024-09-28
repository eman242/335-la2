
import java.util.ArrayList;

/* 
 * class Controller represents a controller between the MyLibrary view and the
 * Library model. 
 * 
 * Authors: Elle Knapp(dmknapp2385) and Eman Ayaz(emanayaz)
 * 
 * Encapsulation is maintained in this class as the view does not have direct 
 * access to the library field. All manipulations of the library object are done
 * through methods. Additionally, any list returned via these methods are copies
 * of the origina data stored in library. 
 */

public class Controller {
    private Library library;

    public Controller() {
        this.library = new Library();

    }

    //overloading
    public ArrayList<Book> search(String searchP, String toSearch) {
        if (searchP.equals("title")) {
            return library.searchByTitle(toSearch);
        }
        return library.searchByAuthor(toSearch);
    }
    //overloading
    public ArrayList<Book> search(int rating) {
        return library.searchByRating(rating);

    }

    public void addBook(String title, String author) throws IllegalArgumentException {
        if (library.contains(title, author)) {
            throw new IllegalArgumentException("This book already exists.");
        }
        if (title.equals("") || author.equals("")) {
            throw new IllegalArgumentException("Must have both title and author.");
        }
        library.addBook(title, author);

    }

    public void setToRead(String title, String author) throws IllegalArgumentException {
        if (!library.contains(title, author)) {
            throw new IllegalArgumentException("Sorry no such book found");
        }
        //library.getBook(title, author).setToRead(); //poses problem as I can't update readBook list
         library.updateReadBook(title, author);  //choose to update attribute through library model
    }

    public void rate(String title, String author, int rating) throws IllegalArgumentException {
        if (!library.contains(title, author)) {
            throw new IllegalArgumentException("Sorry no such book found");
        }
        //library.getBook(title, author).rate(rating); //setting the rate is passed to Model to accomodate Hashmap ratings
        library.rateBook(title, author,rating);    //choose to update attribute through library model 
    }

    /*
     * @pre sortMeth is an integer between 1 and 4. 
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

    public Book suggestRead() {
        return library.suggestRead();
    }
    

}
