package by.epam.khlopava.hotel.service;

import by.epam.khlopava.hotel.entity.Booking;
import by.epam.khlopava.hotel.exception.RepositoryException;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.repository.BookingRepository;
import by.epam.khlopava.hotel.specification.BookingByUserSpecification;
import by.epam.khlopava.hotel.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommonService {

    private static Logger log = LogManager.getLogger();

    public List<Booking> findBookings(String login) throws ServiceException {
        List<Booking> bookings;
        Specification specification = new BookingByUserSpecification(login);
        BookingRepository repository = new BookingRepository();
        try {
            bookings = repository.query(specification);
        } catch (RepositoryException e) {
            log.error("Error while finding bookings");
            throw new ServiceException(e);
        }
        return bookings;


    }
}
