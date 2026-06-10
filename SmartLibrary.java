import java.io.*;
import java.util.Stack;

public class SmartLibrary implements LibraryADT {

    private BookBST catalogue = new BookBST();
    private BorrowHistory history = new BorrowHistory();

    private static final String FILE_NAME = "books.csv";
    private static final String BORROW_FILE = "borrow_history.csv";

    public void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {

                if (first) { first = false; continue; }

                String[] data = line.split(",");

                if (data.length < 3) {
                    System.out.println("Skipping incomplete line: " + line);
                    continue;
                }

                int isbn = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];

                catalogue.insert(isbn, title, author);
            }

            System.out.println("CSV loaded.");

        } catch (IOException e) {
            System.out.println("No CSV found.");
        }
    }

    public void saveToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            pw.println("isbn,title,author");
            catalogue.inorderToCSV(catalogue.getRoot(), pw);

        } catch (IOException e) {
            System.out.println("Save error.");
        }
    }

    public void initSystem() {
        loadFromCSV();
    }

    private void saveBorrowRecord(Book book, String studentName) {

        File file = new File(BORROW_FILE);

        boolean fileExists = file.exists();

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                pw.println("ISBN,Title,Author,StudentName,BorrowDate,DueDate");
            }

            pw.println(
                    book.getIsbn() + "," +
                            book.getTitle() + "," +
                            book.getAuthor() + "," +
                            studentName + "," +
                            book.getBorrowDate() + "," +
                            book.getDueDate()
            );

        } catch (IOException e) {
            System.out.println("Error writing borrow CSV.");
        }
    }

    @Override
    public void addBook(int isbn, String title, String author) {

        if (catalogue.search(isbn) != null) {
            System.out.println("Book exists!");
            return;
        }

        catalogue.insert(isbn, title, author);
        saveToCSV();

        System.out.println("Added: " + title);
    }

    @Override
    public void searchBook(int isbn) {

        Book b = catalogue.search(isbn);

        if (b != null) {
            System.out.println(b);
        } else {
            System.out.println("Borrowed or not found.");
        }
    }

    @Override
    public void borrowBook(int isbn, String studentName) {

        Book book = catalogue.search(isbn);

        if (book == null) {
            System.out.println("Not available.");
            return;
        }

        book.setBorrowInfo(studentName);

        history.push(book);

        catalogue.remove(isbn);

        saveBorrowRecord(book, studentName); // ⭐ NEW CSV LOG

        saveToCSV(); // inventory CSV

        System.out.println("Borrowed: " + book.getTitle());
        System.out.println("Due: " + book.getDueDate());
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

        stack.remove(book);
        catalogue.insert(book.getIsbn(), book.getTitle(), book.getAuthor());

        saveToCSV();

        System.out.println("Returned: " + book.getTitle());
        System.out.println("Fine: RM" + fine);
    }

    @Override
    public void displayLatestHistory() {
        history.viewHistory();
    }

    @Override
    public void viewWaitlist(int isbn) {

        Book book = catalogue.search(isbn);

        if (book == null || !book.hasWaitlist()) {
            System.out.println("No waitlist.");
            return;
        }

        int i = 1;
        for (String s : book.getWaitlist()) {
            System.out.println(i++ + ". " + s);
        }
    }

    @Override
    public void joinWaitlist(int isbn, String studentName) {

        Book book = catalogue.search(isbn);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        book.addToWaitlist(studentName);
        System.out.println("Added to waitlist.");
    }

    @Override
    public void checkDueDateReminder() {
        history.checkDueDateReminder();
    }

    public boolean isBookBorrowed(int isbn) {
        return history.isBorrowed(isbn);
    }
}