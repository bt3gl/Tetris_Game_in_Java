public class Book {

    String title;
    boolean borrowed;

    // Creates a new Book
    public Book(String bookTitle) {
        this.title = bookTitle;
        this.borrowed = false;
    }

    // Marks the book as rented
    public void borrowed() {
        this.borrowed = true;
    }

    // Marks the book as not rented
    public void returned() {
        this.borrowed = false;
    }

    // Returns true if the book is rented, false otherwise
    public boolean isBorrowed() {
        return this.borrowed;
    }

    // Returns the title of the book
    public String getTitle() {
        return  this.title;
    }

    public static void main(String[] arguments) {
        // Small test of the Book class
        Book example = new Book("The Da Vinci Code");
        System.out.println("Title: " + example.title);

        System.out.println("Borrowed? " + example.isBorrowed());
        example.borrowed();
        System.out.println("Borrowed? " +example.isBorrowed());

        example.returned();
        System.out.println("Borrowed? " + example.isBorrowed());
    }
}