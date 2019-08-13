package by.epam.khlopava.hotel.command;

public class DefaultCommand implements Command {

    @Override
    public CommandResult execute(RequestContent requestContent) {
        return new CommandResult() ;
    }
}
