import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {
    private static ApplicationManager instance;
    private List<IApplication> applications;

    private ApplicationManager() {
        applications = new ArrayList<>();
    }

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public void addApplication(IApplication application) {
        if (application != null) {
            applications.add(application);
        }
    }

    public void removeApplication(String id) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getId().equals(id)) {
                applications.remove(i);
                break;
            }
        }
    }

    public IApplication getApplication(String id) {
        for (IApplication application : applications) {
            if (application.getId().equals(id)) {
                return application;
            }
        }
        return null;
    }

    public void updateApplicationStatus(String id, ApplicationStatus status) {
        IApplication application = getApplication(id);
        if (application != null) {
            application.updateStatus(status);
        }
    }

    public List<IApplication> getAllApplications() {
        return applications;
    }

    public void displaySummary() {
        int applied = 0;
        int interview = 0;
        int offered = 0;
        int rejected = 0;
        int accepted = 0;

        for (IApplication application : applications) {
            if (application.getStatus() == ApplicationStatus.APPLIED) {
                applied++;
            } else if (application.getStatus() == ApplicationStatus.INTERVIEW) {
                interview++;
            } else if (application.getStatus() == ApplicationStatus.OFFERED) {
                offered++;
            } else if (application.getStatus() == ApplicationStatus.REJECTED) {
                rejected++;
            } else if (application.getStatus() == ApplicationStatus.ACCEPTED) {
                accepted++;
            }
        }

        System.out.println("Applied: " + applied);
        System.out.println("Interview: " + interview);
        System.out.println("Offered: " + offered);
        System.out.println("Rejected: " + rejected);
        System.out.println("Accepted: " + accepted);
    }

    public List<IApplication> search(ISearchStrategy strategy, String query) {
        if (strategy == null || query == null) {
            return new ArrayList<>();
        }
        return strategy.search(applications, query);
    }
}
