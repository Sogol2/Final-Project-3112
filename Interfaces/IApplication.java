package Interfaces;
import Model.ApplicationStatus;

public interface IApplication {
    String getId();
    String getPosition();
    String getJobType();
    String getStartDate();
    ApplicationStatus getStatus();
    void updateStatus(ApplicationStatus status);
    String save();
}
