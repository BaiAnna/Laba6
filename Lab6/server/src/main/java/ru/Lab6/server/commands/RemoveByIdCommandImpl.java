package ru.Lab6.server.commands;
import ru.Lab6.common.exceptions.*;
import ru.Lab6.common.arguments.ArgumentIdImpl;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.commands.Command;
import ru.Lab6.server.dao.Dao;

import java.util.List;
/**
 * Класс для команды remove_by_id наследуется от интерфейса command
 * Удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommandImpl implements Command {
    private List<Route> routes;
    private Dao interfaceDao;
    public RemoveByIdCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;

    }

    /**
     * Обработка поступивших параметров, поиск элемента, id которого равно задаанному, и его удаление
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object){
        if (!(object instanceof ArgumentIdImpl)){
            return new Response(Response.Status.ERROR , "Невозможно выполнить данную команду");
        }
        routes=interfaceDao.getAll();
        ArgumentIdImpl argumentId=(ArgumentIdImpl) object;
        if (routes.isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста");
        }
        long c=routes.stream().filter(route-> route.getId()== argumentId.id).count();
        if (c==1){
            try{
                interfaceDao.delete(argumentId.id);
                return new Response(Response.Status.SUCCESS, "Элемент, соответствующий даннаму id, успешно удален");
            }
            catch (ConsoleException exception){
                return new Response(Response.Status.ERROR,"Человека с таким id не существует");
            }
        }
        else{
            return new Response(Response.Status.ERROR, "Невозможно удвлить элемент, соответствующего данному id, так как в коллекции несколько элементов с таким id");
        }

    }
}
