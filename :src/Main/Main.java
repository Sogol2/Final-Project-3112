package Main;

import Factory.ApplicationFactory;
import Interfaces.IApplication;
import Interfaces.INotification;
import Interfaces.ISearchStrategy;
import Manager.ApplicationManager;
import Model.Application;
import Model.ApplicationStatus;
import Model.Company;
import Model.Student;
import Model.User;
import Notification.ConsoleNotification;
import Strategy.CompanySearchStrategy;
import Strategy.StatusSearchStrategy;
import Strategy.DateSearchStrategy;
import java.util.List;
import java.util.Scanner;


public class Main {
    private final ApplicationManager manager;
    private final Scanner scanner;

    private final Student student;
    private final Company company;
    private User currentUser;

    public Main() {
        manager = ApplicationManager.getInstance();
        scanner = new Scanner(System.in);

        INotification notifier = new ConsoleNotification();

        student = new Student(
            "Student User",
            "student1",
            "1234",
            "Test",
            "Student",
            3.8,
            "Computer Science",
            2026,
            notifier
    );
    

        company = new Company(
                "Company User",
                "company1",
                "1234",
                "Google"
        );

        currentUser = null;
    }

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                running = showLoginMenu();
            } else if (currentUser instanceof Student) {
                showStudentMenu();
            } else if (currentUser instanceof Company) {
                showCompanyMenu();
            }
        }
    }

    private boolean showLoginMenu() {
        System.out.println();
        System.out.println("==== Job Application Tracker ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Company");
        System.out.println("3. Exit");

        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1":
                loginStudent();
                return true;
            case "2":
                loginCompany();
                return true;
            case "3":
                System.out.println("Exiting application. Goodbye!");
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    private void loginStudent() {
        String username = prompt("Enter student user ID: ");
        String password = prompt("Enter password: ");

        if (student.login(username, password)) {
            currentUser = student;
            System.out.println("Student login successful.");
            System.out.println("Welcome, " + student.getProfileSummary());
            student.displayPendingNotifications();
        } else {
            System.out.println("Invalid student login.");
        }
    }

    private void loginCompany() {
        String username = prompt("Enter company user ID: ");
        String password = prompt("Enter password: ");

        if (company.login(username, password)) {
            currentUser = company;
            System.out.println("Company login successful.");
        } else {
            System.out.println("Invalid company login.");
        }
    }

    private void showStudentMenu() {
        System.out.println();
        System.out.println("==== Student Menu ====");
        System.out.println("1. Add application");
        System.out.println("2. View all applications");
        System.out.println("3. Delete application");
        System.out.println("4. View summary");
        System.out.println("5. Search applications");
        System.out.println("6. View profile");
        System.out.println("7. Logout");

        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1":
                addApplication();
                break;
            case "2":
                viewAllApplications();
                break;
            case "3":
                deleteApplication();
                break;
            case "4":
                manager.displaySummary();
                break;
            case "5":
                searchApplications();
                break;
            case "6":
                student.displayProfile();
                break;
            case "7":
                logoutCurrentUser();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void showCompanyMenu() {
        System.out.println();
        System.out.println("==== Company Menu ====");
        System.out.println("1. View all applications");
        System.out.println("2. Update application status");
        System.out.println("3. Search applications");
        System.out.println("4. Logout");

        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1":
                viewAllApplications();
                break;
            case "2":
                updateApplicationStatus();
                break;
            case "3":
                searchApplications();
                break;
            case "4":
                logoutCurrentUser();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void logoutCurrentUser() {
        currentUser.logout();
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    private void addApplication() {
        System.out.println();
        System.out.println("Application type:");
        System.out.println("1. Full-time");
        System.out.println("2. Internship");

        String choice = prompt("Choose a type: ");
        String id = prompt("Enter application id: ");
        String companyName = prompt("Enter company name: ");
        String position = prompt("Enter position: ");
        String jobType = prompt("Enter job type: ");
        String startDate = prompt("Enter start date: ");
        ApplicationStatus status = ApplicationStatus.APPLIED;

        Application app = null;

        if (choice.equals("1")) {
            int salary = promptInt("Enter salary: ");
            String experience = prompt("Enter experience: ");

            app = ApplicationFactory.createApplication(
                    "fulltime",
                    id,
                    companyName,
                    position,
                    jobType,
                    startDate,
                    status,
                    salary,
                    experience,
                    0,
                    0,
                    ""
            );

            System.out.println("Full-time application added.");
        } else if (choice.equals("2")) {
            int academicCredit = promptInt("Enter academic credit: ");
            int recommendedGPA = promptInt("Enter recommended GPA: ");
            String recommendedMajor = prompt("Enter recommended major: ");

            app = ApplicationFactory.createApplication(
                    "internship",
                    id,
                    companyName,
                    position,
                    jobType,
                    startDate,
                    status,
                    0,
                    "",
                    academicCredit,
                    recommendedGPA,
                    recommendedMajor
            );

            System.out.println("Internship application added.");
        } else {
            System.out.println("Invalid application type.");
        }

        if (app != null) {
            manager.addApplication(app);
            student.receiveUpdate("Application " + id + " was added successfully.");
        }
    }

    private void viewAllApplications() {
        List<IApplication> applications = manager.getAllApplications();

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (IApplication application : applications) {
            System.out.println(application);
        }
    }

    private void updateApplicationStatus() {
        String id = prompt("Enter application id: ");
        IApplication foundApp = manager.getApplication(id);

        if (foundApp != null) {
            ApplicationStatus status = promptStatus();
            manager.updateApplicationStatus(id, status);
            student.receiveUpdate("Your application for " + foundApp.getCompanyName()
                    + " has been updated to: " + status);
            System.out.println("Application status updated.");
        } else {
            System.out.println("Application not found.");
        }
    }

    private void deleteApplication() {
        String id = prompt("Enter application id: ");
        manager.removeApplication(id);
        System.out.println("Application deleted.");
    }

    private void searchApplications() {
        System.out.println("\nSearch by:");
        System.out.println("1. Company");
        System.out.println("2. Status");
        System.out.println("3. Start Date");

        String choice = prompt("Choose: ");
        String query = prompt("Enter search query: ");

        ISearchStrategy strategy;

        if (choice.equals("1")) {
            strategy = new CompanySearchStrategy();
        } else if (choice.equals("2")) {
            strategy = new StatusSearchStrategy();
        } else if (choice.equals("3")) {
            strategy = new DateSearchStrategy();
        } else {
            System.out.println("Invalid option.");
            return;
        }

        List<IApplication> results = manager.search(strategy, query);

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (IApplication app : results) {
                System.out.println(app);
            }
        }
    }

    private ApplicationStatus promptStatus() {
        System.out.println("Choose status:");
        System.out.println("1. APPLIED");
        System.out.println("2. INTERVIEW");
        System.out.println("3. OFFERED");
        System.out.println("4. REJECTED");
        System.out.println("5. ACCEPTED");

        int choice = promptInt("Enter choice: ");

        switch (choice) {
            case 1:
                return ApplicationStatus.APPLIED;
            case 2:
                return ApplicationStatus.INTERVIEW;
            case 3:
                return ApplicationStatus.OFFERED;
            case 4:
                return ApplicationStatus.REJECTED;
            case 5:
                return ApplicationStatus.ACCEPTED;
            default:
                System.out.println("Invalid choice. Defaulting to APPLIED.");
                return ApplicationStatus.APPLIED;
        }
    }

    private int promptInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(prompt(message));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
