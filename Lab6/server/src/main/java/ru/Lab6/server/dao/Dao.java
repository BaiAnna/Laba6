package ru.Lab6.server.dao;

import java.util.List;

import ru.Lab6.common.collectionClass.Route;

import ru.Lab6.common.exceptions.ConsoleException;

public interface Dao {
    void create(Route route);

    void update(Route route) throws ConsoleException;

    void delete(Long id) throws ConsoleException, ConsoleException;

    Route get(Long id);

    List<Route> getAll();
    String getInfo();
    void deleteAll();
    void sort();


}
