package by.epam.khlopava.hotel.command;

@FunctionalInterface
public interface Command {
    CommandResult execute(RequestContent requestContent);
}
