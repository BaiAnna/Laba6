package ru.Lab6.server.commands;

import ru.Lab6.common.response.Response;

public interface Command {

    Response execute(Object object ) ;

}
