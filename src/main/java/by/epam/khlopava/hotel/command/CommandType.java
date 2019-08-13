package by.epam.khlopava.hotel.command;

import by.epam.khlopava.hotel.service.CommonService;
import by.epam.khlopava.hotel.service.UserService;

public enum CommandType {
    LOGIN (new LoginCommand(new UserService())),
    REGISTER(new RegisterCommand(new UserService())),
    //LOGOUT (new LogoutCommand())
    TO_REGISTRATION(new ToRegistrationCommand()),
    TO_LOGIN(new ToLoginCommand()),
    BOOKINGS(new ShowBookingsCommand(new CommonService())),
    BOOK_ROOM(new BookRoomCommand(new UserService()))
    ;

   private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
