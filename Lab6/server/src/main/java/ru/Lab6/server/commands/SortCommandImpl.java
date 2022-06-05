package ru.Lab6.server.commands;
import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.Argument;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.server.dao.Dao;

import java.util.*;

/**
 * Класс для команды sort наследуется от интерфейса command
 * Сортирует коллекцию в естественном порядке
 */
public class SortCommandImpl implements Command {
    private Dao interfaceDao;
    private List <Route> routes;
    public SortCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;}

    /**
     * Обработка поступивших параметров и сортировка элементов коллекции
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object){
        if(!(object instanceof Argument)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        routes=interfaceDao.getAll();
        if(routes.isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому ее невозожно отсортировать");
        }
        else{
            Collections.sort(routes);
            return new Response(Response.Status.SUCCESS, "Коллекция отсортирована");
        }

    }
}
