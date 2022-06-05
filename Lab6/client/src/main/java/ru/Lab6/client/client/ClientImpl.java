package ru.Lab6.client.client;

import ru.Lab6.common.request.Request;
import ru.Lab6.common.response.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ClientImpl implements Client {
    public SocketChannel connectToServer() throws IOException{
        SocketChannel socketChannel=null;
        //try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 65100));
       // } catch (IOException e) {
            //e.printStackTrace();
        //}
        return socketChannel;

    }
    @Override
    public void sendRequest(SocketChannel socketChannel, Request request){
        try {
            socketChannel.write(ByteBuffer.wrap(request.toJson().getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public Response receiveResponse(SocketChannel socketChannel){
        byte[] b = new byte[8990];
        int amount = 0;
        try {
            amount = socketChannel.read(ByteBuffer.wrap(b));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] responseBytes = new byte[amount];
        System.arraycopy(b, 0, responseBytes, 0, amount);
        String json = new String(responseBytes, StandardCharsets.UTF_8);
        return Response.fromJson(json);

    }
    @Override
    public Response runClient(Request request, SocketChannel socketChannel) throws IOException {
        String json = null;
        int amount = 0;
        byte[] b = new byte[8990];
        //SocketChannel socketChannel = SocketChannel.open();
        //socketChannel.connect(new InetSocketAddress("localhost", 65100));
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
        while (true) {
            selector.select();
            Iterator keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();
                keys.remove();
                if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    sendRequest(channel, request);
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if(key.isReadable()) {
                    SocketChannel socketChannel1=(SocketChannel) key.channel();
                    Response response=receiveResponse(socketChannel1);
                    socketChannel1.configureBlocking(false);
                    socketChannel1.register(selector, SelectionKey.OP_WRITE);
                    return response;
                }

            }
        }
    }
}


