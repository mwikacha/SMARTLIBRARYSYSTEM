public class BookBST {
    private Book root;

    // Public insert method
    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    // Private recursive insert helper
    private Book ins(Book r, int i, String t, String a) {
        if (r == null) return new Book(i, t, a);
        if (i < r.getIsbn()) {
            r.left = ins(r.left, i, t, a);
        } else if (i > r.getIsbn()) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    // Public search method
    public Book search(int i) {
        return sea(root, i);
    }

    // Private recursive search helper
    private Book sea(Book r, int i) {
        if (r == null || r.getIsbn() == i) return r;
        return (i < r.getIsbn()) ? sea(r.left, i) : sea(r.right, i);
    }
}