import java.util.*;
public class BorrowHistory {
        private Stack<Book> borrowHistory = new Stack<>();

        // Called when student borrows a book
        public void push(Book book) {
            if (book == null) {
                System.out.println("Error: Cannot add a null record to history.");
                return;
            }
            borrowHistory.push(book);
            System.out.println("\"" + book.title + "\" has been added to borrow history.");
        }

        // Display all borrowed books, newest first
        public void viewHistory() {
            if (borrowHistory.isEmpty()) {
                System.out.println("No borrowing history found.");
            } else {
                System.out.println("\n--- Borrowing History ---");
                //LIFO order
                for (int i = borrowHistory.size() - 1; i >= 0; i--) {
                    Book b = borrowHistory.get(i);
                    System.out.printf("[%d] ISBN: %-5d | Title: %-15s | Author: %-15s%n",
                            (borrowHistory.size() - i), b.isbn, b.title, b.author);
                }
            }
        }
    }

