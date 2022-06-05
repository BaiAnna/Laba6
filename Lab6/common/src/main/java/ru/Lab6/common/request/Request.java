package ru.Lab6.common.request;

import com.google.gson.Gson;

public class Request {
    public final String commandName;
    public final Object argument;

    public Request(String commandName, Object argument){
        this.commandName = commandName;
        this.argument = new Gson().toJson(argument);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getCommandName(){
        return commandName;
    }

    //public Argument getArguments(){
        //return argument;
    //}
    public static Request fromJson(String json){
        return new Gson().fromJson(json, Request.class);
    }

    public <T> T getArgumentAs(Class<T> clazz){
        return new Gson().fromJson((String) argument, clazz);
    }

    /*public <T> Object getArgumentAs (TypeToken<T> typeToken){
        return new Gson().fromJson((String) arguments, typeToken.getType());
    }*/

}
