
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Library {

    private ArrayList<Book> libraryBooks;
    private ArrayList<Book> unReadBooks;
    private ArrayList<Book> readBooks;
    private HashMap<Book, Integer> bookRatings = new HashMap<Book, Integer>();

    public Library() {
        libraryBooks = new ArrayList<Book>();
        unReadBooks = new ArrayList<Book>();
        readBooks = new ArrayList<Book>();
    }
    // this is a merge conflit
    //returns the book that has title
    public ArrayList<Book> searchByTitle(String title) {
    	ArrayList<Book> answer = new ArrayList<Book>();
    	
    	for (Book book : libraryBooks) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) { //not case sensitive
                answer.add(book);
                break; //only one book can match title
            }
        }
    	
        return answer;
    }
    //returns all books that match author
    public ArrayList<Book> searchByAuthor(String author) {
       ArrayList<Book> answer = new ArrayList<Book>();
    	
    	for (Book book : libraryBooks) {
            if (book.getAuthor().toLowerCase().equals(author.toLowerCase())) { //not case sensitive
                answer.add(book);
            }
        }
    	
        return answer;

    }
    
//OLD method that does not deal use hashmap   
//returns all books that match rating
//    public ArrayList<Book> searchByRating(int rating) {
//    	 ArrayList<Book> answer = new ArrayList<Book>();
//     	
//     	for (Book book : libraryBooks) {
//             if (book.getRating()==rating) { //not case sensitive
//                 answer.add(book);
//             }
//         }
//     	
//         return answer;
//    }
    
  //returns all books that match rating
  public ArrayList<Book> searchByRating(int rating) {
  	 ArrayList<Book> answer = new ArrayList<Book>();
   	
   	for (Book book : bookRatings.keySet()) {
   		   int value= bookRatings.get(book);//get value associated with key Book
           if (value==rating) { 
               answer.add(book);
               //System.out.println(book.getTitle()+ "WAS ADDED");
           }
       }
   	 //System.out.println(answer.get(0).getTitle()+ " book onject shows");
       return answer;
  }

    //get random unread book by shuffling
    public Book suggestRead() {
    	ArrayList<Book> temp = new ArrayList<>(unReadBooks);
    	Collections.shuffle(temp);
    	return temp.get(0);
    	
    }
    
    //Eman
    //add a book by creating new book oobject from title and author
    // and updating lists as necessary
    public void addBook(String title, String author) {
    	Book b1= new Book(title,author);
    	libraryBooks.add(b1);  //because a new book is unread by default
    	unReadBooks.add(b1);
    	//System.out.println("Size of library is"+ libraryBooks.size());

    }
    //Eman
    //updates newly read book and adds to readBooks list
    public void updateReadBook(String title, String author) {
    	Book b1= getBook(title,author); //gets the book Object
    	b1.setToRead(); //updates attribute
    	libraryBooks.add(b1);  //adds to Library & readBooks collection
    	readBooks.add(b1);

    }
    
  //Eman
    //updates newly read book and adds to readBooks list
    public void rateBook(String title, String author, int rating) {
    	Book b1= getBook(title,author); //gets the book Object
    	//System.out.println("FROMM" + b1.getTitle());
    	
    		bookRatings.put(b1, rating);  //we have 'rated' book by putting it in Hashmap
    		//System.out.println("FROMM22") ;
    		//if (!bookRatings.containsKey(b1))  thought of using if else not in hashmap
    		                      //             but pointless considering hashMap overwrites old value 	
    	

    }
    
    
    //Eman & Elle
    //finds a book object by searching for name and title
    // and returns it to be changed
    private Book getBook(String title, String author) {
    	for (Book book : libraryBooks) {
    		//System.out.println("for loop ran");
            if (book.getTitle().toLowerCase().equals(title.toLowerCase()) && book.getAuthor().toLowerCase().equals(author.toLowerCase())) {
            	//System.out.println("GET BOOK WORKED!!!");
                return book;
            }

        }
    	//System.out.println("returned Null Book");
    	return new Book("",""); //to allow code to run because contains checks existence of book from controller
        
    }
    
// Elle's (kind of confused on pupose of this as it return a new book object)    
//  //retuns a singular book to be changed
 
//  public Book getBook(String title, String author) {
//      return new Book(title, author);
//  }

    
    /*
     * check if book in books
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
     */
    public ArrayList<Book> sortTitle() {
        ArrayList<Book> copy = new ArrayList<>(libraryBooks);
        Collections.sort(copy, new Book.CompareByTitle());
        return copy;

    }

    /*
     * Returns copy of all LibraryBooks sorted in alphabetical order by author
     */
    public ArrayList<Book> sortAuthor() {
        ArrayList<Book> copy = new ArrayList<>(libraryBooks);
        Collections.sort(copy, new Book.CompareByAuthor());
        return copy;
    }

    /*
     * Returns list containing read libraryBooks
     */
    public ArrayList<Book> sortRead() {
        ArrayList<Book> copy = new ArrayList<>(readBooks);
        for (Book book : libraryBooks) {
            if (book.getRead()) {  //eman using getRead because read should be private
                copy.add(book);
            }
        }
        Collections.sort(copy,new Book.CompareByAuthor());  //eman adding a sort here and sorting by author
        return copy;  
    }

    /*
     * Returns a list containing unread libraryBooks
     */
    public ArrayList<Book> sortUnread() {
        ArrayList<Book> copy = new ArrayList<>(unReadBooks);
        for (Book book : libraryBooks) {
            if (!book.getRead()) {
                copy.add(book);
            }
        }
        Collections.sort(copy,new Book.CompareByAuthor());  //eman adding a sort here and sorting by author
        return copy;
    }

}
