package by.epam.khlopava.hotel.repository;

import by.epam.khlopava.hotel.connection.ConnectionPool;
import by.epam.khlopava.hotel.entity.Room;
import by.epam.khlopava.hotel.entity.RoomType;
import by.epam.khlopava.hotel.exception.RepositoryException;
import by.epam.khlopava.hotel.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository extends DbAbstractRepository<Room> implements Repository<Room> {

    private static Logger log = LogManager.getLogger();


    @Override
    public boolean add(Room entity) {
        return false;
    }

    @Override
    public boolean remove(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public List<Room> query(Specification specification) throws RepositoryException {
        List<Room> rooms = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = specification.specify(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int number = resultSet.getInt(1);
                RoomType type = RoomType.valueOf(resultSet.getString(2).toUpperCase());
                int sleeps = resultSet.getInt(3);
                BigDecimal cost = BigDecimal.valueOf(resultSet.getDouble(4));
                Room room = new Room(number, type, sleeps, cost);
                rooms.add(room);
            }
        } catch (InterruptedException | SQLException e) {
            log.error("Error in execution query RoomRepository", e);
            throw new RepositoryException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return rooms;
    }
}