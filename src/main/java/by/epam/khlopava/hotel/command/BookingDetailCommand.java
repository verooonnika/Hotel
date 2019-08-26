package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.entity.Booking;
import by.epam.khlopava.hotel.entity.User;
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
import static by.epam.khlopava.hotel.constant.RequestConstant.BOOKINGS;

public class BookingDetailCommand implements Command {

    private static Logger log = LogManager.getLogger();


    private CommonService commonService;

    public BookingDetailCommand(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        int bookingId = Integer.parseInt(requestContent.getRequestParameter(BOOKING_ID)[0]);
        Map<String, Object> requestAttributes = new HashMap<>();
        try {
            List<Booking> bookingList = commonService.findBookingById(bookingId);
            if (bookingList.isEmpty()) {
                requestAttributes.put(ERROR_FINDING_BOOKINGS, MessageHandler.getMessage("message.no_bookings", (String) requestContent.getSessionAttribute(LOCALE)));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, BOOKINGS_PAGE, requestAttributes);
            } else {
                Booking booking = bookingList.get(0);
                requestAttributes.put(BOOKING, booking);
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, BOOKING_DETAIL_PAGE, requestAttributes);
            }
            return commandResult;
        } catch (ServiceException e) {
            log.error("Error in receiving bookings");
            return new DefaultCommand().execute(requestContent);
        }
    }
}
