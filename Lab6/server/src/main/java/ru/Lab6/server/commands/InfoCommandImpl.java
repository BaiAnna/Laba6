package ru.Lab6.server.commands;

import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.Argument;
import ru.Lab6.server.dao.Dao;
import ru.Lab6.server.informationCollection.InformationCollection;

/**
 * Класс команды info наследуется от интерфейса command
 * Выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommandImpl implements Command {
    private Dao interfaceDao;
    private InformationCollection interfaceInformationCollection;
    public InfoCommandImpl(Dao interfaceDao, InformationCollection interfaceInformationCollection) {
        this.interfaceInformationCollection=interfaceInformationCollection;
        this.interfaceDao = interfaceDao;
    }

    /**
     * Обработка поступивших параметров и и получение информации о коллекции
     * @param object-параметры для данной команды
     * @return
     */
@Override
    public Response execute(Object object){
        if (!(object instanceof Argument)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        if (interfaceInformationCollection.getSize()==0){
            return new Response(Response.Status.ERROR, "Коллекция пуста, поэтому информация о коллекции не может быть выведена");
        }
        else{
            return new Response(Response.Status.SUCCESS, interfaceDao.getInfo());
        }
    }
}
