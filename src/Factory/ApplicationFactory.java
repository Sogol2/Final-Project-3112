package src.Factory;
import src.Model.Application;
import src.Model.ApplicationStatus;
import src.Model.FullTimeApplication;
import src.Model.InternshipApplication;

public class ApplicationFactory {

    public static Application createApplication(
            String type,
            String id,
            String companyName,
            String position,
            String jobType,
            String startDate,
            ApplicationStatus status,
            int salary,
            String experience,
            int academicCredit,
            int recommendedGPA,
            String recommendedMajor
    ) {
        if (type.equalsIgnoreCase("fulltime")) {
            return new FullTimeApplication(id, companyName, position, jobType, startDate, status, salary, experience);
        } else if (type.equalsIgnoreCase("internship")) {
            return new InternshipApplication(id, companyName, position, jobType, startDate, status,
                    academicCredit, recommendedGPA, recommendedMajor);
        } else {
            return null;
        }
    }
}
