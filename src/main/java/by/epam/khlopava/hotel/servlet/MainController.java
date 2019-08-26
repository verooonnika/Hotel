package by.epam.khlopava.hotel.servlet;

import by.epam.khlopava.hotel.command.*;
import by.epam.khlopava.hotel.connection.ConnectionPool;
import by.epam.khlopava.hotel.exception.ConnectionPoolException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class MainController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestContent requestcontent = RequestContent.createContent(request);
        CommandFactory commandFactory = CommandFactory.getInstance();

        Command command = commandFactory.defineCommand(requestcontent);
        CommandResult commandResult = command.execute(requestcontent);

        commandResult.getAttributes().forEach(request::setAttribute);
        commandResult.getSessionAttributes().forEach(request.getSession()::setAttribute);

        if (command.getClass().isAssignableFrom(LogoutCommand.class)) {
            request.getSession().invalidate(); //session need to be invalidated if command is logout
        }

        if (commandResult.getResponseType() == CommandResult.ResponseType.FORWARD) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(commandResult.getPage());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getServletContext().getContextPath() + commandResult.getPage());
        }
    }

    @Override
    public void destroy(){
        try {
            ConnectionPool.getInstance().closePool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Cant destroy pool connection");
        }
    }
}
