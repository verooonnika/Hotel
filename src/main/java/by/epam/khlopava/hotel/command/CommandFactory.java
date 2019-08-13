package by.epam.khlopava.hotel.command;

import static by.epam.khlopava.hotel.command.constant.RequestConstant.*;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command defineCommand(RequestContent content) {
        String commandName = content.getRequestParameters().containsKey(COMMAND) ?
                content.getRequestParameter(COMMAND)[0] : ERROR;
        try {
            return CommandType.valueOf(commandName.toUpperCase().replaceAll("[-]", "_")).getCommand();
        } catch (IllegalArgumentException e) {
            return new DefaultCommand();
        }
    }
}
