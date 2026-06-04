import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Wishlist {
    // The Map holds the ISBN as the key, and a Queue of student names as the value
    private Map<Integer, Queue<String>> lines = new HashMap<>();

    // Method 1: Add a student to a specific book's line
    public void addStudent(int isbn, String studentName) {
        if (!lines.containsKey(isbn)) {
            lines.put(isbn, new LinkedList<>());
        }
        lines.get(isbn).add(studentName);
        System.out.println(studentName + " joined the waitlist for ISBN: " + isbn);
    }

    // Method 2: Check if a book has a line
    public boolean hasWishlist(int isbn) {
        return lines.containsKey(isbn) && !lines.get(isbn).isEmpty();
    }

    // Method 3: Remove and return the first student in line (FIFO)
    public String getNextStudent(int isbn) {
        if (hasWishlist(isbn)) {
            return lines.get(isbn).poll(); // .poll() grabs the first person and removes them from the queue
        }
        return null;
    }
}