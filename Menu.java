import java.util.Scanner;

public class Menu {
    private SmartLibrary library = new SmartLibrary();

    public static void main(String[] args) {
        new Menu().runMenu();
    }
    
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            printMenu();
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 5) {
                    keepRunning = false;
                    System.out.println("Exiting Smart Library System. Goodbye!");
                } else {
                    choices(choice, sc); 
                }
            } else {
                String invalid = sc.next(); // Clear the invalid text
                System.out.println("Invalid input! '" + invalid + "' is not a number. Please enter 1-5.");
            }
        }
        
        sc.close(); 
    }

    private void printMenu() {
        System.out.println("\n--------------------------");
        System.out.println("    Smart Library Menu"); // 4 Spaces
        System.out.println("--------------------------");
        System.out.println("[1] Add Book");
        System.out.println("[2] Search Book (BST)");
        System.out.println("[3] Borrow Book (Stack)");
        System.out.println("[4] History");
        System.out.println("[5] Exit");
        System.out.println("--------------------------");
    }

    private void choices(int choice, Scanner sc) {

        switch (choice) {
            case 1:
                System.out.print("Enter ISBN: ");
                if (sc.hasNextInt()) {
                    int isbn = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    library.addBook(isbn, title, author);
                } else {
                    sc.next(); // Clear invalid input
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;
            
            case 2:
                System.out.print("Enter ISBN to search: ");
                if (sc.hasNextInt()) {
                    int searchIsbn = sc.nextInt();
                    System.out.println("Searching for " + searchIsbn + " ......");
                    library.searchBook(searchIsbn);
                } else {
                    sc.next(); // Clear invalid input
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;

            case 3:
                System.out.print("Enter ISBN to borrow: ");
                if (sc.hasNextInt()) {
                    int borrowIsbn = sc.nextInt();
                    library.borrowBook(borrowIsbn);
                } else {
                    sc.next(); // Clear invalid input
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;

            case 4:
                System.out.println("Displaying History ......");
                library.displayLatestHistory();
                break;
        
            default:
                System.out.println("Invalid Option!");
        }
    }

}
