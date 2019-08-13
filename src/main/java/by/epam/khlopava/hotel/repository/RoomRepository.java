package by.epam.khlopava.hotel.repository;

import by.epam.khlopava.hotel.entity.Room;
import by.epam.khlopava.hotel.specification.Specification;

import java.util.List;

public class RoomRepository extends DbAbstractRepository<Room> implements Repository<Room> {


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
    public List<Room> query(Specification specification) {
        return null;
    }
}