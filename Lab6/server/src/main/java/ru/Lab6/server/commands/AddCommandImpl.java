package ru.Lab6.server.commands;

import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.builder.RouteBuilderImpl;
import ru.Lab6.common.collectionClass.Coordinates;
import ru.Lab6.common.collectionClass.LocationFrom;
import ru.Lab6.common.collectionClass.LocationTo;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.server.dao.Dao;

/**
 * Класс команды add наследуется от интерфейса command
 * Добавляет новый элемент в коллекцию
 */

public class AddCommandImpl implements Command {
    private Dao interfaceDao;
    public AddCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;

    }

    /**
     *Обработка поступивших параметров, создние и добавление элемента в коллекцию
     * @param object-параметры добавляемого элемента
     * @return
     */
    @Override
    public Response execute(Object object)  {
        if (!(object instanceof ArgumentElementImpl)){
            return new Response(Response.Status.ERROR,"Невозможно выполнить данную команду");
        }
        ArgumentElementImpl argumentElement=(ArgumentElementImpl) object;
        Coordinates coordinates=new Coordinates(argumentElement.coordinatesX, argumentElement.coordinatesY);
        LocationFrom locationFrom=new LocationFrom( argumentElement.fromX, argumentElement.fromY, argumentElement.fromZ, argumentElement.fromName);
        LocationTo locationTo=new LocationTo(argumentElement.toX, argumentElement.toY, argumentElement.toZ);
        Route route=new RouteBuilderImpl().generationId().
                fixName(argumentElement.name).fixCoordinates(coordinates).
                fixLocationFrom(locationFrom).fixLocationTo(locationTo).
                fixDistance(argumentElement.distance).build();
        interfaceDao.create(route);
        interfaceDao.sort();

        return new Response(Response.Status.SUCCESS, "объект добавлен в коллекцию");


    }
}
