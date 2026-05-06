package src.Interfaces;
import src.Model.ApplicationStatus;

public interface IApplication {
    String getId();
    String getCompanyName();
    String getPosition();
    String getJobType();
    String getStartDate();
    ApplicationStatus getStatus();
    void updateStatus(ApplicationStatus status);
    String save();
}
