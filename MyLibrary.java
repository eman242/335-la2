
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Program: Large Assignment 2
 * Authors: Elle Knapp (dmknapp2385) and Eman Ayaz()
 * 
 * Class MyLibrary acts as the user interface for adding, searching and viewing
 * books stored in a virtual library. 
 * 
 * Encapsulation is not so much a concern with this class. This is the view
 * portion of the program and as such is accessed by the view via the command line.
 * No other program has access to these methods, however all class methods and
 * variables are private.
 */
class MyLibrary {

    private static Scanner keyboard = new Scanner(System.in);

    private static Controller control = new Controller();

    public static void main(String[] args) {
        System.out.println("Welcome to your library. Press 0 to exit anytime.");

        //Get user input for library command
        while (true) {
            System.out.println("What would you like to do? You can search,"
                    + " addBook, setToRead, rate, getBooks, suggestRead, or addBooks.");
            String input = keyboard.nextLine().trim();
            checkExit(input);
            input = input.toLowerCase();

            // if statments to handle user input
            if (input.equals("search")) {
                ArrayList<Book> books = searchBook();
                if (!books.isEmpty()) {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                } else {
                    System.out.println("Sorry no books found with these specifications");
                }
            } else if (input.equals("addbook")) {
                addBook();
            } else if (input.equals("settoread")) {
                setToRead();
            } else if (input.equals("rate")) {
                rate();
            } else if (input.equals("getbooks")) {
                ArrayList<Book> books = getBooks();
                if (!books.isEmpty()) {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                } else {
                    System.out.println("Sorry no books found in your library.");
                }

            } else if (input.equals("suggestread")) {
                Book suggestion = suggestRead();
                System.out.println(suggestion);
            } else if (input.equals("addbooks")) {
                addBooks();
            } else {
                System.out.println("Did not regcognize the command. Please "
                        + "enter again");
            }

        }
    }

    /*
     * Method checks if input string is 0 and exits program if true
     * 
     * Parameters: in -- a string
     */
    private static void checkExit(String in) {
        if (in.equals("0")) {
            System.out.println("Goodbye!");
            keyboard.close();
            System.exit(1);
        }
    }

    /*
     * Method gets user input for search type and name and gets all books 
     * corresponding to those inputs
     * 
     * Parameters: None
     * 
     * Returns: an ArrayList of book objects or an emtpy list
     */
    private static ArrayList<Book> searchBook() {
        System.out.println("How would you like to search? By author, title"
                + " or rating?");
        String searchP;
        //ensure input is title, author or rating
        while (true) {
            searchP = keyboard.nextLine().toLowerCase().trim();
            checkExit(searchP);
            if (!searchP.equals("author")
                    && !searchP.equals("title")
                    && !searchP.equals("rating")) {
                System.out.println("Did not recognize that input");
            } else {
                break;
            }
        }

        ArrayList<Book> books;
        if (searchP.equals("rating")) {
            books = searchByRating();
        } else {
            System.out.println("What " + searchP + " would you like to search for?");
            String toSearch = keyboard.nextLine().toLowerCase().trim();
            checkExit(toSearch);
            books = control.search(searchP, toSearch);
        }
        return books;
    }

    /*
     * Method validates rating input to search by 
     * 
     * Parameters: None
     * 
     * Returns: an ArrayList of book objects or an empty list
     */
    private static ArrayList<Book> searchByRating() {
        int rating;
        while (true) {
            // make sure input is an integer between 1 and 5
            try {
                System.out.println("What rating would you like to search for?(1-5)");
                String input = keyboard.nextLine().trim();
                checkExit(input);
                rating = Integer.parseInt(input);
                if (rating < 1 || rating > 5) {
                    System.out.println("Sorry this is not a valid rating.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Sorry this is not a valid rating."); //executes when not string
            }
        }
        return control.search(rating);
    }

    /*
     * Method gets user input for title and author of a book and adds it to the 
     * library if it does not already exist
     * 
     * Parameters: None
     */
    private static void addBook() {
        System.out.println("What is the name of the book you woud like to add?");
        String title = keyboard.nextLine().trim();
        checkExit(title);
        System.out.println("What is the name of the author?");
        String author = keyboard.nextLine().trim();
        checkExit(author);

        //add book if it does not already exist
        try {
            control.addBook(title, author);
            System.out.println("Book added to your library");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /* 
     * Method gets user input for title of a book and sets to read if found, 
     * alerts user if book not found
     * 
     * Parameters: None
     */
    private static void setToRead() {
        System.out.println("What is the name of the book you woud like to read?");
        String title = keyboard.nextLine().trim();
        checkExit(title);

        System.out.println("Who is the author?");
        String author = keyboard.nextLine().trim();
        checkExit(author);
        try {
            control.setToRead(title, author);
            System.out.println("Book read!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Method gets user input for title and author of a book and rating and 
     * updates the book's rating to the user's rating
     * 
     * Parameters: None
     */
    private static void rate() {
        System.out.println("What is the name of the book you woud like to rate?");
        String title = keyboard.nextLine().trim();
        checkExit(title);
        System.out.println("What is the name of the author?");
        String author = keyboard.nextLine().trim();
        checkExit(author);

        // try to rate book, alert user if book not found
        try {
            // make sure rating is a number between 1 and 5
            Integer rating;
            while (true) {
                try {
                    System.out.println("What rating would you like to give this"
                            + " book, enter a number between 1 and 5");
                    String input = keyboard.nextLine().trim();
                    checkExit(input);
                    rating = Integer.parseInt(input);
                    if (rating < 1 || rating > 5) {
                        System.out.println("Sorry this is not a valid rating.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Sorry this is not a valid rating.");
                }
            }
            control.rate(title, author, rating);
            System.out.println("Book rated!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Method retrieves all books from the library sorted in alphabetical order
     * by title, or author. Or returns read/unread books. User input determines 
     * how to sort
     * 
     * Parameters: None
     * 
     * Returns: an ArrayList of book objects or an empty list
     */
    private static ArrayList<Book> getBooks() {
        int sortMethod;
        while (true) {
            // make sure sort method is a number between 1 and 4
            try {
                System.out.println("How would you like to retrieve your books."
                        + " Enter 1 for by title, 2 by author, 3 for all read, or 4 for"
                        + " unread books");
                String input = keyboard.nextLine().trim();
                checkExit(input);
                sortMethod = Integer.parseInt(input);
                if (sortMethod < 1 || sortMethod > 4) {
                    System.out.println("Sorry this is not a valid input.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Sorry this is not a valid input.");
            }
        }
        return control.getBooks(sortMethod);
    }

    /*
     * Method returns a random unread book from the library
     * 
     * Parameters: None
     * 
     * Returns: a book object
     */
    private static Book suggestRead() {
        return control.suggestRead();
    }

    /*
     * Method adds multiple books to the library after reading them in from a
     * textfile. User input is the name of a file/path. File must end in .txt
     * 
     * @pre- textfile must be in the format title;author and end in .txt
     * @throws- FileNotFoundException if file not found at input path
     * 
     * Parameters: None
     */
    private static void addBooks() {
        System.out.println("Enter the name or path of the file containing"
                + " books you would like to add.");
        Scanner inFile = null;
        while (true) {
            try {
                String fileName = keyboard.nextLine().trim();
                checkExit(fileName);
                inFile = new Scanner(new File(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Could not find file with this name");
            }
        }
        while (inFile.hasNextLine()) {
            String book = inFile.nextLine();
            String[] titleAuthor = book.split(";");
            String title = titleAuthor[0].trim();
            String author = titleAuthor[1].trim();

            // add each book in file to library; alert user if the book already
            //exists and do not add it to the library
            try {
                control.addBook(title, author);
                System.out.println("Book " + title + " by " + author + " added to your library");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Title: " + title + ", Author: "
                        + author);
            }
        }

    }

}
