package by.epam.khlopava.hotel.message;

import java.util.ResourceBundle;

public class MessageHandler {

    private static final String MESSAGES = "messages";

    public static String getMessage(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES);
        return resourceBundle.getString(key);
    }
}
