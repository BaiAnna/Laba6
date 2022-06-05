package ru.Lab6.client.client;

import ru.Lab6.common.request.Request;
import ru.Lab6.common.response.Response;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface Client {
    void sendRequest(SocketChannel socketChannel, Request request);
    Response receiveResponse(SocketChannel socketChannel);
    Response runClient(Request request, SocketChannel socketChannel) throws IOException;

}
