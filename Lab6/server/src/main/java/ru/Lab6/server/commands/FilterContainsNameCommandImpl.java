package ru.Lab6.server.commands;

import ru.Lab6.common.arguments.ArgumentNameImpl;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.dao.Dao;

import java.util.Vector;
import java.util.List;

/**
 * Класс команды filter_contains_name наследуется от интерфейса command
 * Выводит элементы, значение поля name которых содержит заданную подстроку
 */

public class FilterContainsNameCommandImpl implements Command{
    private Dao interfaceDao;
    public FilterContainsNameCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;
    }
    private List<Route> routes;
    private List<Route> choose= new Vector();

    /**
     * Обработка поступивших данных и поиск элементов, name которых содержит заданную подстроку
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object) {
        StringBuilder stringBuilder=new StringBuilder();
        if(!(object instanceof ArgumentNameImpl)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        routes = interfaceDao.getAll();
        interfaceDao.sort();
        if (routes.isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому элементов, содержащих данную пожстроку, нет");
        }
        ArgumentNameImpl argumentName = (ArgumentNameImpl) object;
        long c =routes.stream().filter(route -> route.getName().contains(argumentName.name)).count();
        if (c!=0){
            routes.stream().filter(route -> route.getName().contains(argumentName.name)).
                    forEach(route -> stringBuilder.append(route.toString()).append("\n"));
            return new Response(Response.Status.SUCCESS, stringBuilder.toString());
        }
        else{
            return new Response(Response.Status.ERROR, "Элементов, содержащих заданную подстроку, нет") ;
        }
    }

}
