public interface LibraryADT {

    void addBook(int isbn, String title, String author);
    void searchBook(int isbn);
    
    // Requires student name to track who borrows/waits
    void borrowBook(int isbn, String studentName);
    
    void displayLatestHistory();
    void returnBook(int isbn, int lateDays);
    
    // Contract method to inspect a book's queue
    void viewWaitlist(int isbn);
}