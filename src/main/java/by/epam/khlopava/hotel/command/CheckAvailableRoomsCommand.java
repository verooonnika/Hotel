package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.Room;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.message.MessageHandler;
import by.epam.khlopava.hotel.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.khlopava.hotel.constant.PageConstant.*;
import static by.epam.khlopava.hotel.constant.RequestConstant.*;

public class CheckAvailableRoomsCommand implements Command {

    private static Logger log = LogManager.getLogger();

    private CommonService commonService;

    public CheckAvailableRoomsCommand(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String arrivalDate = requestContent.getRequestParameter(ARRIVAL)[0];
        String departureDate = requestContent.getRequestParameter(DEPARTURE)[0];
        try {
            Map<String, Object> requestAttributes = new HashMap<>();
            Map<String, Object> sessionAttributes = new HashMap<>();
            List<Room> roomList = commonService.findAvailableRooms(arrivalDate, departureDate);
            if (roomList.isEmpty()) {
                requestAttributes.put(NO_AVAILABLE_ROOMS, MessageHandler.getMessage("message.no_available_rooms", (String) requestContent.getSessionAttribute(LOCALE)));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, USER_MAIN_PAGE, requestAttributes);
            } else {
                requestAttributes.put(ROOMS, roomList);
                sessionAttributes.put(ARRIVAL, arrivalDate);
                sessionAttributes.put(DEPARTURE, departureDate);
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, AVAILABLE_ROOMS, requestAttributes, sessionAttributes);
            }
            return commandResult;
        } catch (ServiceException e) {
            log.error("Error while receiving rooms", e);
            return new DefaultCommand().execute(requestContent);
        }
    }
}
