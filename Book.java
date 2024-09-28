
import java.util.Comparator;

public class Book {
    
	
	//why not declared as private?, Eman declaring as private
    private boolean read;
    private String author;
    String title;
    //int rating;  hashmap

    public Book(String title, String author) {
    	//books initialized to be unread
        this.title = title;
        this.author = author;
        //this.rating = 0;  hashmap
        this.read = false;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }
    
//Eman: temporary till we add hashmap functionality
//    public int getRating() {
//        return this.rating;
//    }

    public boolean getRead() {
        return this.read;
    }

    public void setToRead() {
        if (!read) {
            this.read = true;
        }
    }

    /*
     * @pre Rating is an integer between 1 and 5
     */
//    public void rate(int rating) {  Hashmap
//        this.rating = rating;
//    }

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
        return this.title.equals(other.title) && this.author.equals(other.author);
    }
}
