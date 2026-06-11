import java.io.*;
import java.util.Stack;

public class SmartLibrary implements LibraryADT {

    private BookBST catalogue = new BookBST();
    private BorrowHistory history = new BorrowHistory();

    private static final String FILE_NAME = "books.csv";
    private static final String BORROW_FILE = "borrow_history.csv";

    public void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {

                if (first) { first = false; continue; }

                String[] data = line.split(",");

                if (data.length < 3) {
                    System.out.println("Skipping incomplete line: " + line);
                    continue;
                }

                int isbn = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];

                    // Failsafe for missing author text
                    if (author.isEmpty()) {
                        author = "Unknown Author";
                    }

                    catalogue.insert(isbn, title, author);
            }

            System.out.println("CSV loaded.");

        } catch (IOException e) {
            System.out.println("No CSV found.");
        }
    }

    public void saveToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            pw.println("ISBN,Title,Author");
            catalogue.inorderToCSV(catalogue.getRoot(), pw);

        } catch (IOException e) {
            System.out.println("Save error.");
        }
    }

    public void initSystem() {
        loadFromCSV();
        loadBorrowHistory();
    }

    private void saveBorrowRecord(Book book, String studentName) {

        File file = new File(BORROW_FILE);

        boolean fileExists = file.exists();

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                pw.println("ISBN,Title,Author,StudentName,BorrowDate,DueDate");
            }

            pw.println(
                    book.getIsbn() + "," +
                            book.getTitle() + "," +
                            book.getAuthor() + "," +
                            studentName + "," +
                            book.getBorrowDate() + "," +
                            book.getDueDate()
            );

        } catch (IOException e) {
            System.out.println("Error writing borrow CSV.");
        }
    }

    public void loadBorrowHistory() {

        File file = new File(BORROW_FILE);

        if (!file.exists()) return; 

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; } // Skip the header row

                String[] data = line.split(",");

                int isbn = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String author = data[2].trim();
                String studentName = data[3].trim();
                String borrowDate = data[4].trim(); 
                String dueDate = data[5].trim();

                Book borrowedBook = new Book(isbn, title, author); 
                
                borrowedBook.setBorrowInfo(studentName, borrowDate, dueDate);

                history.push(borrowedBook);
            }
            System.out.println("Borrow history loaded.");

        } catch (Exception e) {
            System.out.println("Error loading borrow history.");
        }
    }

    @Override
    public void addBook(int isbn, String title, String author) {

        if (catalogue.search(isbn) != null) {
            System.out.println("\nCannot Add: Book already exists!");
            return;
        }

        if (isBookBorrowed(isbn)) {
            System.out.println("\nCannot Add: Book already exists and currently borrowed.");
            return;
        }

        catalogue.insert(isbn, title, author);
        saveToCSV();

        System.out.println("\nBook added: \"" + title + "\" (ISBN: " + isbn + ")");
    }

    @Override
    public void deleteBook(int isbn) {

        if (isBookBorrowed(isbn)) {
            System.out.println("\nCannot Delete: Book with ISBN " + isbn + " is currently checked out!");
            System.out.println("[!] Must wait for the student to return it before permanent deletion.");
            return;
        }

        Book toDelete = catalogue.search(isbn);

        if (toDelete == null) {
            System.out.println("\nCannot Delete: Book with ISBN " + isbn + " does not exist in the catalogue.");
            return;
        }

        catalogue.remove(isbn);
        
        saveToCSV();
        
        System.out.println("\nDeleted: \"" + toDelete.getTitle() + "\" has been permanently removed from the catalogue.");
    }

    @Override
    public void searchBook(int isbn) {

        Book found = catalogue.search(isbn);

        if (found != null) {
            System.out.println("\nBook Found: " + found + " | [Status] : Available");
            return;
        }

        Book borrowed = history.getBorrowedBook(isbn);

        if (borrowed != null) {
            System.out.println("\nBook Found: " + borrowed + " | [Status] : Being Borrowed");
        } else {
            System.out.println("\nBook not found.");
        }
    }

    @Override
    public void borrowBook(int isbn, String studentName) {

        Book book = catalogue.search(isbn);

        if (book == null) {
            System.out.println("\nCannot Borrow: Book does not exist in the catalogue.");
            return;
        }

        book.setBorrowInfo(studentName);

        history.push(book);

        catalogue.remove(isbn);
        System.out.println("This book has been removed from the available catalogue.");

        saveBorrowRecord(book, studentName); // ⭐ NEW CSV LOG

        saveToCSV(); // inventory CSV

        System.out.println("\n--- Borrowed Book Details ---");
        System.out.println("Book     : " + book.getTitle());
        System.out.println("Borrower : " + studentName);
        System.out.println("Borrow Date : " + book.getBorrowDate());
        System.out.println("Due Date    : " + book.getDueDate());
        System.out.println("[!] Return before due date to avoid fine!");
    }

    @Override
    public void returnBook(int isbn, int lateDays) {

        Book book = null;
        Stack<Book> stack = history.getStack();

        for (Book b : stack) {
            if (b.getIsbn() == isbn) {
                book = b;
                break;
            }
        }

        if (book == null) {
            System.out.println("\nReturn failed: Book is currently not checked out.");
            return;
        }

        double fine = book.calculateFine(lateDays);

        System.out.println("\n--- Return Book Summary ---");
        System.out.println("Returned: \"" + book.getTitle() + "\"");

        if (lateDays > 0) {
            System.out.printf("Late by %d days. Fine: RM%.2f%n", lateDays, fine);
        } else {
            System.out.println("Returned on time. No fine charged.");
        }

        // Physically take the book out of the borrowed stacK
        stack.remove(book);

        if (book.hasWaitlist()) {

            String next = book.getNextInWaitlist();

            System.out.println("\n[!] Wailist Alert: " + next + " is on the waitlist of this book");

            book.setBorrowInfo(next);

            history.push(book);

            System.out.println("Automatically checking out book to the next student: " + next);

            saveBorrowRecord(book, next);

        } else {

            System.out.println("Returned to catalogue.");

            catalogue.insert(
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor()
            );
        }

        saveToCSV();
    }

    @Override
    public void displayLatestHistory() {
        history.viewHistory();
    }

    @Override
    public void viewWaitlist(int isbn) {

        Book book = history.getBorrowedBook(isbn);

        if (book == null || !book.hasWaitlist()) {
            System.out.println("\nNo waitlist.");
            return;
        }

        System.out.println("\n--- Waitlist Queue for \"" + book.getTitle() + "\" ---");

        int i = 1;
        for (String s : book.getWaitlist()) {
            System.out.println("[" + i++ + "] " + s);
        }
    }

    @Override
    public void joinWaitlist(int isbn, String studentName) {

        Book book = history.getBorrowedBook(isbn);

        if (book == null) {
            System.out.println("\nBook not currently borrowed.");
            return;
        }

        if (studentName.equalsIgnoreCase(book.getBorrower())) {
            System.out.println("--> You are already borrowing this book.");
            return;
        }

        book.addToWaitlist(studentName);

        System.out.println("--> " + studentName + " has been added to waitlist. (Queue Position: " + book.getWaitlist().size() + ")");
    }

    @Override
    public void checkDueDateReminder() {
        history.checkDueDateReminder();
    }

    public boolean isBookBorrowed(int isbn) {
        return history.isBorrowed(isbn);
    }
}