package ru.Lab6.server.commands;


import ru.Lab6.common.arguments.Argument;
import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.dao.Dao;

/**
 * Класс команды clear наследуется от интерфейса command
 * Удаляет все элементы коллекции
 */

public class ClearCommandImpl implements Command{
    private Dao interfaceDao;
    public ClearCommandImpl(Dao interfaceDao){
        this.interfaceDao=interfaceDao;
    }

    /**
     * Обработка поступивших параметров и "очистка" коллекции
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object){
        if (!(object instanceof Argument)) {
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        if (interfaceDao.getAll().isEmpty()){
            return new Response(Response.Status.ERROR, "Коллекция пуста");
        }
        else{
            interfaceDao.deleteAll();
            return new Response(Response.Status.SUCCESS, "Коллекия очищена");
        }

    }
}
