public class SmartLibrary implements LibraryADT {

    // The official BST catalogue stores available books
    private BookBST catalogue = new BookBST();

    // The BorrowHistory stack records currently checked out books
    private BorrowHistory history = new BorrowHistory();


    public boolean isBookBorrowed(int isbn) {
        return history.isBorrowed(isbn);
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        // Check the catalogue
        Book toAdd = catalogue.search(isbn);
        
        if (toAdd != null) {
            // Book exist in the catalogue
            System.out.println("Cannot Add: Book with ISBN " + isbn + " already exists in the catalogue!");
            
        } else if (isBookBorrowed(isbn)) {
            // THE INTERCEPT: It is not on the shelf, but currently being borrowed
            System.out.println("Cannot Add: Book with ISBN " + isbn + " already exists (Currently being borrowed).");
            
        } else {
            // It is not on the shelf && not borrowed
            catalogue.insert(isbn, title, author);
            System.out.println("Book added: \"" + title + "\" (ISBN: " + isbn + ")");
        }
    }


    @Override
    public void searchBook(int isbn) {
        // Use the teammate's BST search method
        Book found = catalogue.search(isbn);

        if (found != null) {
            // Book exists in the catalogue
            System.out.println("Book Found: " + found + " | [STATUS]: Available"); // calls Book.toString()
        } else {
            Book borrowedBook = history.getBorrowedBook(isbn);

            if(isBookBorrowed(isbn)) {
                System.out.println("Book Found: " + borrowedBook + " | [STATUS]: Being Borrowed"); // calls Book.toString()
            } else {
                // Book not in BST
            System.out.println("Book not found. ISBN " + isbn + " does not exist in the catalogue.");
            }
        }
    }


    @Override
    public void borrowBook(int isbn, String studentName) {
        // Step 1: Search the available catalogue
        Book toBorrow = catalogue.search(isbn);

        if (toBorrow != null) {
            // CASE A: Book is available in the catalogue!

            // Push to the history stack
            toBorrow.setBorrowInfo(studentName);
            history.push(toBorrow);

            // Use the teammate's built-in remove method to clear it from the active catalogue!
            catalogue.remove(isbn);

            System.out.println("Successfully borrowed: \"" + toBorrow.getTitle() + "\" by " + studentName);
            System.out.println("Borrow Date: " + toBorrow.getBorrowDate());
            System.out.println("Due Date   : " + toBorrow.getDueDate());
            System.out.println("This book has been removed from the available catalogue.");
        } else {
            // CASE B: Book is NOT in the catalogue. Check if it's currently borrowed.
            Book borrowedCopy = null;
            for (int i = 0; i < history.getStack().size(); i++) {
                if (history.getStack().get(i).getIsbn() == isbn) {
                    borrowedCopy = history.getStack().get(i);
                    break;
                }
            }

            if (borrowedCopy != null) {

                // Prevent current borrower from joining their own waitlist
                if (studentName.equalsIgnoreCase(borrowedCopy.getBorrower())) {
                    System.out.println("You are already borrowing this book.");
                    return;
                }

                borrowedCopy.addToWaitlist(studentName);

                System.out.println(
                        "--> Success: "
                                + studentName
                                + " has been added to the WAITLIST (Queue position: "
                                + borrowedCopy.getWaitlist().size()
                                + ")."
                );
            }
        }
    }

    @Override
    public void returnBook(int isbn, int lateDays) {
        Book toReturn = null;
        for (int i = 0; i < history.getStack().size(); i++) {
            if (history.getStack().get(i).getIsbn() == isbn) {
                toReturn = history.getStack().get(i);
                break;
            }
        }

        if (toReturn == null) {
            System.out.println("Return failed: ISBN " + isbn + " was not found in borrow history.");
            return;
        }

        // Calculate fines
        double fine = toReturn.calculateFine(lateDays);
        System.out.println("\n--- Return Book Summary ---");
        System.out.println("Returned: \"" + toReturn.getTitle() + "\"");
        if (lateDays > 0) System.out.printf("Late by %d day(s). Fine paid: RM%.2f%n", lateDays, fine);
        else System.out.println("Returned on time. No fine charged.");

        // Remove from current active checkout logs
        history.getStack().remove(toReturn);

        // Check the waitlist queue
        if (toReturn.hasWaitlist()) {
            String nextStudent = toReturn.getNextInWaitlist();

            System.out.println("[!] Waitlist Alert! " + nextStudent);

            // update borrower properly
            toReturn.setBorrowInfo(nextStudent);

            // no need push again (already in history, just update record)
            System.out.println("Book transferred to next student: " + nextStudent);

        } else {

            toReturn.left = null;
            toReturn.right = null;

            catalogue.insert(
                    toReturn.getIsbn(),
                    toReturn.getTitle(),
                    toReturn.getAuthor()
            );

            System.out.println("Book returned to catalogue.");
        }
    }

    @Override
    public void displayLatestHistory() {
        history.viewHistory();
    }

    @Override
    public void viewWaitlist(int isbn) {
        Book targetBook = null;
        for (int i = 0; i < history.getStack().size(); i++) {
            if (history.getStack().get(i).getIsbn() == isbn) {
                targetBook = history.getStack().get(i);
                break;
            }
        }

        if (targetBook != null && targetBook.hasWaitlist()) {
            System.out.println("\n--- Waitlist Queue for \"" + targetBook.getTitle() + "\" ---");
            int rank = 1;
            for (String student : targetBook.getWaitlist()) {
                System.out.println("[" + rank + "] " + student);
                rank++;
            }
        } else {
            System.out.println("No active waitlist queue found for ISBN " + isbn);
        }
    }
    public void checkDueDateReminder() {
        history.checkDueDateReminder();
    }

    public void joinWaitlist(int isbn, String studentName) {

        Book b = history.getBorrowedBook(isbn);

        if (b == null) {
            System.out.println("Cannot join waitlist: Book is not currently borrowed.");
            return;
        }

        if (studentName.equalsIgnoreCase(b.getBorrower())) {
            System.out.println("You are already borrowing this book.");
            return;
        }

        b.addToWaitlist(studentName);

        System.out.println(studentName + " joined waitlist for: " + b.getTitle());
    }
}