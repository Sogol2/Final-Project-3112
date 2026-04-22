public class InternshipApplication extends Application {
    private int academicCredit;
    private int recommendedGPA;
    private String recommendedMajor;

    public InternshipApplication(String id, String position, String jobType, String startDate,
                                 ApplicationStatus status, int academicCredit,
                                 int recommendedGPA, String recommendedMajor) {
        super(id, position, jobType, startDate, status);
        this.academicCredit = academicCredit;
        this.recommendedGPA = recommendedGPA;
        this.recommendedMajor = recommendedMajor;
    }

    public int getAcademicCredit() {
        return academicCredit;
    }

    public int getRecommendedGPA() {
        return recommendedGPA;
    }

    public String getRecommendedMajor() {
        return recommendedMajor;
    }

    @Override
    public String save() {
        return super.save() + "," + academicCredit + "," + recommendedGPA + "," + recommendedMajor;
    }

    @Override
    public String toString() {
        return "InternshipApplication: " + save();
    }
}
