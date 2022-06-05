package ru.Lab6.server.file;

import ru.Lab6.common.read.Read;
import ru.Lab6.server.buildCollection.BuildCollectionRouteVectorImpl;
import ru.Lab6.server.informationCollection.InformationCollectionRouteImpl;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Класс для чтения данных из файла
 */
public class FileReadeImpl implements Read {
    private String fileName;
    private InformationCollectionRouteImpl informationCollectionRoute;
    private BuildCollectionRouteVectorImpl buildCollection;

    public FileReadeImpl(String arg, BuildCollectionRouteVectorImpl buildCollection){
        this.buildCollection=buildCollection;
        this.fileName=arg;

    }

    /**
     * Метод читает данные из файла и добавляет в коллекцию
     * @return
     */
    @Override
    public String read() {
        String line;
        Path path= Paths.get(fileName);

        try{
            Scanner scanner = new Scanner(path);
            while(scanner.hasNext()){
                line=scanner.next();
                buildCollection.dateConversion(line);
            }
            scanner.close();
        } catch(NoSuchFileException e){
            System.out.println("Такого файла нет");
        }
        catch (IOException e) {
            System.out.println("Что-то пошло не так");
        }
        return null;
    }


}
