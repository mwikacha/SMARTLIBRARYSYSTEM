# 📓 Smart Library System

> 🎓 **Course Project**: WIA 1002 Data Structure<br>
> 📅 **Semester**: 2, 2025/2026

---

## Table of Contents
- [📓 Smart Library System](#-smart-library-system)
	- [Table of Contents](#table-of-contents)
	- [1.0 Team Members](#10-team-members)
	- [2.0 Project Overview](#20-project-overview)
	- [3.0 Features](#30-features)
	- [4.0 Additional Features](#40-additional-features)
	- [5.0 Project Structure](#50-project-structure)
	- [6.0 Console Output Program](#60-console-output-program)
	- [7.0 Contribution](#70-contribution)
	- [8.0 Challenges Faced](#80-challenges-faced)

--- 

## 1.0 Team Members

| NO | NAME | MATRIX NUMBER |
| :---: | --- | :---: |
| 1 | ARISHA BINTI MOHAMAD | 25073436 |
| 2 | CHUA JEE HENG | 25058634 |
| 3 | LAW XIN WEY | 25062483 |
| 4 | KEERTTI A/P RASANRAN | 24057852 |
| 5 | HAN NING | 25067696 |

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
| Due Date Reminder | The Due Date Reminder feature proactively monitors the `BorrowHistory` stack to notify administrators of upcoming deadlines and overdue items. During the checkout process, the system utilizes Java's `LocalDate` API to automatically generate and stamp a due date— 14 days from the current date—onto each borrowed `Book` object.<br>When the administrator initiates a system check, the application uses the `ChronoUnit` class to calculate the exact difference in days between the current system date and each book's assigned due date. Based on this temporal data, the system filters the checked-out inventory into specific alert tiers, generating standard warnings for items within a three-day proximity, urgent alerts for items due today, and penalty notices for overdue books.<br>This feature was implemented entirely using Java's standard time management libraries and iterative logic, requiring no additional data structures. The chronological assessment is processed dynamically in real-time, outputting a clean, categorized status report directly to the console for the administrator to review. |

---

## 5.0 Project Structure
```
SMARTLIBRARYSYSTEM/
├── .idea
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
|Add Book|<img width="371" height="354" alt="Screenshot 2026-06-04 184037" src="https://github.com/user-attachments/assets/95fa0b04-e358-42d0-ad5a-d82cc141b23a" />|
|Search Book|<img width="811" height="312" alt="Screenshot 2026-06-04 184130" src="https://github.com/user-attachments/assets/416040c4-8ff4-4a53-b4ce-a0da9ad55380" />|
|Borrow Book|1. Available <br> <img width="517" height="401" alt="Screenshot 2026-06-04 184202" src="https://github.com/user-attachments/assets/babebcea-3d44-4a81-9b8c-fbc08130ec21" /> <br>2. Checked Out <br><img width="645" height="400" alt="Screenshot 2026-06-04 184334" src="https://github.com/user-attachments/assets/2b7b3319-70e0-4156-a0e2-934081139742" />|
|Borrow History|<img width="574" height="356" alt="Screenshot 2026-06-04 184224" src="https://github.com/user-attachments/assets/bc9f2639-8c44-4545-a773-ca856a2f542d" />|
|Return Book|1. Fine Management<br>2. Automatically Assigned<br> <img width="545" height="464" alt="Screenshot 2026-06-04 184442" src="https://github.com/user-attachments/assets/aca2ebe9-4be0-463d-b150-95cb055ff424" />|
|Waitlist (Queue)|<img width="392" height="355" alt="Screenshot 2026-06-04 184404" src="https://github.com/user-attachments/assets/62d42ab4-37ba-4f30-813e-f5c81f448882" />|

## 7.0 Contribution

| NO | NAME | CONTRIBUTIONS | DESCRIPTION |
| :---: | --- | :---: | --- |
| 1 | ARISHA BINTI MOHAMAD | - ADT Designer & Book Entity<br>- Fine Management | - Create the Interface for the Library System to ensure "Information Hiding."<br>- Design the core Book entity class to encapsulate book details.<br>- Implement the Fine Management feature to calculate overdue penalties during the book return process. |
| 2 | CHUA JEE HENG | - Catalogue Architect & Record Finder<br>- Waitlist Queue | - Build a BST to store book titles and authors indexed by ISBN.<br>- Implement a recursive Search function within the BST to find books by ISBN.<br>- Implement the Waitlist Management feature to queue students for unavailable books and handle automatic reassignments during the book return process.|
| 3 | LAW XIN WEY | - Borrowing History <br>- Due Date Reminder | - Implement a Stack to keep track of books checked out (Most recent on top). <br>Implement an Due Date Reminder system utilizing Java's `LocalDate` API to actively track borrowing periods and sending a alert within a three-day proximity of their expiration or overdue status. |
| 4 | KEERTTI A/P RASANRAN | - Smart Library Manager<br>- Return Book | - Handle the central administrative logic, including the borrowing and returning process by coordinating the search from the catalogue etc.<br> - Implemented the Return Book feature that enables users to return borrowed books using their ISBN.<br>- Integrated the borrowing history and BST catalogue to support the complete book return process. |
| 5 | HAN NING | - Functional Console Interface<br> - Remove Book (True Deletion)<br> - Refine Add Book | - Deliver a smooth Console Interface that allows a user to interact with the system without modifying the source code.<br> - Implement the Dynamic Catalogue feature to safely extract and delete book nodes from the active database during the borrowing process. <br> - Implement the Dual-Validation Add Book feature to prevent ISBN duplication by cross-referencing both the active catalogue and the checkout logs. |

---

## 8.0 Challenges Faced

1. One of the main challenges faced during the development process was integrating multiple data structures such as Binary Search Tree (BST), Stack, and ADT Interface into one complete system. The group needed to ensure that all components could work together smoothly without affecting each other’s functionality.
2. Implementing the BST for book searching was challenging because recursive insertion and searching methods were required. Proper handling of the left and right nodes was important to ensure books were stored and retrieved correctly based on ISBN values.

---
