package ru.Lab6.server.server;

import ru.Lab6.common.builder.RouteBuilderImpl;
import ru.Lab6.server.buildCollection.BuildCollectionRouteVectorImpl;
import ru.Lab6.server.file.FileReadeImpl;
import ru.Lab6.server.file.FileWriteImpl;
import ru.Lab6.server.commands.*;
import ru.Lab6.server.dao.*;
import ru.Lab6.server.informationCollection.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.Lab6.common.collectionClass.*;
public class ServerApp {
    public static void main(String args[]) throws Exception {
        final Logger logger=Logger.getLogger(ServerImpl.class.getName());
        ServerSocket serverSocket = null;
        Route route = new Route();
        Executor executor = new Executor();
        FileWriteImpl fileWrite = new FileWriteImpl(args[0]);
        InformationCollectionRouteImpl informationCollectionRoute = new InformationCollectionRouteImpl("Vector", route.getLocalDate(), 0);
        VectorDaoImpl vectorDao = new VectorDaoImpl(informationCollectionRoute);
        BuildCollectionRouteVectorImpl buildCollectionRouteVector = new BuildCollectionRouteVectorImpl(informationCollectionRoute, vectorDao);
        FileReadeImpl fileReader = new FileReadeImpl(args[0], buildCollectionRouteVector);
        try{
            fileReader.read();
        } catch(Exception e){
            System.out.println("Что-то пошло не так");
            throw new Exception();
        }
        List<Route> routes=vectorDao.getAll();
        long id=0 ;
        for (Route r: routes){
            if (r.getId()>id){
                id=r.getId();
            }
        }
        RouteBuilderImpl routeBuilder=new RouteBuilderImpl(id+1);
        Map<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommandImpl());
        commands.put("add", new AddCommandImpl(vectorDao));
        commands.put("info", new InfoCommandImpl(vectorDao, informationCollectionRoute));
        commands.put("show", new ShowCommandImpl(vectorDao));
        commands.put("sort", new SortCommandImpl(vectorDao));
        commands.put("save", new SaveCommandImpl(fileWrite,vectorDao, args[0]));
        commands.put("clear", new ClearCommandImpl(vectorDao));
        commands.put("add_if_min", new AddIfMinCommandImpl(vectorDao));
        commands.put("remove_first", new RemoveFirstCommandImpl(vectorDao));
        commands.put("remove_by_id", new RemoveByIdCommandImpl(vectorDao));
        commands.put("min_by_id", new MinByIdCommandImpl(vectorDao));
        commands.put("filter_contains_name", new FilterContainsNameCommandImpl(vectorDao));
        commands.put("filter_less_than_distance", new FilterLessThanDistanceCommandImpl(vectorDao));
        commands.put("execute_script", new ExecuteScriptCommandImpl(executor));
        ServerImpl server = new ServerImpl(executor);
        executor.setCommands(commands);
        System.out.println(vectorDao.get(Long.parseLong("2")));

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(65100));
            server.runServer(serverSocket);
        } catch (IOException e) {
            logger.log(Level.INFO, "The connection isn't installed");
            throw new RuntimeException(e);
        }

    }
}

