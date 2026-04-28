package Factory;
import Model.Application;
import Model.ApplicationStatus;
import Model.FullTimeApplication;
import Model.InternshipApplication;

public class ApplicationFactory {

    public static Application createApplication(
            String type,
            String id,
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
            return new FullTimeApplication(id, position, jobType, startDate, status, salary, experience);
        } else if (type.equalsIgnoreCase("internship")) {
            return new InternshipApplication(id, position, jobType, startDate, status,
                    academicCredit, recommendedGPA, recommendedMajor);
        } else {
            return null;
        }
    }
}
