# Library Management System

# Table of Contents
1. [Overview](#overview)
2. [Features](#features)

## Overview
Library Management System is a simple library project where you can add a book or borrow one. It contains JWT authentication to ensure secure login. The project has one-to-many relationship between books and borrowing records as well as one-to-many relationship between patron and borrowing records.

## Features
### Authentication
- Librarians, and customers can create accounts.

### Books Service
1. View all the books in the library.
2. Find a book by ID.
3. Update book details.
4. remove book.

### Patron
1. Add a new patron.
2. View All patrons.
3. Find a patron by ID.
4. Remove a patron
5. Update a patron

### Borrowing Record
1. Borrow a book
2. Return book

## Database Schema
The application uses a relational database using PostgreSQL with tables for users, books, patrons, and borrowing records. 