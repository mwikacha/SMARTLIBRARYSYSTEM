import java.util.Stack;

public class SmartLibrary implements LibraryADT {

    private BookBST catalogue = new BookBST();
    private BorrowHistory history = new BorrowHistory();

    public boolean isBookBorrowed(int isbn) {
        return history.isBorrowed(isbn);
    }

    @Override
    public void addBook(int isbn, String title, String author) {

        if (catalogue.search(isbn) != null) {
            System.out.println("Cannot Add: Book already exists!");
            return;
        }

        if (isBookBorrowed(isbn)) {
            System.out.println("Cannot Add: Book currently borrowed.");
            return;
        }

        catalogue.insert(isbn, title, author);
        System.out.println("Book added: " + title);
    }

    @Override
    public void searchBook(int isbn) {

        Book found = catalogue.search(isbn);

        if (found != null) {
            System.out.println("Book Found: " + found + " | Available");
            return;
        }

        Book borrowed = history.getBorrowedBook(isbn);

        if (borrowed != null) {
            System.out.println("Book Found: " + borrowed + " | Being Borrowed");
        } else {
            System.out.println("Book not found.");
        }
    }

    @Override
    public void borrowBook(int isbn, String studentName) {

        Book book = catalogue.search(isbn);

        if (book == null) {
            System.out.println("Book not available.");
            return;
        }

        book.setBorrowInfo(studentName);

        history.push(book);
        catalogue.remove(isbn);

        // ✅ FIXED: proper due date popup
        System.out.println("\n===== BORROW SUCCESS =====");
        System.out.println("Book     : " + book.getTitle());
        System.out.println("Borrower : " + studentName);
        System.out.println("Borrow Date : " + book.getBorrowDate());
        System.out.println("Due Date    : " + book.getDueDate());
        System.out.println("⚠ Return before due date to avoid fine!");
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
            System.out.println("Return failed.");
            return;
        }

        double fine = book.calculateFine(lateDays);

        System.out.println("\n--- Return Summary ---");
        System.out.println("Book: " + book.getTitle());

        if (lateDays > 0) {
            System.out.printf("Late by %d days. Fine: RM%.2f%n", lateDays, fine);
        } else {
            System.out.println("Returned on time.");
        }

        stack.remove(book);

        if (book.hasWaitlist()) {

            String next = book.getNextInWaitlist();

            System.out.println("Waitlist activated: " + next);

            book.setBorrowInfo(next);

            history.push(book);

            System.out.println("Transferred to: " + next);

        } else {

            System.out.println("Returned to catalogue.");

            catalogue.insert(
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor()
            );
        }
    }

    @Override
    public void displayLatestHistory() {
        history.viewHistory();
    }

    @Override
    public void viewWaitlist(int isbn) {

        Book book = history.getBorrowedBook(isbn);

        if (book == null || !book.hasWaitlist()) {
            System.out.println("No waitlist found.");
            return;
        }

        System.out.println("\nWaitlist for " + book.getTitle());

        int i = 1;
        for (String s : book.getWaitlist()) {
            System.out.println(i++ + ". " + s);
        }
    }

    public void checkDueDateReminder() {
        history.checkDueDateReminder();
    }

    public void joinWaitlist(int isbn, String studentName) {

        Book book = history.getBorrowedBook(isbn);

        if (book == null) {
            System.out.println("Book not currently borrowed.");
            return;
        }

        if (studentName.equalsIgnoreCase(book.getBorrower())) {
            System.out.println("You are already borrowing this book.");
            return;
        }

        book.addToWaitlist(studentName);

        System.out.println(studentName + " added to waitlist.");
    }
}