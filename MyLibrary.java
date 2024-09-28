
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Program: Large Assignment 2
 * Authors: Elle Knapp (dmknapp2385) and Eman Ayaz()
 * 
 */
class MyLibrary {

    private static final Scanner keyboard = new Scanner(System.in);

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

            if (input.equals("search")) {  //how does this get title, author or rating?
                ArrayList<Book> books = searchBook();
                if (!books.isEmpty()) {
                    for (Book book : books) {
                        ///does this handle empty list
                        System.out.println(book.getTitle() + " : " + book.getAuthor());
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
                        System.out.println(book.getTitle() + " : " + book.getAuthor());
                    }
                } else {
                    System.out.println("Sorry no books found in your library.");
                }

            } else if (input.equals("suggestread")) {
                Book suggestion = suggestRead();
                System.out.println(suggestion.getTitle() + " : " + suggestion.getAuthor());
            } else if (input.equals("addbooks")) {
                addBooks();
            } else {
                System.out.println("Did not regcognize the command. Please "
                        + "enter again");
            }

        }
    }

    /*
     * method checks if input string is 1 and exits program if true
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
     * @post- returns a list of Book objects or an empty list
     */
    private static ArrayList<Book> searchBook() {
        System.out.println("How would you like to search? By author, title"
                + " or rating?");
        String searchP;
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
     * Input validation to search by rating
     */
    private static ArrayList<Book> searchByRating() {
        int rating;
        while (true) {
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
                System.out.println("Sorry this is not a valid rating.");
            }
        }
        return control.search(rating);
    }

    /*
     * Method gets user input for title and author of a book and adds it to the 
     * library if it does not already exist
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
     * Method gets user input for title of a book and sets to read if found
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
     * updates the books rating to the users input
     */
    private static void rate() {
        System.out.println("What is the name of the book you woud like to rate?");
        String title = keyboard.nextLine().trim();
        checkExit(title);
        System.out.println("What is the name of the author?");
        String author = keyboard.nextLine().trim();
        checkExit(author);
        try {
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
     * @post- methods returns a list of Book objects or an empty list
     */
    private static ArrayList<Book> getBooks() {
        int sortMethod;
        while (true) {
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
     */
    private static Book suggestRead() {
        return control.suggestRead();
    }

    /*
     * Method adds multiple books to the library after reading them in from a
     * textfile. User input is the name of a file/path.
     * 
     * @pre- textfile must be in the format title;author
     * @throws- FileNotFoundException if file not found at input path
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
            String title = titleAuthor[0].trim();  //why not trimming
            String author = titleAuthor[1].trim();

            try {
                control.addBook(title, author);
                System.out.println("Book " + title + " by " + author + " added to your library");
            } catch (IllegalArgumentException e) { //what does this do?
                System.out.println(e.getMessage() + " Title: " + title + ", Author: "
                        + author);
            }
        }

    }

}
