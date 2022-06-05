
package ru.Lab6.server.dao;

import ru.Lab6.common.exceptions.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.server.informationCollection.*;
/**
 * Класс, реализующий интерфейс DAO, для коллекции Vector
 */
public class VectorDaoImpl implements Dao {
    ReadWriteLock lock=new ReentrantReadWriteLock();
    Lock readLock=lock.readLock();
    Lock writeLock=lock.readLock();

    private InformationCollection interfaceInformationCollection;
    public VectorDaoImpl(InformationCollection interfaceInformationCollection){
        this.interfaceInformationCollection=interfaceInformationCollection;

    }
    private final Vector<Route> collection=new Vector<>();

    /**
     * Метод добавляет элемент в коллекцию
     * @param route-элемент, добавляемый в коллекцию
     */
    @Override
    public void create(Route route){
        writeLock.lock();
        interfaceInformationCollection.setSize(interfaceInformationCollection.getSize()+1);
        collection.add(route);
        writeLock.unlock();
    }

    /**
     * Метод обновляет значения элемента
     * @param route-элемент, значения которого обновляется
     */
    @Override
    public void update(Route route) throws ConsoleException {
        writeLock.lock();
        Route existedRoute= get(route.getId());
        if (existedRoute!=null){
            existedRoute.setName(route.getName());
            existedRoute.setCoordinate(route.getCoordinate());
            existedRoute.setDistance(route.getDistance());
            existedRoute.setFrom(route.getFrom());
        }
        else{
            throw new ConsoleException("Человек с таким id не существует");
        }
        writeLock.unlock();
    }

    /**
     * Метод удаляет элемент колллекции по его id
     * @param id-удаляемого элемента
     */
    @Override
    public void delete(Long id) throws ConsoleException {
        writeLock.lock();
        Route existedRoute=collection.stream().filter(get(id)::equals).findFirst().get();
        if (existedRoute!=null){
            collection.remove(existedRoute);
            interfaceInformationCollection.setSize(interfaceInformationCollection.getSize()-1);
        }
        else {
            throw new ConsoleException("Человека с таким id не существует");
        }
        writeLock.unlock();
    }

    /**
     * Метод возвращает элемент по его id
     * @param id-возвращаемого элемента
     * @return
     */
    @Override
    public Route get(Long id){
        readLock.lock();
        try{
            return collection.stream().filter((route) -> (route.getId()==id)).findAny().orElse(null);
        } finally {
            readLock.unlock();
        }
    }
    /**
     * Метод возвращает все элементы коллекции
     */
    @Override
    public List<Route> getAll(){
        readLock.lock();
        try{
            return collection;
        }finally {
            readLock.unlock();
        }
    }

    /**
     * Метод для получения информации о коллекции
     * @return
     */
    public String getInfo(){
        readLock.lock();
        try{
            return interfaceInformationCollection.toString();
        }finally{
            readLock.unlock();
        }
    }

    /**
     * Метод удаляет все элементы коллекции
     */
    public void deleteAll(){
        writeLock.lock();
        collection.clear();
        interfaceInformationCollection.setSize(0);
        writeLock.unlock();

    }
    public void sort(){
        writeLock.lock();
        Collections.sort(getAll());
        writeLock.unlock();

    }

}



