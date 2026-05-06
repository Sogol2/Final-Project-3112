# Job Application Tracker

## 1. Project Overview

Job Application Tracker is a console-based Java application that helps students manage full-time job and internship applications in one place. The system allows users to add applications, view them, update status by company, search, delete applications, and view summary information.

### Problem Statement

Students who apply to many jobs or internships can lose track of their application progress. This can lead to missed follow-ups, duplicate applications, and confusion about current status. This project solves that problem by giving students a simple program to organize and track applications.

### Features

* Login and logout functionality
* Add full-time applications
* Add internship applications
* View all applications
* Update application status
* Delete applications
* Search applications
* View application summary
* Console notification when application status is updated by company

### User Roles

* **Student:** can add, view, search, and delete applications
* **Company:** can view applications and update application status

### Application Types

* FullTimeApplication
* InternshipApplication

Both types inherit from the abstract parent class `Application`.

### Object-Oriented Concepts Used

* **Inheritance:** FullTimeApplication and InternshipApplication inherit from Application, and Student and Company inherit from User
* **Abstraction:** Application is an abstract class
* **Polymorphism:** applications are handled through IApplication, notifications are handled through INotification, and search is handled through ISearchStrategy
* **Encapsulation:** data and methods are grouped inside classes

### Interfaces

* IApplication
* IUserAuth
* INotification
* ISearchStrategy

### Default Credentials

* Student login: `student1`
* Student password: `1234`
* Company login: `company1`
* Company password: `1234`

---

# 2. Build & Run Instructions

## Requirements

* Java 17 or higher
* No external libraries or build tools are required

## Steps to Run

1. Open the project in VS Code, Eclipse, or another Java IDE
2. Make sure all files are in the correct folders with matching package names
3. Compile the project files
4. Run the `Main.java` class
5. Follow the menu in the console

### Login Information

* Student username: `student1`
* Student password: `1234`
* Company username: `company1`
* Company password: `1234`

## Sample Menus

### Login Menu

```text
==== Job Application Tracker ====
1. Login as Student
2. Login as Company
3. Exit
```

### Student Menu

```text
==== Student Menu ====
1. Add application
2. View all applications
3. Delete application
4. View summary
5. Search applications
6. View profile
7. Logout
```

### Company Menu

```text
==== Company Menu ====
1. View all applications
2. Update application status
3. Search applications
4. Logout
```

---

# 3. Required OOP Features (with File & Line References)

| OOP Feature                  | File Name                                                    | Line Numbers                                                                     | Reasoning / Purpose                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ---------------------------- | ------------------------------------------------------------ | -------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Inheritance Example 1        | `Student.java`                                               | 7                                                                                | Student extends User, allowing it to reuse shared login/logout behavior.                                                                                                                                                                                                                                                                                                                                                                                                     |
| Inheritance Example 2        | `Company.java`                                               | 3                                                                                | Company extends User, allowing company accounts to reuse shared login/logout behavior.                                                                                                                                                                                                                                                                                                                                                                                       |
| Inheritance Example 3        | `FullTimeApplication.java`                                   | 3                                                                                | FullTimeApplication extends Application to reuse common application fields and behavior.                                                                                                                                                                                                                                                                                                                                                                                     |
| Inheritance Example 4        | `InternshipApplication.java`                                 | 3                                                                                | InternshipApplication extends Application while adding internship-specific fields.                                                                                                                                                                                                                                                                                                                                                                                           |
| Interface Implementation 1   | `Application.java`                                           | 4                                                                                | Application implements IApplication, creating a shared contract for application objects.                                                                                                                                                                                                                                                                                                                                                                                     |
| Interface Implementation 2   | `User.java`                                                  | 4                                                                                | User implements IUserAuth, requiring login and logout behavior.                                                                                                                                                                                                                                                                                                                                                                                                              |
| Interface Implementation 3   | `ConsoleNotification.java`                                   | 4                                                                                | ConsoleNotification implements INotification for console-based messages.                                                                                                                                                                                                                                                                                                                                                                                                     |
| Interface Implementation 4   | `EmailNotification.java`                                     | 4                                                                                | EmailNotification implements INotification, allowing another notification type.                                                                                                                                                                                                                                                                                                                                                                                              |
| Interface Implementation 5   | `CompanySearchStrategy.java`                                 | 6                                                                                | Implements ISearchStrategy for company/position searches.                                                                                                                                                                                                                                                                                                                                                                                                                    |
| Interface Implementation 6   | `StatusSearchStrategy.java`                                  | 7                                                                                | Implements ISearchStrategy for status searches.                                                                                                                                                                                                                                                                                                                                                                                                                              |
| Interface Implementation 7   | `DateSearchStrategy.java`                                    | 7                                                                                | Implements ISearchStrategy for start date searches.                                                                                                                                                                                                                                                                                                                                                                                                                          |
| Polymorphism Example 1       | `Main.java`                                                  | 221–225                                                                          | Uses IApplication references to loop through different application objects without knowing their exact subclass.                                                                                                                                                                                                                                                                                                                                                             |
| Polymorphism Example 2       | `Main.java`                                                  | 264–270                                                                          | Uses ISearchStrategy strategy to hold different search strategy objects at runtime.                                                                                                                                                                                                                                                                                                                                                                                          |
| Polymorphism Example 3       | `FullTimeApplication.java`, `InternshipApplication.java`     | 22–32, 25–35                                                                     | Both override save() and toString() to customize behavior for each application type.                                                                                                                                                                                                                                                                                                                                                                                         |
| Access Modifiers             | `Application.java`, `User.java`, `Student.java`, `Main.java` | `Application.java` 5–10, `User.java` 6–9, `Student.java` 8–14, `Main.java` 19–24 | Uses private fields to protect data, protected fields in parent classes for subclass access, and public methods for controlled behavior. |
| Struct / Language Equivalent | `ApplicationDTO.java`                                        | 2                                                                                | Java record acts as a lightweight data structure for application data.                                                                                                                                                                                                                                                                                                                                                                                                       |
| Enum                         | `ApplicationStatus.java`                                     | 2–8                                                                              | Defines fixed application statuses: APPLIED, INTERVIEW, OFFERED, REJECTED, and ACCEPTED.                                                                                                                                                                                                                                                                                                                                                                                     |
| Data Structure Example 1     | `ApplicationManager.java`                                    | 11, 14                                                                           | Uses List<IApplication> / ArrayList to store all applications.                                                                                                                                                                                                                                                                                                                                                                                                               |
| Data Structure Example 2     | `Student.java`                                               | 14, 23                                                                           | Uses List<String> / ArrayList to store student notifications when logged out.                                                                                                                                                                                                                                                                                                                                                                                                |
| I/O                          | `Main.java`                                                  | 66–80, 116–145, 149–169, 301–313                                                 | Uses menus, Scanner, prompt(), and System.out.println() for console input/output.                                                                                                                                                                                                                                                                                                                                                                                            |

---

# 4. Design Patterns

| Pattern Name   | Category   | File Name                                                                                                                               | Line Numbers                                             | Rationale                                                                                                                                                                                                                                        |
| -------------- | ---------- | --------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Singleton      | Creational | `ApplicationManager.java`                                                                                                               | 12–24                                                    | The application needs exactly one shared list of applications that everything uses. ApplicationManager takes care of this by ensuring there is only one instance of itself, so all parts of the program work with the same application list.     |
| Factory Method | Creational | `ApplicationFactory.java`                                                                                                               | 7–31                                                     | Different application types such as full-time and internship applications have different requirements. ApplicationFactory centralizes object creation, making it easier to add new application types without changing other parts of the system. |
| Strategy       | Behavioral | `ISearchStrategy.java`, `CompanySearchStrategy.java`, `StatusSearchStrategy.java`, `DateSearchStrategy.java`, `ApplicationManager.java` | Strategy files and `ApplicationManager.java` lines 89–94 | Users can search applications in different ways such as by company, status, or start date. Each search behavior is separated into its own class, allowing new search methods to be added without modifying existing code.                        |

---

# 5. Design Decisions

The design of this Job Application Tracker is very straightforward - it consists of a simple menu-driven program running in console mode. Class `Main` takes care of all input/output operations, class `ApplicationManager` stores information about the applications in a list, while `ApplicationFactory` is used to generate appropriate instances of Application classes. Here there is one parent `Application` class for subclasses `FullTimeApplication` and `InternshipApplication`. Using inheritance pattern here helps avoid code duplication, as common variables and methods are placed in the parent class, leaving room for unique members of subclasses. Interfaces like `IApplication`, `IUserAuth`, `INotification` and `ISearchStrategy` provide separation of behavior from implementation, making code well-organized and demonstrating OOP features such as abstraction and polymorphism. The patterns implemented in this project include Singleton, Factory and Strategy. The disadvantage of such an approach lies in simplifying login functionality and notifications through usage of pre-defined accounts and console-based notifications instead of a database and some other notification mechanism.

