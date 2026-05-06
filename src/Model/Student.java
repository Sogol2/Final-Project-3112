package src.Model;

import java.util.ArrayList;
import java.util.List;

import src.Interfaces.INotification;

public class Student extends User {
    private String firstName;
    private String lastName;
    private double GPA;
    private String major;
    private int graduationDate;
    private INotification notifier;
    private List<String> pendingNotifications;

    public Student(String name, String userID, String password, String firstName, String lastName,
                   double GPA, String major, int graduationDate, INotification notifier) {
        super(userID, name, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.GPA = GPA;
        this.major = major;
        this.graduationDate = graduationDate;
        this.notifier = notifier;
        this.pendingNotifications = new ArrayList<>();
    }

    public String getProfileSummary() {
        return firstName + " " + lastName + " | Major: " + major
                + " | GPA: " + GPA + " | Graduation Year: " + graduationDate;
    }

    public void displayProfile() {
        System.out.println("Student Profile");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Major: " + major);
        System.out.println("GPA: " + GPA);
        System.out.println("Graduation Year: " + graduationDate);
    }

    public void receiveUpdate(String message) {
        if (loggedIn) {
            notifier.notifyMessage(message);
        } else {
            pendingNotifications.add(message);
        }
    }

    public void displayPendingNotifications() {
        if (!pendingNotifications.isEmpty()) {
            System.out.println("\n--- Notifications ---");
            for (String message : pendingNotifications) {
                notifier.notifyMessage(message);
            }
            pendingNotifications.clear();
        }
    }
}
