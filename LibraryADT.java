public interface LibraryADT {

    void addBook(int isbn, String title, String author);
    void deleteBook(int isbn);
    void searchBook(int isbn);

    void borrowBook(int isbn, String studentName);

    void displayLatestHistory();

    void returnBook(int isbn, int lateDays);

    void viewWaitlist(int isbn);
    void joinWaitlist(int isbn, String studentName);
}