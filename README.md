# 📓 Smart Library System

> 🎓 **Course Project**: WIA 1002 Data Structure<br>
> 📅 **Semester**: 2, 2025/2026

---

## Table of Contents
- [📓 Smart Library System](#-smart-library-system)
	- [Table of Contents](#table-of-contents)
	- [Team Members](#team-members)
 	- [1.0 Problem Statement](#10-problem-statement)
	- [2.0 Project Overview](#20-project-overview)
	- [3.0 Features](#30-features)
	- [4.0 Additional Features](#40-additional-features)
	- [5.0 Project Structure](#50-project-structure)
	- [6.0 Console Output Program](#60-console-output-program)
	- [7.0 Contribution](#70-contribution)
	- [8.0 Challenges Faced](#80-challenges-faced)
 	- [9.0 Conclusion](#90-conclusion)

--- 

## Team Members

| NO | NAME | MATRIX NUMBER |
| :---: | --- | :---: |
| 1 | ARISHA BINTI MOHAMAD | 25073436 |
| 2 | CHUA JEE HENG | 25058634 |
| 3 | LAW XIN WEY | 25062483 |
| 4 | KEERTTI A/P RASANRAN | 24057852 |
| 5 | HAN NING | 25067696 |

---

## 1.0 Problem Statement

Traditional library management systems often rely on inefficient data storage methods, such as flat arrays or manual logging, which lead to slow search times—especially as the database grows. Furthermore, managing concurrent demand for popular books and tracking complex user histories requires a system that can handle dynamic state changes efficiently. A linear approach results in an $O(n)$ time complexity that bottlenecks operations. This project addresses the need for a high-performance, automated library system that leverages specialized data structures to optimize data retrieval, ensure fair book distribution, and maintain secure, persistent transaction records.

---

## 2.0 Project Overview

The Smart Library System is a Java-based application designed to demonstrate the practical efficiency of the core of data structures in managing real-world information.

Books are stored in a database (BST) for fast searching by ISBN. When a student borrows a book, it's added to their "Borrowing History" (Stack), so they can see their most recent activity first.

---

## 3.0 Features

| Feature | Description |
| --- | --- |
| Interface Functionality | The console menu runs without crashing or terminating unexpectedly and handles invalid inputs gracefully. |
| BST Search Logic | Recursive search that utilises a recursive algorithm to locate books by ISBN, achieving an optimal O(log n) search time complexity. |
| Stack History | Integrated a Java Stack to track borrowed books, ensuring records are securely stored and displayed in a Last-In-First-Out (LIFO) sequence. |
| Information Hiding | Enforced strict encapsulation by making all internal data structures and entity attributes private, relying exclusively on the LibraryADT interface and getter methods. |
| Input Validation | The system handles cases like non-integer ISBNs or empty search results. |

--- 

## 4.0 Additional Features

| Feature | Description |
| --- | --- |
| Return Book |The Return Book feature allows users to return previously borrowed books by entering the book's ISBN and the number of late days. The system first searches the borrowing history to verify that the book was borrowed. If the book is found, the fine is calculated using the calculateFine() method in the Book class, where each late day incurs a penalty of RM1.00.<br>After the fine is calculated, the returned book is automatically reinserted into the BST catalogue, making it available for future borrowing. The system then displays a confirmation message along with the fine amount, if applicable. If the ISBN does not exist in the borrowing history, an error message is displayed to inform the user that the return process cannot be completed.<br>This feature integrates the Stack and BST data structures by retrieving borrowed records from the borrowing history and restoring them to the library catalogue. It improves the overall functionality of the Smart Library System by providing a complete borrowing and returning workflow. |
| Fine Management | The Fine Management feature is to calculate penalties for overdue book returns. When a user returns a book, the system requests the ISBN and the number of late days.<br>The system then searches for the book in the BST catalogue and calculates the fine using the calculateFine() method in the `Book` class.<br>The fine calculation is based on a simple rule where each late day is charged RM1.00. If the book is returned on time or before the due date, no fine is charged. After calculation, the system displays the fine amount to the user.<br>This feature was implemented using methods and conditional statements without requiring an additional data structure. The calculation is performed immediately during the return process and the result is displayed directly in the console.|
| Wishlist(Queue) | The waitlist handles situations where a student attempts to borrow a book that is already checked out. Instead of rejecting the request, the system places the student into a First-In, First-Out (FIFO) Queue tied directly to that specific book.<br>How It Works:<br>1) Joining the Line (Enqueue): If a book is missing from the available catalogue, the system scans the borrow logs. If the book is actively checked out, the student's name is appended to the back of that book's private waitlist queue.<br>2) Automatic Handover (Dequeue): When the current borrower returns the book, the system checks if anyone is waiting. If the queue is not empty, the book bypasses the available catalogue entirely; the system automatically removes the first student from the front of the queue and instantly checks the book out to them.<br>This ensures fair, automated book distribution based on who asked for the book first. |
| Due Date Reminder | The Due Date Reminder feature is integrated directly into the borrowing process to establish clear, automated return deadlines for library users. During a checkout transaction, the system utilizes Java's `LocalDate API` to automatically generate and assign a specific due date—exactly 14 days from the current system date—to the active `Book` object.<br>Upon successfully borrowing a book, the application retrieves data and prints a detailed transaction summary directly to the console. This summary explicitly displays both the checkout date and the final due date, ensuring the student is fully aware of their return timeframe and the policy to avoid fines.|
| CSV File Implementation | A CSV file persistence feature was implemented to store and manage library data. The system automatically saves book records to a CSV file, allowing data to be retained even after the program is closed. When the system starts, the stored data is loaded from the CSV file into the library catalogue. In addition, a separate borrow history CSV file is maintained to record borrowing transactions, including the ISBN, title, author, student name, borrow date, and due date. This feature improves data persistence, record tracking, and overall system usability. |

---

## 5.0 Project Structure
```
SMARTLIBRARYSYSTEM/
├── .idea
	├── production
		└── SMARTLIBRARYSYSTEM
	├── SMARTLIBRARYSYSTEM.iml
	├── misc.xml
	├── modules.xml
	├── vcs.xml
	└── workplace.xml
├── Book.java        
├── LibraryADT.java
├── BookBST.java                          
├── BorrowHistory.java            
├── SmartLibrary.java
├── Menu.java
├── SMARTLIBRARYSYSTEM.iml
├── README.md
└── .gitignore    
```

---

## 6.0 Console Output Program

|Feature|Screenshot|
|---|---|
|Add Book|<img width="355" height="397" alt="Screenshot 2026-06-10 124403" src="https://github.com/user-attachments/assets/b05c623f-772b-494e-8e7e-b7e7fb4b830d" />|
|Delete Book|<img width="650" height="356" alt="Screenshot 2026-06-10 124508" src="https://github.com/user-attachments/assets/f63f41f6-e6c5-4bda-92a3-efa30a616d23" />|
|Search Book|<img width="811" height="351" alt="Screenshot 2026-06-10 124558" src="https://github.com/user-attachments/assets/4e02bb23-9e4a-4281-8505-5638d1a11c11" />|
|Borrow Book|1. Available <br> <img width="517" height="556" alt="Screenshot 2026-06-10 124747" src="https://github.com/user-attachments/assets/c1c7d5a8-4075-406a-9b54-5953f4c075ed" /><br>2. Checked Out <br><img width="535" height="422" alt="Screenshot 2026-06-10 124813" src="https://github.com/user-attachments/assets/0cf775b2-806f-45b7-ada4-caeaf72ffcd9" />|
|Borrow History|<img width="350" height="373" alt="Screenshot 2026-06-10 124839" src="https://github.com/user-attachments/assets/ec9564b2-336c-4954-a88b-e9202188dad0" />|
|Return Book|1. Fine Management<br>2. Automatically Assigned<br> <img width="548" height="534" alt="Screenshot 2026-06-10 124922" src="https://github.com/user-attachments/assets/f01d56cb-a60c-4ff0-b5ce-e92838a3db17" />|
|Waitlist (Queue)|<img width="394" height="399" alt="Screenshot 2026-06-10 124854" src="https://github.com/user-attachments/assets/83e85645-1e56-45d6-bde9-f862cf37f1c5" />|

---

## 7.0 Contribution

| NO | NAME | CONTRIBUTIONS | DESCRIPTION |
| :---: | --- | :---: | --- |
| 1 | ARISHA BINTI MOHAMAD | - ADT Designer & Book Entity<br>- Fine Management | - Create the Interface for the Library System to ensure "Information Hiding."<br>- Design the core Book entity class to encapsulate book details.<br>- Implement the Fine Management feature to calculate overdue penalties during the book return process. |
| 2 | CHUA JEE HENG | - Catalogue Architect & Record Finder<br>- Waitlist Queue | - Build a BST to store book titles and authors indexed by ISBN.<br>- Implement a recursive Search function within the BST to find books by ISBN.<br>- Implement the Waitlist Management feature to queue students for unavailable books and handle automatic reassignments during the book return process.|
| 3 | LAW XIN WEY | - Borrowing History <br>- Due Date Reminder <br> - CSV File | - Implement a Stack to keep track of books checked out (Most recent on top). <br>Implement an Due Date Reminder system utilizing Java's `LocalDate` API to actively track borrowing periods and their expiration or overdue status. <br> - Designed and implemented a CSV-based data persistence system for the Smart Library System, enabling automatic storage and retrieval of book records and borrowing history. The feature records transaction details such as ISBN, book information, student names, borrow dates, and due dates, ensuring data consistency and allowing the system to maintain records even after it is closed and restarted. |
| 4 | KEERTTI A/P RASANRAN | - Smart Library Manager<br>- Return Book | - Handle the central administrative logic, including the borrowing and returning process by coordinating the search from the catalogue etc.<br> - Implemented the Return Book feature that enables users to return borrowed books using their ISBN.<br>- Integrated the borrowing history and BST catalogue to support the complete book return process. |
| 5 | HAN NING | - Functional Console Interface<br> - Remove Book (True Deletion)<br> - Refine `addBook` and `deleteBook` | - Deliver a smooth Console Interface that allows a user to interact with the system without modifying the source code.<br> - Implement the Dynamic Catalogue feature to safely extract and delete book nodes from the active database during the borrowing process. <br> - Refined the `addBook` method to prevent the addition of duplicate or currently borrowed items, and introduced a `deleteBook` method to safely remove available books from the permanent catalogue. |

---

## 8.0 Challenges Faced

1. One of the main challenges faced during the development process was integrating multiple data structures such as Binary Search Tree (BST), Stack, and ADT Interface into one complete system. The group needed to ensure that all components could work together smoothly without affecting each other’s functionality.
2. Implementing the BST for book searching was challenging because recursive insertion and searching methods were required. Proper handling of the left and right nodes was important to ensure books were stored and retrieved correctly based on ISBN values.

---

## 9.0 Conclusion

The Smart Library System successfully demonstrates the practical efficiency of core data structures in managing real-world information. By correctly applying a Binary Search Tree for rapid data retrieval, a Stack for chronological history tracking, and a Queue for fair waitlist management, the project effectively solved the performance bottlenecks found in standard library arrays. Coupled with integrated CSV data persistence and automated fine calculation, the final application provides a robust, scalable, and technically sound solution for library administration. 

---
