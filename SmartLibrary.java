import java.util.Stack;
public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowHistory history = new BorrowHistory();

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println("Book added to catalogue.");
    }

    @Override
    public void searchBook(int isbn) {
        Book found = catalogue.search(isbn);
        if (found != null) {
            System.out.println("Found: " + found.getTitle() + " by " + found.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book toBorrow = catalogue.search(isbn);
        if (toBorrow != null) {
            history.push(toBorrow);
            System.out.println("Successfully borrowed: " + toBorrow.getTitle());
        } else {
            System.out.println("Cannot borrow: Book does not exist.");
        }
    }

    @Override
    public void displayLatestHistory() {
        history.viewHistory();
    }


}