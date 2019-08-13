package by.epam.khlopava.hotel.service;

import by.epam.khlopava.hotel.entity.Booking;
import by.epam.khlopava.hotel.entity.Room;
import by.epam.khlopava.hotel.entity.User;
import by.epam.khlopava.hotel.exception.RepositoryException;
import by.epam.khlopava.hotel.exception.ServiceException;
import by.epam.khlopava.hotel.repository.BookingRepository;
import by.epam.khlopava.hotel.repository.UserRepository;
import by.epam.khlopava.hotel.specification.Specification;
import by.epam.khlopava.hotel.specification.UserLoginSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class UserService {

    private static Logger log = LogManager.getLogger();

    public List<User> login(String login, String password) throws ServiceException {
        List<User> users;
        Specification specification = new UserLoginSpecification(login, password);
        UserRepository repository = new UserRepository();
        try {
            users = repository.query(specification);
        } catch (RepositoryException e) {
            log.error("Error while login");
            throw new ServiceException(e);
        }
        return users;
    }

    public User register(String login, String password, String email, String firstName,
                         String lastName, String phoneNumber, String country, LocalDate birthday, boolean isAdmin)
            throws ServiceException {

        UserRepository repository = new UserRepository();
        User user = new User(login, password, firstName, lastName, email, country, phoneNumber, birthday, isAdmin);
        // was set
        try {
            repository.add(user);
        } catch (RepositoryException e) {
            log.error("Error while registering new user");
            throw new ServiceException(e);
        }
        return user;
    }

    public Booking addBooking(String userLogin, LocalDate arrival, LocalDate departure, Room room, int guestsNumber, String guestName) throws ServiceException {
        BookingRepository repository = new BookingRepository();
        Booking booking = new Booking(userLogin, arrival, departure, room, guestsNumber, guestName);
        try {
            repository.add(booking);
        } catch (RepositoryException e) {
            //todo log
            throw new ServiceException(e);
        }
        return booking;
    }
}
