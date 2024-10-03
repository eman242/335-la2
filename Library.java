
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* 
 * class Library is the library model that is implemented using the Controller
 * based off the input commands to the MyLibrary View  
 * 
 * Authors:Eman Ayaz(emanayaz) and Elle Knapp(dmknapp2385) 
 * 
 * The model takes many precautions to maintain encapsulation such as the 
 * returns of any list of books involve creating a new duplicate list so clients searching for 
 * read/unread book cannot access the collections of books directly. Furthermore, this 
 * model also implements a Hashmap to store the ratings of books and this allows us to declare
 * the book object immutable and prevents the book object being accessed to update rating.
 * Additionally, all the attributes are declared private to prevent access outside the class.
 *
 */

public class Library {
	/* 
	 * This class creates Library objects that behave like a real library and
	 * makes use of various data structures such as ArrayLists (to keep track of read
	 * and unread books) and a Hashmap to store the rating of each book. This object responds
	 * to various commands such as searching for books by title, rating, finding specific books etc
	 * An instance of this class is created without intaking any parameters
	 * 
	 */
	
	private ArrayList<Book> libraryBooks;
	private ArrayList<Book> unReadBooks;
	private ArrayList<Book> readBooks;
	private HashMap<Book, Integer> bookRatings = new HashMap<Book, Integer>();

	public Library() {
		libraryBooks = new ArrayList<Book>();
		unReadBooks = new ArrayList<Book>();
		readBooks = new ArrayList<Book>();
	}

	/*
	 * Searches for books that have the desired title
	 * 
	 * Parameters: title that is a String
	 * 
	 * Returns: answer that is an Arraylist of books
	 */

	public ArrayList<Book> searchByTitle(String title) {
		ArrayList<Book> answer = new ArrayList<Book>(); //duplicate maintains encapsulation
		for (Book book : libraryBooks) {
			if (book.getTitle().toLowerCase().equals(title.toLowerCase())) { // not case sensitive
				answer.add(book);
			}
		}

		return answer;
	}

	/* Searches for books that have the desired author
	 * 
	 * Parameters: author that is a String
	 * 
	 * Returns: answer that is an Arraylist of books
	 */
	public ArrayList<Book> searchByAuthor(String author) {
		ArrayList<Book> answer = new ArrayList<Book>(); //duplicate maintains encapsulation

		for (Book book : libraryBooks) {
			if (book.getAuthor().toLowerCase().equals(author.toLowerCase())) { // not case sensitive
				answer.add(book);
			}
		}

		return answer;

	}

	// returns all books that match rating
	/*
	 * Searches for books that have the desired rating in the Hashmap bookRatings
	 * 
	 * Parameters: Rating that is a int
	 * 
	 * Returns: answer that is an ArrayList of books
	 */
	public ArrayList<Book> searchByRating(int rating) {
		ArrayList<Book> answer = new ArrayList<Book>(); //duplicate maintains encapsulation

		for (Book book : bookRatings.keySet()) {
			int value = bookRatings.get(book);// get value associated with key Book
			if (value == rating) {
				answer.add(book);
			}
		}
		return answer;
	}

	/*
	 * Find a random unread book by searching in unRead books ArrayList and
	 * shuffling to get different book each time
	 * 
	 * Parameters: None
	 * 
	 * Returns: Book object that is an unread book
	 */
	public Book suggestRead() {
		ArrayList<Book> temp = new ArrayList<>(unReadBooks);
		Collections.shuffle(temp);  
		return temp.get(0);

	}

	/*
	 * Adds a new book to the collection of books and updates libraryBooks and
	 * unReadBooks ArrayLists
	 * 
	 * Parameters: title that is String author that is String
	 * 
	 * Returns: None
	 */
	public void addBook(String title, String author) {
		Book b1 = new Book(title, author); // create book object
		libraryBooks.add(b1);
		unReadBooks.add(b1); // a new book is unread by default
	}

	/*
	 * Receives a newly read book and updates readBooks and unReadBooks ArrayLists
	 * 
	 * 
	 * Parameters: title that is String author that is String
	 * 
	 * Returns: None
	 */
	public void updateReadBook(String title, String author) {
		Book b1 = getBook(title, author); // gets the book Object
		readBooks.add(b1);
		unReadBooks.remove(b1);
	}

	/*
	 * Receives a book's rating and updates the Hashmap bookRating
	 * 
	 * Parameters: title that is String author that is String rating that is int
	 * 
	 * Returns: None
	 */
	public void rateBook(String title, String author, int rating) {
		Book b1 = getBook(title, author); // gets the book Object

		bookRatings.put(b1, rating); // we have 'rated' book by putting it in Hashmap

	}

	/*
	 * Helper method that returns a book object by searching for its title and
	 * author
	 * 
	 * Parameters: title that is String author that is String
	 * 
	 * Returns: a Book object
	 */
	private Book getBook(String title, String author) {
		for (Book book : libraryBooks) {

			if (book.getTitle().toLowerCase().equals(title.toLowerCase())
					&& book.getAuthor().toLowerCase().equals(author.toLowerCase())) {

				return book;
			}

		}
		return new Book("", ""); // allows code to run because contains() checks existence
								 // of book from controller

	}
	/*
	 * Checks if a book exists in the collection of books stored in libraryBooks
	 * ArrayList
	 * 
	 * Parameters: title that is String author that is String
	 * 
	 * Returns: true - if book found in collection false- if book not found in
	 * collection
	 */

	public boolean contains(String title, String author) {
		Book newBook = new Book(title, author);
		for (Book book : libraryBooks) {
			if (book.equals(newBook)) {
				return true;
			}

		}
		return false;
	}

	/*
	 * Returns copy of all LibraryBooks sorted in alphabetical order by title
	 * 
	 * Parameters: None
	 * 
	 * Returns: copy that is ArrayList of Books
	 */
	public ArrayList<Book> sortTitle() {
		ArrayList<Book> copy = new ArrayList<>(libraryBooks); //duplicate maintains encapsulation
		Collections.sort(copy, new Book.CompareByTitle());
		return copy;  

	}

	/*
	 * Returns copy of all LibraryBooks sorted in alphabetical order by author
	 * 
	 * Parameters: None
	 * 
	 * Returns: copy that is ArrayList of Books
	 */
	public ArrayList<Book> sortAuthor() {
		ArrayList<Book> copy = new ArrayList<>(libraryBooks); //duplicate maintains encapsulation
		Collections.sort(copy, new Book.CompareByAuthor());
		return copy; 
	}

	/*
	 * Returns list containing all the read books in library
	 * 
	 * Parameters: None
	 * 
	 * Returns: an ArrayList of Books
	 */
	public ArrayList<Book> sortRead() {
		return new ArrayList<>(readBooks); //duplicate maintains encapsulation
	}

	/*
	 * Returns list containing all the unread books in library
	 * 
	 * Parameters: None
	 * 
	 * Returns: an ArrayList of Books
	 */
	public ArrayList<Book> sortUnread() {
		return new ArrayList<>(unReadBooks); //duplicate maintains encapsulation
	}

}

  
  
   