# 🚀 Library Management System

**Author:** Joud Senan  
**Professor:** Khaled El Dassouki  
**Course:** EECE332 – Fall 2024 ( Object-Oriented and Effective Java Programming )  
**University:** American University of Beirut

## 🛠 Overview

The Library Management System is a Java-based application developed as part of the EECE332 course at the American University of Beirut. The goal of the project is to digitize and streamline library operations for efficient resource management. The system handles book lending, meeting room reservations, electronic resource usage, and user interaction tracking.

## 🔧 Features

### 📌 Admin Features
- **Manage Resources**: Add or remove books, meeting rooms, tablets, and PCs.
- **Configure Rules**: Set borrowing durations, penalties, and renewal options.
- **View Logs and Statistics**: Track user activities, overdue items, popular resources, and frequent borrowers.

### 📌 Student/Professor Features
- **Search Books**: Find books using title, author, genre, or ISBN.
- **Borrow/Return Books**: Interact with physical and electronic resources.
- **Reserve Meeting Rooms**: Book and release study/conference rooms.
- **Use Tablets/PCs**: Access electronic devices within the library.
- **View History**: See borrowing and interaction history.

## 🔧 System Design

### 📦 Classes and Relationships

- `User` *(Abstract)*:
  - Subclasses: `Admin`, `Student`, `Professor`
  - Attributes: `name`, `address`, `phoneNumber`, `emailAddress`

- `Library`:
  - Attributes: `name`, `faculty`, `admin`, `resources`, `books`, `rooms`, `rules`

- `Book` *(Abstract)*:
  - Subclasses: `PhysicalBook`, `eBook`
  - Attributes: `title`, `ISBN`, `author`, `description`, `available`, `publicationYear`, `genre`

- `ElectronicResource` *(Abstract)*:
  - Subclasses: `Tablet`, `PC`
  - Attributes: `available`

- `MeetingRoom`:
  - Attributes: `location`, `phoneNumber`, `reserved`

- `Rules`:
  - Attributes: `studentBorrowDays`, `professorBorrowDays`, `renewalAllowedProfessor`, `renewalAllowedStudent`, `latePenalty`

- `BorrowingData`:
  - Attributes: `userName`, `resourceTitle`, `borrowDate`, `returnDate`, `resourceType`

- `Logger`: Tracks user interactions and system logs.

### 📑 Relationships
- **Inheritance**: `Admin`, `Student`, `Professor` inherit from `User`; `PhysicalBook`, `eBook` from `Book`; `Tablet`, `PC` from `ElectronicResource`
- **Composition**: Libraries contain Books, Rooms, and Electronic Resources
- **Associations**: Admins manage libraries; Students/Professors use libraries in their faculty

## ⚙️ Implementation Steps

1. Created 3 faculties with 3 libraries each
2. Created and assigned Admins to each library
3. Populated libraries with various resources (Books, PCs, Tablets, Meeting Rooms)
4. Configured library-specific rules for borrowing and penalties
5. Created Professors and Students and assigned them to faculties
6. Simulated various interactions such as borrowing books, reserving rooms, and viewing logs

## 📂 Deliverables

- Java Source Code
- UML Class Diagram
- Javadoc Documentation
- PDF detailing the implementation process
- Working Simulation of System Features ( simulated in the video on my drive - link below )

## 🔗 Link for Simulation Video

https://mailaub-my.sharepoint.com/:v:/g/personal/jas53_mail_aub_edu/EX7aDZZY1WdFp_nc6YeI-jABYm8Qdi0G_05F3w9NlZu5Jw?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=kyYCig

## 📈 Technologies Used

- Java (OOP principles)
- Javadoc
- UML Modeling Tools

## 📜 License

This project was created for educational purposes under the EECE 332 course at AUB. Please cite the original author when referencing or reusing any part of this work.

---

