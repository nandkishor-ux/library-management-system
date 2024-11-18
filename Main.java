package projectjava;

import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }
}

// Abstract User Class
abstract class User {
    private String name;
    private int borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = 0;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook() {
        borrowedBooks++;
    }

    public void returnBook() {
        borrowedBooks--;
    }

    public abstract int getMaxBooks();
}

// Student Class (Inherits from User)
class Student extends User {
    private static final int MAX_BOOKS = 3;

    public Student(String name) {
        super(name);
    }

    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
}

// Teacher Class (Inherits from User)
class Teacher extends User {
    private static final int MAX_BOOKS = 5;

    public Teacher(String name) {
        super(name);
    }

    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
}

// Library Class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void lendBook(User user, String title) {
        Book book = findBook(title);
        if (book != null && book.isAvailable()) {
            if (user.getBorrowedBooks() < user.getMaxBooks()) {
                book.borrowBook();
                user.borrowBook();
                System.out.println(user.getName() + " borrowed " + book.getTitle());
            } else {
                System.out.println(user.getName() + " cannot borrow more than " + user.getMaxBooks() + " books.");
            }
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(User user, String title) {
        Book book = findBook(title);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            user.returnBook();
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("Book was not borrowed.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add some books to the library
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        // Create some users
        User student1 = new Student("Alice");
        User teacher1 = new Teacher("Mr. Smith");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter your name (Alice or Mr. Smith): ");
                    String name = scanner.nextLine();
                    User user = name.equalsIgnoreCase("Alice") ? student1 : teacher1;
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.lendBook(user, borrowTitle);
                    break;
                case 3:
                    System.out.print("Enter your name (Alice or Mr. Smith): ");
                    name = scanner.nextLine();
                    user = name.equalsIgnoreCase("Alice") ? student1 : teacher1;
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(user, returnTitle);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
