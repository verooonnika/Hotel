package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.User;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.message.MessageHandler;
import by.epam.khlopava.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.khlopava.hotel.command.constant.PageConstant.*;
import static by.epam.khlopava.hotel.command.constant.RequestConstant.*;

public class LoginCommand implements Command {

    private static Logger log = LogManager.getLogger();

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        User user;
        CommandResult commandResult;
        String login = requestContent.getRequestParameter(LOGIN)[0];
        String password = requestContent.getRequestParameter(PASSWORD)[0];
        Map<String, Object> requestAttributes = new HashMap<>();
        Map<String, Object> users = new HashMap<>();
        try {
            if (userService.login(login, password).isEmpty()) {
                users.put(ERROR_LOGIN_PASS, MessageHandler.getMessage("warning.login_password"));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, LOGIN_PAGE, requestAttributes, users);
                log.debug("Login error: can't find user");
            } else {
                user = userService.login(login, password).get(0);
                if (!user.isAdmin()) {
                    users.put(USER, user);
                    commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, USER_MAIN_PAGE, requestAttributes, users);
                    log.debug(user + " logged in as user");
                } else {
                    user = userService.login(login, password).get(0);
                    users.put(USER, user);
                    commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, ADMIN_MAIN_PAGE, requestAttributes, users);
                    log.debug(user + " logged in as admin");
                }
            }
            return commandResult;
        } catch (ServiceException e) {
            log.debug("Unable to log in");
            return new DefaultCommand().execute(requestContent);
        }
    }
}
