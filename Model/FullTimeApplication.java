package Model;

public class FullTimeApplication extends Application {
    private int salary;
    private String experience;

    public FullTimeApplication(String id, String companyName, String position, String jobType, String startDate,
                               ApplicationStatus status, int salary, String experience) {
        super(id, companyName, position, jobType, startDate, status);
        this.salary = salary;
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public String getExperience() {
        return experience;
    }

    @Override
    public String save() {
        return super.save() + "," + salary + "," + experience;
    }

    @Override
    public String toString() {
        return "FullTimeApplication: " + save();
    }
}
