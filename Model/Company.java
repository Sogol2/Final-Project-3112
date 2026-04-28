package Model;
public class Company extends User {
    private String companyName;

    public Company(String name, String userID, String password, String companyName) {
        super(userID, name, password);
        this.companyName = companyName;
    }

    public void postStatus(Application app, ApplicationStatus status, Student student) {
        app.updateStatus(status);

        //notify the student about the status update
        student.recieveUpdate("Your application for " + companyName + " has been updated to: " + status);
    }
}