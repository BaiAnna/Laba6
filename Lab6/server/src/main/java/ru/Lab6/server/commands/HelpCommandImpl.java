package ru.Lab6.server.commands;

import ru.Lab6.common.arguments.ArgumentElementImpl;
import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.Argument;

/**
 * Класс команды help наследуется от интерфейса command
 * Выводит справку по доступным командам
 */
public class HelpCommandImpl implements Command {
    @Override
    public Response execute(Object object){
        if (!(object instanceof Argument)){
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        return new Response(Response.Status.SUCCESS, "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add : добавить новый элемент в коллекцию\n" +
                "update : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "add_if_min  : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "sort : отсортировать коллекцию в естественном порядке\n" +
                "min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным\n" +
                "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                "filter_less_than_distance distance : вывести элементы, значение поля distance которых меньше заданного");
    }
}
