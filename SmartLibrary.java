public class SmartLibrary implements LibraryADT {

    // The BST catalogue stores all available books, searchable by ISBN
    private BookBST catalogue = new BookBST();

    // The BorrowHistory stack records every borrowed book (LIFO order)
    private BorrowHistory history = new BorrowHistory();

    // We need our own BST root reference to support deletion,
    // because BookBST does not expose a delete() method.
    // We mirror inserts here so we can remove nodes ourselves.

    private Book bstRoot = null; // Mirrors the internal BST root for deletion purposes

    // ===========================================================
    // METHOD 1: addBook()
    // Called by: Menu (case 1)
    // Uses: BookBST.insert() — adds the book into the BST by ISBN
    // Also mirrors the insert into our local bstRoot for deletion support
    // ===========================================================
    @Override
    public void addBook(int isbn, String title, String author) {
        // Insert into the official BST catalogue (from teammate's class)
        catalogue.insert(isbn, title, author);

        // Mirror the same insert into our own root so we can delete later
        bstRoot = mirrorInsert(bstRoot, isbn, title, author);

        System.out.println("Book added: \"" + title + "\" (ISBN: " + isbn + ")");
    }

    // Private helper: mirrors the BST insert logic so SmartLibrary can manage deletion
    private Book mirrorInsert(Book node, int isbn, String title, String author) {
        if (node == null) {
            return new Book(isbn, title, author); // Create a new leaf node
        }
        if (isbn < node.getIsbn()) {
            node.left = mirrorInsert(node.left, isbn, title, author); // Go left
        } else if (isbn > node.getIsbn()) {
            node.right = mirrorInsert(node.right, isbn, title, author); // Go right
        }
        // If isbn == node.getIsbn(), duplicate — do nothing
        return node;
    }


    // ===========================================================
    // METHOD 2: searchBook()
    // Called by: Menu (case 2)
    // Uses: BookBST.search() — searches BST recursively by ISBN
    // Time complexity: O(log n) for a balanced BST
    // ===========================================================
    @Override
    public void searchBook(int isbn) {
        // Use the teammate's BST search method
        Book found = catalogue.search(isbn);

        if (found != null) {
            // Book exists in the catalogue
            System.out.println("Book Found: " + found); // calls Book.toString()
        } else {
            // Book not in BST
            System.out.println("Book not found. ISBN " + isbn + " does not exist in the catalogue.");
        }
    }


    // ===========================================================
    // METHOD 3: borrowBook()
    // Called by: Menu (case 3)
    // Steps:
    //   1. Search the BST for the book by ISBN
    //   2. If found, push it onto the BorrowHistory stack
    //   3. Remove the book from the BST (so it's no longer available)
    // Uses: BookBST.search(), BorrowHistory.push(), mirrorDelete()
    // ===========================================================
    @Override
    public void borrowBook(int isbn) {
        // Step 1: Search for the book using the teammate's BST
        Book toBorrow = catalogue.search(isbn);

        if (toBorrow != null) {
            // Step 2: Push the found book into the borrow history stack
            history.push(toBorrow);

            // Step 3: Remove the book from our mirrored BST
            // (BookBST has no delete method, so we handle it here)
            bstRoot = mirrorDelete(bstRoot, isbn);

            System.out.println("Successfully borrowed: \"" + toBorrow.getTitle() + "\"");
            System.out.println("This book has been removed from the catalogue.");
        } else {
            // Book not found — cannot borrow
            System.out.println("Cannot borrow: Book with ISBN " + isbn + " does not exist in the catalogue.");
        }
    }

    // Private helper: removes a node from our mirrored BST by ISBN
    // Uses standard BST deletion with in-order successor strategy
    private Book mirrorDelete(Book node, int isbn) {
        if (node == null) {
            return null; // Base case: node not found
        }

        if (isbn < node.getIsbn()) {
            // Target is in the left subtree
            node.left = mirrorDelete(node.left, isbn);

        } else if (isbn > node.getIsbn()) {
            // Target is in the right subtree
            node.right = mirrorDelete(node.right, isbn);

        } else {
            // Found the node to delete — handle 3 cases:

            // Case 1: No left child — replace with right child
            if (node.left == null) {
                return node.right;
            }

            // Case 2: No right child — replace with left child
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children — find the in-order successor (smallest in right subtree)
            Book successor = findMin(node.right);

            // Copy successor's data into current node
            node.setIsbn(successor.getIsbn());
            node.setTitle(successor.getTitle());
            node.setAuthor(successor.getAuthor());

            // Delete the successor from the right subtree
            node.right = mirrorDelete(node.right, successor.getIsbn());
        }

        return node;
    }

    // Private helper: finds the leftmost (minimum) node in a subtree
    private Book findMin(Book node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    // ===========================================================
    // METHOD 4: displayLatestHistory()
    // Called by: Menu (case 4)
    // Uses: BorrowHistory.viewHistory() — prints all borrowed books in LIFO order
    // ===========================================================
    @Override
    public void displayLatestHistory() {
        history.viewHistory(); // Delegates entirely to the BorrowHistory class
    }
}