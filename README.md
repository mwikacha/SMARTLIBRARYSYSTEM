# 📓 Smart Library System

> 🎓 **Course Project**: WIA 1002 Data Structure<br>
> 📅 **Semester**: 2, 2025/2026

---

## Table of Contents
- [Team Members](#10-team-members)
- [Project Overview](#20-project-overview)
- [Features](#30-features)
- [Additional Features](#40-additional-features)
- [Project Structure](#50-project-structure)
- [Screenshot](#60-screenshot)
- [Contribution](#70-contribution)
- [Challenges Faced](#80-challenges-faced)

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

## 6.0 Screenshot



## 7.0 Contribution

| NO | NAME | CONTRIBUTIONS | DESCRIPTION |
| :---: | --- | :---: | --- |
| 1 | ARISHA BINTI MOHAMAD | - ADT Designer & Book Entity<br>- Fine Management | Create the Interface for the Library System to ensure "Information Hiding."<br>Design the core Book entity class to encapsulate book details.<br>Implement the Fine Management feature to calculate overdue penalties during the book return process. |
| 2 | CHUA JEE HENG | - Catalogue Architect & Record Finder | Build a BST to store book titles and authors indexed by ISBN.<br>Implement a recursive Search function within the BST to find books by ISBN. |
| 3 | LAW XIN WEY | - Borrowing History | Implement a Stack to keep track of books checked out (Most recent on top). |
| 4 | KEERTTI A/P RASANRAN | - Smart Library Manager<br>- Return Book | Handle the central administrative logic, including the borrowing and returning process by coordinating the search from the catalogue etc. |
| 5 | HAN NING | - Functional Console Interface | Deliver a smooth Console Interface that allows a user to interact with the system without modifying the source code. |

---

## 8.0 Challenges Faced

1. One of the main challenges faced during the development process was integrating multiple data structures such as Binary Search Tree (BST), Stack, and ADT Interface into one complete system. The group needed to ensure that all components could work together smoothly without affecting each other’s functionality.
2. Implementing the BST for book searching was challenging because recursive insertion and searching methods were required. Proper handling of the left and right nodes was important to ensure books were stored and retrieved correctly based on ISBN values.

---
