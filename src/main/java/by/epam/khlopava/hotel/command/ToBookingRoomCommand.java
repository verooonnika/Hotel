package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.constant.PageConstant;

import java.util.HashMap;
import java.util.Map;

import static by.epam.khlopava.hotel.constant.RequestConstant.ROOM;

public class ToBookingRoomCommand implements Command {

    @Override
    public CommandResult execute(RequestContent requestContent) {
        String room = requestContent.getRequestParameter(ROOM)[0];
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(ROOM, room);
        return new CommandResult(CommandResult.ResponseType.FORWARD, PageConstant.BOOK_ROOM_PAGE, attributes);
    }
}
