package ru.Lab6.server.commands;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.List;

import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.Argument;
import ru.Lab6.common.collectionClass.Route;
import ru.Lab6.server.dao.Dao;
import ru.Lab6.server.file.Write;

/**
 * Класс для команды save наследуется от интерфейса command
 * Сохраняет коллекцию в файл
 */
public class SaveCommandImpl implements Command {
    private Write interfaceWrite;
    private Dao interfaceDao;
    private String fileName;
    public SaveCommandImpl(Write interfaceWrite, Dao interfaceDao, String arg){
        this.interfaceWrite=interfaceWrite;
        this.interfaceDao=interfaceDao;
        this.fileName=arg;
    }
    private List <Route> r=new Vector<>();

    /**
     * Обработка поступивших параметров и сохранение элементов коллекции в файл
     * @param object-параметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object) {
        if (!(object instanceof Argument)) {
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        r = interfaceDao.getAll();
        interfaceDao.getAll();
        try {
            new FileWriter(fileName, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (r.isEmpty()) {
            return new Response(Response.Status.ERROR, "Коллекция пуста,поэтому сохранять нечего");
        } else {
            for (Route route : r) {
                interfaceWrite.writer(route);
                }
            return new Response(Response.Status.SUCCESS, "коллекция сохранена в файл");
        }
    }

}
