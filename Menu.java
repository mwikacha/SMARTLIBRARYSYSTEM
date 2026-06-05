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

                if (choice == 8) {
                    keepRunning = false;
                    System.out.println("Exiting Smart Library System. Goodbye!");
                } else {
                    choices(choice, sc); 
                }
            } else {
                String invalid = sc.next(); 
                System.out.println("Invalid input! '" + invalid + "' is not a number. Please enter 1-7.");
            }
        }
        sc.close(); 
    }

    private void printMenu() {
        System.out.println("\n--------------------------");
        System.out.println("    Smart Library Menu");       // 4 Space
        System.out.println("--------------------------");
        System.out.println("[1] Add Book");
        System.out.println("[2] Search Book (BST)");
        System.out.println("[3] Borrow / Join Waitlist (Stack)"); // Adjusted label
        System.out.println("[4] History");
        System.out.println("[5] Return Book");
        System.out.println("[6] View Book Waitlist (Queue)"); // NEW option
        System.out.println("[7] Due Date Reminder");
        System.out.println("[8] Exit");
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
                    sc.next();
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;
            
            case 2:
                System.out.print("Enter ISBN to search: ");
                if (sc.hasNextInt()) {
                    int searchIsbn = sc.nextInt();
                    library.searchBook(searchIsbn);
                } else {
                    sc.next();
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;

            case 3:
                System.out.print("Enter ISBN to borrow: ");

                if (sc.hasNextInt()) {
                    int borrowIsbn = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    if (library.isBookBorrowed(borrowIsbn)) {

                        System.out.println("Book is currently borrowed.");
                        System.out.print("Join waitlist? (Y/N): ");
                        String answer = sc.nextLine();

                        if (answer.equalsIgnoreCase("Y")) {
                            library.joinWaitlist(borrowIsbn, name);
                        } else {
                            System.out.println("Cancelled.");
                        }

                    } else {
                        library.borrowBook(borrowIsbn, name);
                    }

                } else {
                    sc.next();
                    System.out.println("Error: ISBN must be a valid integer!");
                }
                break;

            case 4:
                System.out.println("Displaying History ......");
                library.displayLatestHistory();
                break;

            case 5:
                System.out.print("Enter ISBN to return: ");
                if (sc.hasNextInt()) {
                    int returnIsbn = sc.nextInt();
                    System.out.print("Enter number of late days: ");
                    if (sc.hasNextInt()) {
                        int lateDays = sc.nextInt();
                        library.returnBook(returnIsbn, lateDays);
                    } else {
                        sc.next();
                        System.out.println("Error: Late days must be a valid integer!");
                    }
                }  
                break;      
                
            case 6: // NEW: Option execution to check waitlists
                System.out.print("Enter Book ISBN to view waitlist: ");
                if (sc.hasNextInt()) {
                    int queueIsbn = sc.nextInt();
                    library.viewWaitlist(queueIsbn);
                } else {
                    sc.next();
                    System.out.println("Error: ISBN must be an integer!");
                }
                break;

            case 7:
                library.checkDueDateReminder();
                break;

            default:
                System.out.println("Invalid Option!");
        }
    }
}