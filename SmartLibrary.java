import java.util.Stack;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

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
        history.show();
    }

    //Implementing stack logic
    private class BorrowStack {
        private Stack<Book> stack = new Stack<>();

        public void push(Book b) {
            stack.push(b);
        }

        public void show() {
            if (stack.isEmpty()) {
                System.out.println("History is empty.");
            } else {
                System.out.println("--- Most Recent Activity ---");
                for (int i = stack.size() - 1; i >= 0; i--) {
                    Book b = stack.get(i);
                    System.out.println("[ISBN: " + b.getIsbn() + "] " + b.getTitle());
                }
            }
        }
    }
}