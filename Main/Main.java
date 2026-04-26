package Main;
import java.util.List;
import java.util.Scanner;

import Factory.ApplicationFactory;
import Interfaces.IApplication;
import Manager.ApplicationManager;
import Model.Application;
import Model.ApplicationStatus;

public class Main {
    private final ApplicationManager manager;
    private final Scanner scanner;

    public Main() {
        manager = ApplicationManager.getInstance();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = prompt("Choose an option: ");

            switch (choice) {
                case "1":
                    addApplication();
                    break;
                case "2":
                    viewAllApplications();
                    break;
                case "3":
                    updateApplicationStatus();
                    break;
                case "4":
                    deleteApplication();
                    break;
                case "5":
                    manager.displaySummary();
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("==== Job Application Tracker ====");
        System.out.println("1. Add application");
        System.out.println("2. View all applications");
        System.out.println("3. Update application status");
        System.out.println("4. Delete application");
        System.out.println("5. View summary");
        System.out.println("6. Exit");
    }

    private void addApplication() {
        System.out.println();
        System.out.println("Application type:");
        System.out.println("1. Full-time");
        System.out.println("2. Internship");

        String choice = prompt("Choose a type: ");
        String id = prompt("Enter application id: ");
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
        ApplicationStatus status = promptStatus();

        manager.updateApplicationStatus(id, status);
        System.out.println("Application status updated.");
    }

    private void deleteApplication() {
        String id = prompt("Enter application id: ");
        manager.removeApplication(id);
        System.out.println("Application deleted.");
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
