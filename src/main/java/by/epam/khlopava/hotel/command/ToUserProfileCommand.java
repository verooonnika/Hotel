package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.constant.PageConstant;

public class ToUserProfileCommand implements Command {

    @Override
    public CommandResult execute(RequestContent requestContent) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, PageConstant.USER_PROFILE_PAGE);
    }
}
