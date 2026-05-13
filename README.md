# 📚 Library Management System

![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=flat-square)
![Stars](https://img.shields.io/github/stars/nandkishor-ux/library-management-system?style=flat-square)

A console-based **Library Management System** built in Java, demonstrating core Object-Oriented Programming concepts — encapsulation, inheritance, abstraction, and polymorphism — through a clean and practical real-world application.

---

## Table of Contents

1. [Project Description & Goals](#1-project-description--goals)
2. [Tech Stack & Architecture](#2-tech-stack--architecture)
3. [Prerequisites](#3-prerequisites)
4. [Setup & Installation](#4-setup--installation)
5. [Running the Project](#5-running-the-project)
6. [Features](#6-features)
7. [Data Model Overview](#7-data-model-overview)
8. [API / Interface Reference](#8-api--interface-reference)
9. [Configuration & Environment Variables](#9-configuration--environment-variables)
10. [Testing](#10-testing)
11. [Deployment](#11-deployment)
12. [Contribution Guidelines](#12-contribution-guidelines)
13. [Known Issues & Future Enhancements](#13-known-issues--future-enhancements)
14. [License](#14-license)
15. [Contact & Support](#15-contact--support)

---

## 1. Project Description & Goals

This project simulates the core operations of a physical library — borrowing books, returning them, and checking availability — while enforcing role-based borrowing limits for different user types (Students vs. Teachers).

**Goals:**
- Demonstrate OOP principles in Java (abstraction, inheritance, polymorphism, encapsulation)
- Model a realistic library domain with `Book`, `User`, `Student`, `Teacher`, and `Library` classes
- Provide a simple, interactive CLI menu for library operations
- Serve as a learning reference for Java beginners and intermediate developers

---

## 2. Tech Stack & Architecture

**Language:** Java (JDK 8+)  
**Paradigm:** Object-Oriented Programming  
**Build Tool:** None required (single-file compilation)  
**Package:** `projectjava`

### Architecture Overview

```
┌──────────────────────────────────────────────┐
│                   Main.java                  │
│  ┌───────────┐  ┌─────────────────────────┐  │
│  │  Library  │  │       User (abstract)   │  │
│  │-----------│  │------------------------ │  │
│  │ books[]   │  │  + name                 │  │
│  │           │  │  + borrowedBooks        │  │
│  │ addBook() │  │  + getMaxBooks()        │  │
│  │ lendBook()│  └─────────┬───────────────┘  │
│  │ returnBook│            │                  │
│  └─────┬─────┘     ┌──────┴──────┐           │
│        │           │             │           │
│  ┌─────▼─────┐ ┌───▼────┐ ┌─────▼──────┐    │
│  │   Book    │ │Student │ │  Teacher   │    │
│  │-----------│ │--------│ │----------- │    │
│  │ title     │ │MAX=3   │ │  MAX=5     │    │
│  │ author    │ └────────┘ └────────────┘    │
│  │isAvailable│                              │
│  └───────────┘                              │
└──────────────────────────────────────────────┘
```

| Component | Responsibility |
|-----------|----------------|
| `Book` | Stores book data; tracks availability; handles borrow/return state |
| `User` (abstract) | Base class for all user types; tracks borrowed count |
| `Student` | Extends `User`; max 3 books |
| `Teacher` | Extends `User`; max 5 books |
| `Library` | Manages the book collection; mediates borrow/return operations |
| `Main` | Entry point; drives the interactive CLI menu |

---

## 3. Prerequisites

| Requirement | Version |
|-------------|---------|
| Java Development Kit (JDK) | 8 or higher |
| A terminal / command prompt | Any |

Verify your Java installation:

```bash
java -version
javac -version
```

---

## 4. Setup & Installation

```bash
# 1. Clone the repository
git clone https://github.com/nandkishor-ux/library-management-system.git

# 2. Navigate into the project directory
cd library-management-system
```

No dependencies or package manager required — this is a standalone Java application.

---

## 5. Running the Project

### Compile

```bash
javac Main.java
```

### Run

```bash
java projectjava.Main
```

### Expected Output

```
Library Management System:
1. Display available books
2. Borrow a book
3. Return a book
4. Exit
Enter your choice:
```

### Example Session

```
Enter your choice: 1
Available Books:
1984 by George Orwell
To Kill a Mockingbird by Harper Lee
The Great Gatsby by F. Scott Fitzgerald

Enter your choice: 2
Enter your name (Alice or Mr. Smith): Alice
Enter the title of the book you want to borrow: 1984
Alice borrowed 1984

Enter your choice: 3
Enter your name (Alice or Mr. Smith): Alice
Enter the title of the book you want to return: 1984
Alice returned 1984
```

> **Note:** The current version has two hardcoded users — `Alice` (Student) and `Mr. Smith` (Teacher). See [Future Enhancements](#13-known-issues--future-enhancements) for dynamic user registration plans.

---

## 6. Features

### Core Features
- **Display available books** — Lists all books currently not on loan
- **Borrow a book** — Allows a user to check out an available book, subject to their borrowing limit
- **Return a book** — Marks a borrowed book as available again
- **Role-based borrowing limits** — Students may borrow up to 3 books; Teachers up to 5
- **Availability tracking** — Each book maintains a boolean availability flag
- **Input-driven CLI menu** — Interactive loop with `Scanner`; exits cleanly on selection `4`

### Optional / Planned Features
- Dynamic user registration (see [Future Enhancements](#13-known-issues--future-enhancements))
- Persistent storage via file I/O or a database
- Book search by author
- Due date tracking and overdue fines
- Admin role with elevated privileges

---

## 7. Data Model Overview

This application uses in-memory Java objects (no database). The logical data model is:

### Book

```
Book
├── title        : String   (e.g. "1984")
├── author       : String   (e.g. "George Orwell")
└── isAvailable  : boolean  (true = on shelf, false = borrowed)
```

### User (abstract base)

```
User
├── name          : String
└── borrowedBooks : int     (current count of books on loan)
```

### Student (extends User)

```
Student
└── MAX_BOOKS : 3  (static constant)
```

### Teacher (extends User)

```
Teacher
└── MAX_BOOKS : 5  (static constant)
```

### Library

```
Library
└── books : ArrayList<Book>
```

### Entity Relationship (Logical)

```
┌──────────┐   borrows   ┌──────────┐
│   User   │────────────▶│   Book   │
│  (name)  │             │ (title)  │
└──────────┘             │ (author) │
     △                   └──────────┘
     │
  ┌──┴──────┐
  │         │
Student  Teacher
```

---

## 8. API / Interface Reference

This is a **CLI application** — there is no HTTP API. The public Java interface for each class is summarised below.

### `Book`

| Method | Signature | Description |
|--------|-----------|-------------|
| Constructor | `Book(String title, String author)` | Creates an available book |
| `getTitle()` | `→ String` | Returns the book title |
| `getAuthor()` | `→ String` | Returns the author name |
| `isAvailable()` | `→ boolean` | Returns availability status |
| `borrowBook()` | `void` | Marks book as unavailable |
| `returnBook()` | `void` | Marks book as available |

### `User` (abstract)

| Method | Signature | Description |
|--------|-----------|-------------|
| `getName()` | `→ String` | Returns user name |
| `getBorrowedBooks()` | `→ int` | Returns current borrow count |
| `borrowBook()` | `void` | Increments borrow counter |
| `returnBook()` | `void` | Decrements borrow counter |
| `getMaxBooks()` | `abstract int` | Max books allowed (overridden per role) |

### `Library`

| Method | Signature | Description |
|--------|-----------|-------------|
| `addBook(Book)` | `void` | Adds a book to the collection |
| `displayAvailableBooks()` | `void` | Prints all available books |
| `findBook(String title)` | `→ Book \| null` | Case-insensitive title search |
| `lendBook(User, String title)` | `void` | Lends book if available and within user limit |
| `returnBook(User, String title)` | `void` | Processes a book return |

> **Authentication:** No authentication mechanism exists in the current version. User identity is determined by name string matching.

---

## 9. Configuration & Environment Variables

This project requires **no configuration files or environment variables**. All settings are hardcoded in `Main.java`:

| Setting | Location | Default Value |
|---------|----------|---------------|
| Pre-loaded books | `Main.main()` | "1984", "To Kill a Mockingbird", "The Great Gatsby" |
| Pre-registered users | `Main.main()` | `Alice` (Student), `Mr. Smith` (Teacher) |
| Student borrow limit | `Student.MAX_BOOKS` | `3` |
| Teacher borrow limit | `Teacher.MAX_BOOKS` | `5` |

To customise, edit `Main.java` directly and recompile.

---

## 10. Testing

No automated test suite exists in the current version. To test manually:

### Borrow Limit Test
1. Log in as `Alice` (Student, max 3 books)
2. Borrow 3 books in succession — all should succeed
3. Attempt to borrow a 4th — expect: `"Alice cannot borrow more than 3 books."`

### Availability Test
1. Borrow a book as any user
2. Run "Display available books" — the borrowed title should not appear
3. Return the book — it should reappear in the list

### Edge Case Tests
- Try to borrow a book that doesn't exist → `"Book is not available."`
- Try to return a book that was never borrowed → `"Book was not borrowed."`
- Enter an invalid menu number → `"Invalid choice. Please try again."`

### Adding Unit Tests (Recommended)

To add JUnit 5 tests:

```bash
# Add JUnit 5 to your classpath
# Then create e.g. BookTest.java:

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void bookShouldBeAvailableOnCreation() {
        Book book = new Book("Test Title", "Test Author");
        assertTrue(book.isAvailable());
    }

    @Test
    void borrowShouldMarkBookUnavailable() {
        Book book = new Book("Test Title", "Test Author");
        book.borrowBook();
        assertFalse(book.isAvailable());
    }
}
```

---

## 11. Deployment

### Local (Standard)

As a CLI application, deployment simply means compiling and running on any machine with a JDK installed (see [Section 5](#5-running-the-project)).

### Dockerized Setup

No `Dockerfile` is included. Below is a minimal example to containerize the app:

```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY Main.java .

RUN javac Main.java

CMD ["java", "projectjava.Main"]
```

Build and run:

```bash
docker build -t library-management-system .
docker run -it library-management-system
```

> The `-it` flag is required because the app reads from `stdin`.

---

## 12. Contribution Guidelines

Contributions are welcome! Please follow the process below.

### Branching Model

```
main          ← stable, production-ready code
└── feature/  ← new features   (e.g. feature/add-search-by-author)
└── fix/      ← bug fixes       (e.g. fix/return-book-null-check)
└── docs/     ← documentation   (e.g. docs/update-readme)
```

### Steps

1. **Fork** the repository
2. **Create** a branch from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Write** clean, well-commented Java code following standard naming conventions
4. **Test** your changes manually (see [Section 10](#10-testing))
5. **Commit** with a clear message:
   ```bash
   git commit -m "feat: add search by author functionality"
   ```
6. **Push** and open a **Pull Request** against `main`

### Coding Standards

- Follow standard Java naming conventions (`camelCase` for methods and variables, `PascalCase` for classes)
- Keep methods focused and short (single responsibility)
- Add Javadoc comments to all public methods
- Do not commit hardcoded test data unless it serves as a meaningful example

### PR Process

- Provide a clear description of what the PR does and why
- Reference any related issues (e.g. `Closes #12`)
- At least one reviewer approval is required before merging

---

## 13. Known Issues & Future Enhancements

### Known Issues

- **Hardcoded users:** Only `Alice` and `Mr. Smith` can use the system. Any other name defaults to `teacher1`.
- **No persistence:** All data is lost when the program exits.
- **Case sensitivity in user lookup:** The name check uses `equalsIgnoreCase` but is limited to exactly two users.
- **No duplicate book handling:** Adding the same book title twice creates two separate entries.

### Future Enhancements

- [ ] Dynamic user registration at runtime
- [ ] Persistent storage using file I/O (`books.txt`, `users.txt`) or SQLite
- [ ] Search books by author or keyword
- [ ] Due date system with overdue fine calculation
- [ ] Admin role with the ability to add/remove books and users
- [ ] Unit test suite with JUnit 5
- [ ] GUI frontend using Java Swing or JavaFX
- [ ] Multi-copy support (multiple copies of the same book)
- [ ] Borrow history / transaction log per user

---

## 14. License

This project is licensed under the **MIT License**.

```
MIT License

Copyright (c) 2024 nandkishor-ux

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
```

---

## 15. Contact & Support

**Author:** [nandkishor-ux](https://github.com/nandkishor-ux)  
**Repository:** [github.com/nandkishor-ux/library-management-system](https://github.com/nandkishor-ux/library-management-system)

For bugs or feature requests, please [open an issue](https://github.com/nandkishor-ux/library-management-system/issues) on GitHub.

---

> ⭐ If you found this project helpful, consider giving it a star on GitHub!
