package by.epam.khlopava.hotel.repository;

import by.epam.khlopava.hotel.entity.Entity;
import by.epam.khlopava.hotel.exception.RepositoryException;
import by.epam.khlopava.hotel.specification.Specification;

import java.util.List;

public interface Repository< T extends Entity> {

    boolean add(T entity) throws RepositoryException;
    boolean remove(T entity) throws RepositoryException;
    boolean update(T entity) throws RepositoryException;

    List<T> query(Specification specification) throws RepositoryException;

}
