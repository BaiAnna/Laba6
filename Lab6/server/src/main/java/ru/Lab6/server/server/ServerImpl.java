package ru.Lab6.server.server;
import ru.Lab6.common.arguments.*;
import ru.Lab6.common.exceptions.ExitException;
import ru.Lab6.common.request.Request;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.commands.Executor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerImpl implements Server {
    private final Logger logger = Logger.getLogger(ServerImpl.class.getName());
    private Executor executor;

    public ServerImpl(Executor executor) {
        this.executor = executor;

    }

    @Override
    public Request receiveRequest(Socket socket) {
        Request request;
        try {
            byte[] buffer = new byte[4096];
            int amount = socket.getInputStream().read(buffer);
            byte[] bytes = new byte[amount];
            System.arraycopy(buffer, 0, bytes, 0, amount);
            String json = new String(bytes, StandardCharsets.UTF_8);
            request = Request.fromJson(json);
            logger.info("Request received");
        } catch (IOException e) {
            logger.log(Level.INFO, "Error when receiving the request");
            throw new RuntimeException(e);
        }
        return request;
    }

    @Override
    public void handleRequest(Socket socket, Request request, ServerSocket serverSocket) {
        Runnable threadHandlesRequest = () -> {
            Response response = null;
            if (request.commandName.equals("help")) {
                response = executor.executeHelp(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("add")) {
                response = executor.executeAddElement(request.getArgumentAs(ArgumentElementImpl.class));
            } else if (request.commandName.equals("info")) {
                response = executor.executeInfo(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("show")) {
                response = executor.executeShow(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("sort")) {
                response = executor.executeSort(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("exit")) {
                System.out.println("Серверное приложение завершило работу");
                executor.executeSave(new ArgumentEmptyImpl());
                try {
                    socket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            } else if (request.commandName.equals("clear")) {
                response = executor.executeClear(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("add_if_min")) {
                response = executor.executeAddIfMin(request.getArgumentAs(ArgumentElementImpl.class));
            } else if (request.commandName.equals("remove_first")) {
                response = executor.executeRemoveFirst(request.getArgumentAs(ArgumentEmptyImpl.class));
            } else if (request.commandName.equals("remove_by_id")) {
                response = executor.executeRemoveById(request.getArgumentAs(ArgumentIdImpl.class));
            } else if (request.commandName.equals("min_by_id")) {
                response = executor.executeMinById(request.getArgumentAs(ArgumentIdImpl.class));
            } else if (request.commandName.equals("filter_contains_name")) {
                response = executor.executeFilterContainsName(request.getArgumentAs(ArgumentNameImpl.class));
            } else if (request.commandName.equals("filter_less_than_distance")) {
                response = executor.executeFilterLessThanDistance(request.getArgumentAs(ArgumentDistanceImpl.class));
            } else if (request.commandName.equals("execute_script")) {
                response = executor.executeScript(request.getArgumentAs(ArgumentScriptImpl.class));
            } else {
                response = new Response(Response.Status.ERROR, "Такой команды не сущетвует");
            }
            sendResponse(socket, response);
        };
        new Thread(threadHandlesRequest).start();

    }

    @Override
    public void closeConnection(Socket socket) {
        try {
            socket.close();
            logger.info("Connection stopped.");
        } catch (IOException e) {
            //logger.error("Error while stopping connection.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeServer(Request request) throws ExitException {
        if (request.commandName.equals("exit")) {
            throw new ExitException("Сервер завершил работу");
        }
    }

    @Override
    public void sendResponse(Socket socket, Response response) {
        Runnable threadSendsResponse = () -> {
            try {
                socket.getOutputStream().write(response.toJson().getBytes(StandardCharsets.UTF_8));
                closeConnection(socket);
                logger.info("Response sent");
            } catch (IOException e) {
                logger.log(Level.INFO, "Error when sending response");
                throw new RuntimeException(e);

            }
        };
        new Thread(threadSendsResponse).start();
    }

    @Override
    public void runServer(ServerSocket serverSocket)  {
        while (true) {
            //System.out.println("wsd");
            Socket socket;
            /*if (Thread.interrupted()) {
                System.out.println("");
                break;
            }*/
            //try {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                break;
            }
            //Thread thread=new Thread(){
            Runnable threadRunsServer = () -> {
                Request request = receiveRequest(socket);
                /*if (request.commandName.equals("exit")){
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }*/
                handleRequest(socket, request, serverSocket);
                logger.info("The connection is established, request received, response sent");
            };
            new Thread(threadRunsServer).start();
        }

            /*}finally {
                System.out.println("wer");
                try {
                    if (Thread.interrupted()) {
                        System.out.println("eer");
                        throw new ExitException("");
                    }
                }
                catch (ExitException e){
                    System.out.println("ert");
                    break;
                }*/
        }
    }


            //} catch (IOException e) {
                //logger.log(Level.INFO, "Input or output error while waiting for connection");
                //System.out.println("клиент отключился");
                //throw new RuntimeException(e);
           // }


