# CS-320 Software Testing, Automation, and QA – Contact Service

## Project Overview

This repository contains my work for **Project One** and **Project Two** in CS-320: Software Testing, Automation, and Quality Assurance.

The goal of this project was to design, implement, and thoroughly unit-test a Contact Service as part of a mobile application backend. The service allows contacts to be added, deleted, and updated while strictly enforcing the customer’s requirements for data integrity.

**Included in this repository:**
- Contact.java (entity class)
- ContactService.java (service class)
- ContactTest.java and ContactServiceTest.java (JUnit unit tests)
- Project Two Summary and Reflections Report

---

## Features Implemented

### Contact Class
- Unique Contact ID (required, max 10 characters, not null, not updatable)
- First Name (required, max 10 characters, not null)
- Last Name (required, max 10 characters, not null)
- Phone Number (required, exactly 10 digits, not null)
- Address (required, max 30 characters, not null)

### Contact Service
- Add a new contact with a unique ID
- Delete a contact by ID
- Update first name, last name, phone number, and address by ID

All requirements were verified through comprehensive JUnit unit tests covering valid inputs, boundary values, null checks, and exception handling.

---

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I ensure functionality and security by writing thorough unit tests that verify both the “happy path” and all failure cases. In this project, every requirement was turned into one or more JUnit tests. I tested valid object creation, boundary lengths, null values, duplicate IDs, and update operations. By catching invalid data early (for example, rejecting null or overly long fields), the service protects data integrity. Using clear validation in the constructors and setters, combined with automated tests, helps prevent defective or insecure states from reaching later stages of the application.

### How do I interpret user needs and incorporate them into a program?

I start by carefully reading the requirements and treating them as the single source of truth. For the Contact Service, the customer specified exact constraints (ID length, phone format, non-updatable ID, etc.). I translated each constraint directly into validation logic inside the Contact class and into corresponding unit tests. This requirements-driven approach ensured that every rule the customer stated was enforced in code and verified by tests, rather than relying on assumptions.

### How do I approach designing software?

My approach is iterative and test-informed. I first identify the core objects and their rules (the Contact entity), then design the service layer that manages those objects (ContactService). I implement validation as close to the data as possible (constructors and setters) and keep the service focused on collection management (add, delete, update). Throughout development I write unit tests alongside the production code. This helps me catch design issues early, keep classes focused, and confirm that the implementation continues to meet the original requirements as the code evolves.

---

## Technologies Used
- Java
- JUnit
- In-memory data structure (HashMap)

## Author
Vincent D. Esposito  
CS-320 – Software Testing, Automation, and Quality Assurance
