package ru.Lab6.server.commands;


import ru.Lab6.common.arguments.ArgumentIdImpl;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.dao.Dao;

import java.util.List;

/**
 * Класс команды min_by_id наследуется от интерфейса command
 * Выводит любой объект из коллекции, значение поля id которого является минимальным
 */
public class MinByIdCommandImpl implements Command{
    private Dao interfaceDao;
    private List<Route> routes;
    long minId;
    String element;
    public MinByIdCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;
    }

    /**
     * Обработка поступивших параметров и поиск элемента, id которого минимально
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object){
        if (!(object instanceof ArgumentIdImpl)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        routes=interfaceDao.getAll();
        if (routes.isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому элемент с наименьшим id невозможно вывести");
        }
        else {
             return new Response(Response.Status.SUCCESS,routes.stream().min((route1, route2) -> route1.getId().compareTo(route2.getId())).get().toString());
        }
    }
}
