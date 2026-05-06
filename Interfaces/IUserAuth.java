package Interfaces;
public interface IUserAuth {
    boolean login(String username, String password);
    void logout();
}