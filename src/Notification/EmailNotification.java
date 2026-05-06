package src.Notification;
import src.Interfaces.INotification;

public class EmailNotification implements INotification {
    
    @Override
    public void notifyMessage(String message) {
        //sending an email notification
        System.out.println("[EMAIL] " + message);
    }
}