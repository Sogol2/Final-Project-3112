package Notification;
import Interfaces.INotification;

public class ConsoleNotification implements INotification {
    
    @Override
    public void notifyMessage(String message) {
        //sending a console notification
        System.out.println("[CONSOLE] " + message);
    }
}