package ru.Lab6.server.commands;

import ru.Lab6.common.arguments.Argument;
import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.builder.RouteBuilderImpl;
import ru.Lab6.common.collectionClass.Coordinates;
import ru.Lab6.common.collectionClass.LocationFrom;
import ru.Lab6.common.collectionClass.LocationTo;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.commands.Command;
import ru.Lab6.server.dao.Dao;

import java.util.List;
import java.util.Optional;

/**
 * Класс команды add_if_min наследуется от интерфейса command
 * Класс для добавления элемента в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */

public class AddIfMinCommandImpl implements Command {
    private Dao interfaceDao;
    private List<Route> routes;
    public AddIfMinCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;
    }

    /**
     * Обработка поступивших парметров, создание и добавление элемента в коллекцию
     * @param object-параметрв добавляемого элемента
     * @return
     */
    @Override
    public Response execute(Object object){
        if (!(object instanceof Argument)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        ArgumentElementImpl argumentElement=(ArgumentElementImpl) object;
        Coordinates coordinates=new Coordinates(argumentElement.coordinatesX, argumentElement.coordinatesY);
        LocationFrom locationFrom=new LocationFrom(argumentElement.fromX, argumentElement.fromY, argumentElement.fromZ, argumentElement.fromName);
        LocationTo locationTo=new LocationTo(argumentElement.toX, argumentElement.toY, argumentElement.toZ);
        Route route=new RouteBuilderImpl().generationId().fixName(argumentElement.name).
                fixCoordinates(coordinates).
                fixLocationFrom(locationFrom).
                fixLocationTo(locationTo).
                fixDistance(argumentElement.distance).build();
        routes=interfaceDao.getAll();
        if (routes.isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому невозможно выполнить данную команду");
        }
        Optional<Route> min=routes.stream().min(Route::compareTo);
        if(min.get().compareTo(route)<0){
            interfaceDao.create(route);
            interfaceDao.sort();
            return new Response(Response.Status.SUCCESS, "Объект успешно добавлен в коллекцию");
        }
        else{
            return new Response(Response.Status.ERROR, "значение данного элемента не меньше, чем у наименьшего элемента коллекции, поэтому его не возможно добавить в коллекцию");
        }

    }

}
