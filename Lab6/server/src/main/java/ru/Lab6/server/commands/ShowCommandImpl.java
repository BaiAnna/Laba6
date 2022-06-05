package ru.Lab6.server.commands;

import java.util.List;

import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.Argument;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.server.dao.Dao;

/**
 * Класс для команды show наследуется от интерфейса command
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommandImpl implements Command {
    private Dao interfaceDao;

    public ShowCommandImpl(Dao interfaceDao) {
        this.interfaceDao = interfaceDao;
    }

    /**
     * Обработка поступивших параметров и получение всех элементов коллекции
     *
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object) {
        if (!(object instanceof Argument)) {
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        List<Route> routes = interfaceDao.getAll();
        StringBuilder stringBuilder = new StringBuilder();
        if (routes.isEmpty()) {
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому элементы коллекции не могут быть выведены");
        } else {
            routes.stream().forEach(route -> stringBuilder.append(route.toString()).append("\n"));
            interfaceDao.sort();
            return new Response(Response.Status.SUCCESS, stringBuilder.toString());
        }
    }
}

