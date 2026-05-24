# 📓 Smart Library System

> 🎓 **Course Project**: WIA 1002 Data Structure
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
| Return Book | |
| Fine Management |  |
| Wishlist (Queue) | |

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
| 1 | ARISHA BINTI MOHAMAD | ADT Designer & Book Entity | Create the Interface for the Library System to ensure "Information Hiding."  Design the core Book entity class to encapsulate book details. |
| 2 | CHUA JEE HENG | Catalogue Architect & Record Finder | Build a BST to store book titles and authors indexed by ISBN.  Implement a recursive Search function within the BST to find books by ISBN. |
| 3 | LAW XIN WEY | Borrowing History | Implement a Stack to keep track of books checked out (Most recent on top). |
| 4 | KEERTTI A/P RASANRAN | Smart Library Manager | Handle the central administrative logic, including the borrowing and returning process by coordinating the search from the catalogue etc. |
| 5 | HAN NING | Functional Console Interface | Deliver a Console Interface that allows a user (Librarian or Student) to interact with the system without modifying the source code. |

---

## 8.0 Challenges Faced
