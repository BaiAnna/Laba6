package ru.Lab6.server.buildCollection;
import ru.Lab6.common.builder.RouteBuilderImpl;
import ru.Lab6.server.dao.*;
import ru.Lab6.common.collectionClass.*;
import ru.Lab6.server.informationCollection.*;

public class BuildCollectionRouteVectorImpl implements BuildCollection {
    private InformationCollection interfaceInformationCollection;
    private Dao vectorDao;

    public BuildCollectionRouteVectorImpl(InformationCollection interfaceInformationCollection, Dao vectorDao) {
        this.interfaceInformationCollection = interfaceInformationCollection;
        this.vectorDao = vectorDao;
    }

    public void dateConversion(String line) {
        String[] fields = line.split(",");
        try {
            Route route = new RouteBuilderImpl().fixId(Long.parseLong(fields[0])).fixName(fields[1])
                    .fixCoordinates(new Coordinates(Double.parseDouble(fields[2]), Float.parseFloat(fields[3])))
                    .fixCreationDate(fields[4])
                    .fixLocationFrom(new LocationFrom(Float.parseFloat(fields[5]), Long.parseLong(fields[6]), Float.parseFloat(fields[7]), fields[8]))
                    .fixLocationTo(new LocationTo(Integer.parseInt(fields[9]), Integer.parseInt(fields[10]), Long.parseLong(fields[11])))
                    .fixDistance(Long.parseLong(fields[12])).build();
            vectorDao.create(route);
        } catch (NumberFormatException e) {
            System.out.println("Данные в файлы не корректны");
            throw new NumberFormatException();
        }
    }
}

