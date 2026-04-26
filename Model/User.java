package Model;
import Interfaces.IUserAuth;

public abstract class User implements IUserAuth {
    protected String userID;
    protected String name;
    protected String password;
    protected boolean loggedIn;

    public User(String userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.loggedIn = false;
    }

    @Override
    public boolean login(String username, String password) {
        if (this.userID.equals(username) && this.password.equals(password)) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.loggedIn = false;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }
}