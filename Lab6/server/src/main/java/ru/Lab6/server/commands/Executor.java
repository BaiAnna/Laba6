package ru.Lab6.server.commands;


import ru.Lab6.common.response.Response;
import ru.Lab6.common.arguments.*;

import java.util.Map;
/**
 * Класс Invoker
 */
public class Executor {

    private Map<String, Command> commands;

    /**
     * Метод заполняет коллекцию Map commands командыми переданными в параметрах
     *
     * @param commands-комманды для заполнения коллекции Map commands
     */
    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Метод возырвщиет команду, соответствующую данному ключю
     *
     * @param key-ключ команды
     * @return
     */
    public Command getCommands(String key) {
        Command com = commands.get(key);
        return com;

    }

    /**
     * Метод для выполнения команды help
     *
     * @return
     */
   public Response executeHelp(ArgumentEmptyImpl argumentEmpty){
       return commands.get("help").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды info
     * @return
     */
   public Response executeInfo(ArgumentEmptyImpl argumentEmpty){
        return commands.get("info").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды show
     * @return
     */
   public Response executeShow(ArgumentEmptyImpl argumentEmpty){
        return commands.get("show").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды add
     */
    public Response executeAddElement(ArgumentElementImpl argumentElement) {
        return commands.get("add").execute(argumentElement);
    }

    /**
     * Метод для выполнения команды update
     * @return
     */
    public Response executeUpdateIdElement(ArgumentUpdateImpl argumentUpdate){
        return commands.get("update").execute(argumentUpdate);
    }

    /**
     * Метод для выполнения команды clear
     */
    public Response executeClear(ArgumentEmptyImpl argumentEmpty){
        return commands.get("clear").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды save
     * @return
     */
   public Response executeSave(ArgumentEmptyImpl argumentEmpty){
       return commands.get("save").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды sort
     * @return
     */
    public Response executeSort(ArgumentEmptyImpl argumentEmpty){
        return commands.get("sort").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды remove_first
     * @return
     */
    public Response executeRemoveFirst(ArgumentEmptyImpl argumentEmpty){
        return commands.get("remove_first").execute(argumentEmpty);
    }

    /**
     * Метод для выполнения команды min_by_id
     * @return
     */
    public Response executeMinById(ArgumentIdImpl argumentId){
        return commands.get("min_by_id").execute(argumentId);
    }

    /**
     * Метод для выполнения команды remove_by_id
     * @return
     */
    public Response executeRemoveById(ArgumentIdImpl argumentId){
        return commands.get("remove_by_id").execute(argumentId);
    }

    /**
     * Метод для выполнения команды aad_if_min
     * @return
     */
    public Response executeAddIfMin(ArgumentElementImpl argumentElement){
        return commands.get("add_if_min").execute(argumentElement);
    }

    /**
     * Метод для выполнения команды filter_contains_name
     * @return
     */
    public Response executeFilterContainsName(ArgumentNameImpl argumentName){
        return commands.get("filter_contains_name").execute(argumentName);
    }

    /**
     * Метод для выполнения команды filter_less_than_distance
     * @return
     */
    public Response executeFilterLessThanDistance(ArgumentDistanceImpl argumentDistance){
        return commands.get("filter_less_than_distance").execute(argumentDistance);
    }

    /**
     * Метод для выполнения команды execute_script
     * @return
     */
    public Response executeScript(ArgumentScriptImpl argumentScript){
        return commands.get("execute_script").execute(argumentScript);
    }
}
