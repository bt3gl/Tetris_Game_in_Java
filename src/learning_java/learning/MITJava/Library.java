// we build a class that represent each library and manage a
// collection of books


import java.util.ArrayList;


public class Library{

    String address;

    ArrayList<Book> catalog = new ArrayList<Book>();
    private int bookCount = 0;

    public Library(String nameLib){
      this.address = nameLib;
    }

    public static void printOpeningHours(){
      System.out.println("The hours are 9-5.");
    }

    public void printAddress(){
      System.out.println(this.address);
    }

    public void addBook(Book bookTitle){
      catalog.add(bookTitle);
      bookCount++;
    }

    public void borrowBook(String bookTitle) {

        // find the book in the catalog
        if (bookCount > 0) {
            for (int i = 0; i < bookCount; i++) {
                //search through the whole catalog with book.getTitle()
                if (catalog.get(i).getTitle().equals(bookTitle)) {

                    // if the book is in stock
                    if (!catalog.get(i).isBorrowed()) {
                        // mark as checked out
                        catalog.get(i).borrowed();
                        System.out.println("Successfully borrowed " + catalog.get(i).getTitle());
                        // Book is already checked out
                    } else {
                        System.out.println("Sorry, this book is already borrowed.");
                    }
                }
            }
            // Return an error if you try to borrow a book from an empty library
        } else {
            System.out.println("Sorry, this book is not in our catalog.");
        }
    }

    public void printAvailableBooks() {

        // Check to see if there are any books in the library
        if (bookCount > 0) {

        // For all the books in the library
            for (int i = 0; i < bookCount; i++) {

        // Check to see if the book is checked out
        // isBorrowed returns false if the book is in stock
                if (!catalog.get(i).isBorrowed()){
                   System.out.println(catalog.get(i).getTitle());
                } else {
                    continue;
                }
            }
        } else {
            System.out.println("No Book in Catalog");
        }
    }


      public void returnBook(String bookTitle) {

        // find the book in the catalog
        if (bookCount > 0) {
            for (int i = 0; i < bookCount; i++) {
                //search through the whole catalog with book.getTitle()

                if (catalog.get(i).getTitle().equals(bookTitle)) {

                    // if the book is checked out?
                    if (catalog.get(i).isBorrowed()) {
                        // mark as returned
                        catalog.get(i).returned();
                        System.out.println("You Successfully returned " + catalog.get(i).getTitle());
                        // Book is already returned
                    } else {
                        System.out.println("Sorry, this book is already returned."
                                + "this isnt our book.");
                    }
                }
            }
            // Return an error if you try to borrow a book from an empty library
        } else {
            System.out.println("Sorry, this book is not in our catalog.");
        }

    }



    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

         // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();


        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();

    }
}