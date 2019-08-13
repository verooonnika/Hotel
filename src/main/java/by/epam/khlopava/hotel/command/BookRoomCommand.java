package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.Booking;
import by.epam.khlopava.hotel.entity.Room;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.message.MessageHandler;
import by.epam.khlopava.hotel.service.UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static by.epam.khlopava.hotel.command.constant.PageConstant.LOGIN_PAGE;
import static by.epam.khlopava.hotel.command.constant.PageConstant.USER_MAIN_PAGE;
import static by.epam.khlopava.hotel.command.constant.RequestConstant.*;

public class BookRoomCommand implements Command {

    private UserService userService;

    public BookRoomCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String login = requestContent.getSessionAttribute(LOGIN).toString();
        LocalDate arrival = LocalDate.parse(requestContent.getRequestParameter(ARRIVAL_DATE)[0]);
        LocalDate departure = LocalDate.parse(requestContent.getRequestParameter(DEPARTURE_DATE)[0]);
        Room room = new Room();
        room.setNumber(Integer.parseInt(requestContent.getRequestParameter(ROOM_NUMBER)[0]));
        int guestsNumber = Integer.parseInt(requestContent.getRequestParameter(NUMBER_OF_GUESTS)[0]);
        String guestsNames = requestContent.getRequestParameter(GUESTS_NAMES)[0];

        Booking booking;
        try {
            booking = userService.addBooking(login, arrival, departure, room, guestsNumber, guestsNames);
        } catch (ServiceException e) {
            //todo log
            return new DefaultCommand().execute(requestContent);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(SUCCESSFUL_BOOKING, MessageHandler.getMessage("message.successful_book"));
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, USER_MAIN_PAGE, attributes);
        //todo log
        return commandResult;
    }
}
