import java.io.PrintWriter;

public class BookBST {
    private Book root;

    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    private Book ins(Book r, int i, String t, String a) {
        if (r == null) return new Book(i, t, a);

        if (i < r.getIsbn()) {
            r.left = ins(r.left, i, t, a);
        } else if (i > r.getIsbn()) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    public Book search(int i) {
        return sea(root, i);
    }

    private Book sea(Book r, int i) {
        if (r == null || r.getIsbn() == i) return r;

        return (i < r.getIsbn())
                ? sea(r.left, i)
                : sea(r.right, i);
    }

    public void remove(int isbn) {
        root = deleteNode(root, isbn);
    }

    private Book deleteNode(Book node, int isbn) {
        if (node == null) return null;

        if (isbn < node.getIsbn()) {
            node.left = deleteNode(node.left, isbn);
        } else if (isbn > node.getIsbn()) {
            node.right = deleteNode(node.right, isbn);
        } else {

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Book successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            node.setIsbn(successor.getIsbn());
            node.setTitle(successor.getTitle());
            node.setAuthor(successor.getAuthor());

            node.right = deleteNode(node.right, successor.getIsbn());
        }
        return node;
    }

    public Book getRoot() {
        return root;
    }

    public void inorderToCSV(Book node, PrintWriter pw) {
        if (node == null) return;

        inorderToCSV(node.left, pw);
        pw.println(node.getIsbn() + "," + node.getTitle() + "," + node.getAuthor());
        inorderToCSV(node.right, pw);
    }
}