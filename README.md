# Password Manager (Java Swing + SQLite)

## Overview

This is a Java-based desktop Password Manager built using Swing for the graphical user interface and SQLite for local data persistence. The system provides a simple but functional password storage solution with a master key authentication layer, password generation utility, and basic password strength validation.

It is designed as a foundational project to demonstrate core concepts in:
- GUI application development (Java Swing)
- Database integration (SQLite via JDBC)
- Basic security principles (password rules and secure random generation)
- CRUD operations in a local database environment

---

## Author

- Name: Alexander M. Sapo  
- GitHub: https://github.com/conan0s4  
- Email: alexandermsapo@gmail.com  
- Course: BSIT (1st Year Finals Project)

---

## System Features

### Authentication System
- Master key setup on first launch
- Master key validation on subsequent logins
- Prevents access to password manager without correct credentials

### Password Management
- Add new credential entries (username, password, platform)
- View all saved credentials in a table format
- Delete entries using unique ID

### Password Security Tools
- Secure password generator using `SecureRandom`
- Enforced password strength rules:
  - Minimum 8 characters
  - At least 1 uppercase letter
  - At least 1 lowercase letter
  - At least 1 digit
  - At least 1 special character

### Database Features
- Automatic SQLite database creation
- Auto table initialization on startup
- Persistent storage of:
  - Master key
  - User credentials

---

## Project Structure
