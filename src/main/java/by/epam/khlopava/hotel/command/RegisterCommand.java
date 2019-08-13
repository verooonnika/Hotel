package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.User;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Map;

import static by.epam.khlopava.hotel.command.constant.PageConstant.*;
import static by.epam.khlopava.hotel.command.constant.RequestConstant.*;


public class RegisterCommand implements Command {

    private static Logger log = LogManager.getLogger();

    private static DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();
    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String login = requestContent.getRequestParameter(LOGIN)[0];
        String password = requestContent.getRequestParameter(PASSWORD)[0];
        String email = requestContent.getRequestParameter(EMAIL)[0];
        String firstName = requestContent.getRequestParameter(FIRST_NAME)[0];
        String lastName = requestContent.getRequestParameter(LAST_NAME)[0];
        String phoneNumber = requestContent.getRequestParameter(PHONE_NUMBER)[0];
        String country = requestContent.getRequestParameter(COUNTRY)[0];
        LocalDate birthday = LocalDate.parse(requestContent.getRequestParameter(BIRTHDAY)[0], formatter);

        User user;
        try {
            user = userService.register(login, password, email, firstName, lastName, phoneNumber, country, birthday, false);
        } catch (ServiceException e) {
            log.error("Unable to register user");
            return new DefaultCommand().execute(requestContent);
        }
        Map<String, Object> users = new HashMap<>();
        users.put(USER, user);
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, LOGIN_PAGE, users);
        log.debug(user + "was succesfully registered");
        return commandResult;
    }
}
