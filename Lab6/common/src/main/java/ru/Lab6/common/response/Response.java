package ru.Lab6.common.response;

import com.google.gson.Gson;

public class Response {
    public final Status status;
    public final String arguments;

    public Response(Status status, String arguments) {
        this.status = status;
        this.arguments = arguments;
    }

    public static Response fromJson(String json) {
        return new Gson().fromJson(json, Response.class);
    }

    /*public <T> T getArgumentAs(Class<T> clazz) {
        return new Gson().fromJson((String) argument, clazz);
    }

    public <T> Object getArgumentAs(TypeToken<T> typeToken) {
        return new Gson().fromJson((String) argument, typeToken.getType());
    }
      */

    public String toJson() {
        return new Gson().toJson(this);
    }

    public enum Status {
        SUCCESS,
        ERROR
    }
}

