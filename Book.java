import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;

public class Book {
    private int isbn;
    private String title;
    private String author;

    private double fine;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String borrower;

    // BST pointers
    Book left, right;

    // waitlist belongs to BOOK (correct design)
    private Queue<String> waitlist = new LinkedList<>();

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    // getters
    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public double getFine() { return fine; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getBorrower() { return borrower; }

    // setters
    public void setIsbn(int isbn) { this.isbn = isbn; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    public void setBorrowInfo(String borrower) {
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
    }

    // waitlist
    public void addToWaitlist(String studentName) {
        if (!waitlist.contains(studentName)) {
            waitlist.add(studentName);
        } else {
            System.out.println(studentName + " already in waitlist.");
        }
    }

    public String getNextInWaitlist() {
        return waitlist.poll();
    }

    public boolean hasWaitlist() {
        return !waitlist.isEmpty();
    }

    public Queue<String> getWaitlist() {
        return waitlist;
    }

    public double calculateFine(int lateDays) {
        if (lateDays <= 0) {
            fine = 0;
        } else {
            fine = lateDays * 1.0;
        }
        return fine;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Title: " + title + " | Author: " + author;
    }
}