import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowHistory {
    private Stack<Book> borrowHistory = new Stack<>();

    public void push(Book book) {
        if (book == null) return;
        borrowHistory.push(book);
        System.out.println("\"" + book.getTitle() + "\" added to history.");
    }

    public void viewHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("No history.");
            return;
        }

        System.out.println("\n--- Borrow History ---");

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

    public Stack<Book> getStack() {
        return borrowHistory;
    }

    public void checkDueDateReminder() {

        LocalDate today = LocalDate.now();
        boolean found = false;

        System.out.println("\n===== Due Reminder =====");

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
            System.out.println("No reminders.");
        }
    }
}