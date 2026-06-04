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
            System.out.println("\"" + book.getTitle() + "\" has been added to borrow history.");
        }

        // Display all borrowed books, newest first
        public void viewHistory() {
            if (borrowHistory.isEmpty()) {
                System.out.println("No borrowing history found.");
            } else {
                System.out.println("\n--- Borrowing History ---");
                // LIFO order
                for (int i = borrowHistory.size() - 1; i >= 0; i--) {
                    Book b = borrowHistory.get(i);
                    System.out.printf("[%d] ISBN: %-5d | Title: %-15s | Author: %-15s%n",
                            (borrowHistory.size() - i), b.getIsbn(), b.getTitle(), b.getAuthor());
                }
            }
        }

        public boolean isBorrowed(int isbn) {
            for (Book b : borrowHistory) {
                if (b.getIsbn() == isbn) {
                    return true;
                }
            }
            return false;
        }

        public Book removeReturnedBook(int isbn) {
            for (int i = 0; i < borrowHistory.size(); i++) {
                if (borrowHistory.get(i).getIsbn() == isbn) {
                    return borrowHistory.remove(i); // Remove from Borrow History
                }
            }
            return null; 
        }

        public Book getBorrowedBook(int isbn) {
            for (Book b : borrowHistory) {
                if (b.getIsbn() == isbn) {
                    return b; // Found it! Return a copy of the data.
                }
            }
            return null;
        }
    
         // Getter so SmartLibrary can search and remove from the stack
        public Stack<Book> getStack() {
             return borrowHistory;
        }
    }

