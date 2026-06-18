import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//track and manage a student’s book borrowing records using a Stack (LIFO structure)
public class BorrowHistory {
    private Stack<Book> borrowHistory = new Stack<>();

    // Called when student borrows a book
    public void push(Book book) {
        if (book == null) {
            System.out.println("\nError: Cannot add a null record to history.");
            return;
        }
        borrowHistory.push(book);
        System.out.println("\n\"" + book.getTitle() + "\" has been added to borrow history.");
    }

    // Display all borrowed books, newest first
    public void viewHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("\nNo borrowing history found.");
            return;
        }

        System.out.println("\n--- Borrow History ---");

        // LIFO order
        for (int i = borrowHistory.size() - 1; i >= 0; i--) {
            Book b = borrowHistory.get(i);
            System.out.printf("[%d] %d | %s | %s%n",
                    borrowHistory.size() - i,
                    b.getIsbn(),
                    b.getTitle(),
                    b.getAuthor());
        }
    }

    public boolean isBorrowed(int isbn) {
        for (Book b : borrowHistory) {
            if (b.getIsbn() == isbn) return true;
        }
        return false;
    }

    public Book getBorrowedBook(int isbn) {
        for (Book b : borrowHistory) {
            if (b.getIsbn() == isbn) return b;
        }
        return null;
    }

    // Getter so SmartLibrary can search and remove from the stack
    public Stack<Book> getStack() {
        return borrowHistory;
    }

    public void checkDueDateReminder() {

        LocalDate today = LocalDate.now();
        boolean found = false;

        System.out.println("\n--- Due Date Reminder ---");

        for (Book b : borrowHistory) {

            if (b.getDueDate() == null) continue;

            long daysLeft = ChronoUnit.DAYS.between(today, b.getDueDate());

            if (daysLeft <= 3) {
                found = true;
                System.out.println("Student: " + b.getBorrower());
                System.out.println("Book   : " + b.getTitle());

                if (daysLeft >= 0) {
                    System.out.println("Due in : " + daysLeft + " days");
                } else {
                    System.out.println("OVERDUE: " + Math.abs(daysLeft) + " days");
                }
            }
        }

        if (!found) {
            System.out.println("\nNo due date reminders.");
        }
    }
}