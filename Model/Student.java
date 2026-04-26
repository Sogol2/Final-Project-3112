package Model;
import java.util.ArrayList;
import java.util.List;

import Interfaces.INotification;

public class Student extends User {
    private String firstName;
    private String lastName;
    private double GPA;
    private String major;
    private int graduationDate;
    private List<Application> applications;

    private INotification notifier;

    public Student(String name, String userID, String password, String firstName, String lastName, double GPA, String major, int graduationDate, INotification notifier) {
        super(userID, name, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.GPA = GPA;
        this.major = major;
        this.graduationDate = graduationDate;
        this.applications = new ArrayList<>();
        this.notifier = notifier;
    }

    public void addApplication(Application application) {
        applications.add(application);
        //notifier.notify("New application added for " + application.getCompanyName()); might remove
    }

    public List<Application> viewApplications() {
        return applications;
    }

    public void recieveUpdate(String message) {
        notifier.notifyMessage(message);
    }
}