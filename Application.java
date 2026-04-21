public abstract class Application implements IApplication {
    protected String id;
    protected String position;
    protected String jobType;
    protected String startDate;
    protected ApplicationStatus status;

    public Application(String id, String position, String jobType, String startDate, ApplicationStatus status) {
        this.id = id;
        this.position = position;
        this.jobType = jobType;
        this.startDate = startDate;
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getJobType() {
        return jobType;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public void updateStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String save() {
        return id + "," + position + "," + jobType + "," + startDate + "," + status;
    }
}
