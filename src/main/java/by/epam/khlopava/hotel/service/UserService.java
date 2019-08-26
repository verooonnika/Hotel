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
            log.error("RepositoryException while login");
            throw new ServiceException(e);
        }
        return users;
    }

    public User register(String login, String password, String email, String firstName,
                         String lastName, String phoneNumber, String country, String birthday, boolean isAdmin)
            throws ServiceException {

        UserRepository repository = new UserRepository();
        User user = new User(login, password, firstName, lastName, email, country, phoneNumber, LocalDate.parse(birthday), isAdmin);
        try {
            repository.add(user);
        } catch (RepositoryException e) {
            log.error("RepositoryException while registering new user");
            throw new ServiceException(e);
        }
        return user;
    }

    public Booking addBooking(String userLogin, String arrival, String departure, Room room, int guestsNumber, String guestName) throws ServiceException {
        BookingRepository repository = new BookingRepository();
        Booking booking = new Booking(userLogin, LocalDate.parse(arrival), LocalDate.parse(departure), room, guestsNumber, guestName);
        try {
            repository.add(booking);
        } catch (RepositoryException e) {
            log.error("RepositoryException while add booking", e);
            throw new ServiceException(e);
        }
        return booking;
    }

    public User updateProfile(String login, String password, String email, String firstName,
                              String lastName, String phoneNumber, String country, String birthday, boolean isAdmin) throws ServiceException {

        UserRepository repository = new UserRepository();
        User user = new User(login, password, firstName, lastName, email, country, phoneNumber, LocalDate.parse(birthday), isAdmin);
        try {
            repository.update(user);
        } catch (RepositoryException e) {
            log.error("RepositoryException while registering new user");
            throw new ServiceException(e);
        }
        return user;

    }
}
