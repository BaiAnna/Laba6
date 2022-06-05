package ru.Lab6.server.server;

import ru.Lab6.common.exceptions.ExitException;
import ru.Lab6.common.request.Request;
import ru.Lab6.common.response.Response;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface Server {
    Request receiveRequest(Socket socket);
    void sendResponse(Socket socket, Response response) throws IOException;
    void runServer(ServerSocket serverSocket) throws IOException;
    void handleRequest(Socket socket, Request request, ServerSocket serverSocket);
    void closeConnection(Socket socket);
    void closeServer(Request request) throws ExitException;

}
