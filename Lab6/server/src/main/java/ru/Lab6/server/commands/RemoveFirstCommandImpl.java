package ru.Lab6.server.commands;

import ru.Lab6.common.exceptions.*;
import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.arguments.ArgumentEmptyImpl;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.exceptions.ConsoleException;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.dao.Dao;

import java.util.List;

/**
 * Класс для команды remove_first наследуется от интерфейса command
 * Удаляет первый элемент из коллекции
 */
public class RemoveFirstCommandImpl implements Command {
    private Dao interfaceDao;
    private List<Route> routes;

    public RemoveFirstCommandImpl(Dao interfaceDao) {
        this.interfaceDao = interfaceDao;
    }

    /**
     * Обработка поступивших параметров, поиск первого элемента и его удаление
     *
     * @param object-параметры для данной команды
     * @return
     */

    @Override
    public Response execute(Object object) {
        if (!(object instanceof ArgumentEmptyImpl)) {
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        routes = interfaceDao.getAll();
        if (!(routes.isEmpty())) {
            try {
                interfaceDao.delete(routes.stream().findFirst().get().getId());
                return new Response(Response.Status.SUCCESS, "Первый элемент коллекции успешно удален");
            } catch (ConsoleException exception) {
                return new Response(Response.Status.ERROR, "Что-то поло не так...");
            }
        } else {
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому невозможно выполнить данную команду");
        }
    }
}

