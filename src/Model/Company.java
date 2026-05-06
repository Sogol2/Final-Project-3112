package src.Model;

public class Company extends User {
    private String companyName;

    public Company(String name, String userID, String password, String companyName) {
        super(userID, name, password);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}