package ru.Lab6.server.commands;
import ru.Lab6.common.arguments.ArgumentDistanceImpl;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.dao.Dao;

import java.util.Vector;
import java.util.List;

/**
 * Класс команды filter_less_than_distance наследуется от интерфейса command
 * Выводит элементы, значение поля distance которых меньше заданного
 */
public class FilterLessThanDistanceCommandImpl implements Command {
    private Dao interfaceDao;
    private List<Route> routes;
    private List<Route> choose = new Vector();

    public FilterLessThanDistanceCommandImpl(Dao interfaceDao) {
        this.interfaceDao = interfaceDao;
    }

    /**
     * Обработка поступивших параметров и поиск элементов, значение поля distance которых меньше заданного
     *
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object) {
        if (!(object instanceof ArgumentDistanceImpl)) {
            return new Response(Response.Status.ERROR,  "Невозможно выполнить данную команду");
        }
        StringBuilder stringBuilder = new StringBuilder();
        ArgumentDistanceImpl argumentDistance = (ArgumentDistanceImpl) object;
        routes = interfaceDao.getAll();
        if (routes.isEmpty()) {
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому элементов, значение distance которых меньше заданного нет");
        }
        long c = routes.stream().filter(route -> route.getDistance() < argumentDistance.distance).count();
        if (c == 0) {
            return new Response(Response.Status.ERROR, "Элементов, значение поля distance которых меньше заданного, нет");
        } else {
            routes.stream().sorted((route1, route2)-> route1.getDistance().compareTo(route2.getDistance())).limit(c).
                    forEach(route -> stringBuilder.append(route.toString()).append("\n"));
            return new Response(Response.Status.SUCCESS, stringBuilder.toString());

        }
    }
}



