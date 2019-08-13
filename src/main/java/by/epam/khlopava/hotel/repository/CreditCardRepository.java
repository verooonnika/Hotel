package by.epam.khlopava.hotel.repository;

import by.epam.khlopava.hotel.entity.CreditCard;
import by.epam.khlopava.hotel.specification.Specification;

import java.util.List;

public class CreditCardRepository implements Repository<CreditCard> {

    @Override
    public boolean add(CreditCard entity) {
        return false;
    }

    @Override
    public boolean remove(CreditCard entity) {
        return false;
    }

    @Override
    public boolean update(CreditCard entity) {
        return false;
    }

    @Override
    public List<CreditCard> query(Specification specification) {
        return null;
    }
}
