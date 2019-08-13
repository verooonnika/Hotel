package by.epam.khlopava.hotel.specification;

import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserLoginSpecification implements Specification {

    @Language("SQL")
    private static final String FIND_USER_BY_LOGIN_PASSWORD = "SELECT login, password, email, first_name, last_name," +
            " phone_number, country, birthday, isAdmin FROM user where login = ? and password = ?;";

    private String login;
    private String password;

    public UserLoginSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_PASSWORD);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }
}
