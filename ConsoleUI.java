import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private ApplicationManager manager;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        manager = ApplicationManager.getInstance();
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            running = handleInput();
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n~~~ Job Application Tracker ~~~");
        System.out.println("1. Add Application");
        System.out.println("2. View Applications");
        System.out.println("3. Search Applications");
        System.out.println("4. Remove Application");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private boolean handleInput() {
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                addApplication();
                return true;
            case "2":
                viewApplications();
                return true;
            case "3":
                searchApplications();
                return true;
            case "4":
                removeApplication();
                return true;
            case "5":
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("Invalid option.");
                return true;
        }
    }

    private void addApplication() {

        System.out.println("\nSelect application type:");
        System.out.println("1. Full-time");
        System.out.println("2. Internship");
        System.out.print("Choice: ");

        String typeChoice = scanner.nextLine();

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter job type: ");
        String jobType = scanner.nextLine();

        String id = String.valueOf(System.currentTimeMillis());
        ApplicationStatus status = ApplicationStatus.APPLIED;

        Application app;

        if (typeChoice.equals("1")) {
            System.out.print("Enter salary: ");
            int salary = Integer.parseInt(scanner.nextLine());

            app = ApplicationFactory.createApplication(
                    "fulltime",
                    id,
                    position,
                    jobType,
                    status,
                    salary,
                    false,
                    0.0,
                    null
            );

        } else if (typeChoice.equals("2")) {
            System.out.print("Academic credit (true/false): ");
            boolean credit = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Recommended GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine());

            System.out.print("Recommended major: ");
            String major = scanner.nextLine();

            app = ApplicationFactory.createApplication(
                    "internship",
                    id,
                    position,
                    jobType,
                    status,
                    0,
                    credit,
                    gpa,
                    major
            );

        } else {
            System.out.println("Invalid type.");
            return;
        }

        manager.addApplication(app);
        System.out.println("Application added.");
    }

    private void viewApplications() {
        List<Application> apps = manager.getApplications();

        if (apps.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (Application app : apps) {
            System.out.println(app);
        }
    }

    private void searchApplications() {

        System.out.println("\nSearch by:");
        System.out.println("1. Company");
        System.out.println("2. Status");
        System.out.println("3. Date");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();

        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        ISearchStrategy strategy;

        switch (choice) {
            case "1":
                strategy = new CompanySearchStrategy();
                break;
            case "2":
                strategy = new StatusSearchStrategy();
                break;
            case "3":
                strategy = new DateSearchStrategy();
                break;
            default:
                System.out.println("Invalid search option.");
                return;
        }

        List<Application> results = manager.search(strategy, query);

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Application app : results) {
                System.out.println(app);
            }
        }
    }

    private void removeApplication() {
        System.out.print("Enter Application ID to remove: ");
        String id = scanner.nextLine();

        Application toRemove = null;

        for (Application app : manager.getApplications()) {
            if (app.getId().equals(id)) {
                toRemove = app;
                break;
            }
        }

        if (toRemove != null) {
            manager.removeApplication(toRemove);
            System.out.println("Application removed.");
        } else {
            System.out.println("Application not found.");
        }
    }
}