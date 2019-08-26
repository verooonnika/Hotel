package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.Booking;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.message.MessageHandler;
import by.epam.khlopava.hotel.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.khlopava.hotel.constant.PageConstant.BOOKINGS_PAGE;
import static by.epam.khlopava.hotel.constant.PageConstant.USER_MAIN_PAGE;
import static by.epam.khlopava.hotel.constant.RequestConstant.*;

public class CancelBookingCommand implements Command {

    private static Logger log = LogManager.getLogger();

    private CommonService commonService;

    public CancelBookingCommand(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        int bookingId = Integer.parseInt(requestContent.getRequestParameter(BOOKING_ID)[0]);
        System.out.println(bookingId);
        try {
            Booking booking = commonService.cancelBooking(bookingId);
        } catch (ServiceException e) {
            log.error("Error while canceling booking", e);
            return new DefaultCommand().execute(requestContent);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(SUCCESSFUL_BOOKING, MessageHandler.getMessage("message.successful_canceling", (String) requestContent.getSessionAttribute(LOCALE)));
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, BOOKINGS_PAGE, attributes);
        log.debug("Booking was successfully canceled");
        return commandResult;
    }
}
