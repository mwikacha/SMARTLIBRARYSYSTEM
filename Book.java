public class Book {
    private int isbn;
    private String title;
    private String author;
    private double fine;
    // BST Pointers
    Book left, right;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }

    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getFine() {return fine;}

    public void setIsbn(int isbn) { this.isbn = isbn; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Title: " + title + " | Author: " + author;
    }

    public double calculateFine(int lateDays) {
        if (lateDays <= 0) {
            fine = 0;
        } else {
            fine = lateDays * 1.00; // RM1 per late day
        }
        return fine;
    }
}