package ru.Lab6.server.commands;

import ru.Lab6.common.exceptions.*;
import ru.Lab6.common.arguments.*;
import ru.Lab6.common.response.Response;
import ru.Lab6.server.commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import ru.Lab6.server.commands.Executor;

/**
 * Класс команды execute_script наследуется от интерфейса command
 * Читает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommandImpl implements Command {
    private Executor executor;
    public ExecuteScriptCommandImpl(Executor executor){
        this.executor=executor;
    }

    /**
     * Обработка поступивших парметров и исполнение скрипта
     * @param object-парметры для данной команды
     * @return
     */
    @Override
    public Response execute(Object object) {
        if (!(object instanceof ArgumentScriptImpl)) {
            return new Response(Response.Status.ERROR, "Невозможно выполнить данную команду");
        }
        ArgumentScriptImpl argumentScript = (ArgumentScriptImpl) object;
        try {
            Scanner scanner = new Scanner(new File(argumentScript.fileName));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.equals("help")) {
                    executor.executeHelp(new ArgumentEmptyImpl());
                } else if (line.equals("show")) {
                    executor.executeShow(new ArgumentEmptyImpl());
                } else if (line.equals("info")) {
                    executor.executeInfo(new ArgumentEmptyImpl());
                } else if (line.equals("add")) {
                    executor.executeAddElement(new ArgumentElementImpl(readName(scanner), readCoordinatesX(scanner), readCoordinatesY(scanner), readFromLocationX(scanner), readFromLocationY(scanner), readFromLocationZ(scanner), readFromLocationName(scanner), readToLocationX(scanner), readToLocationY(scanner), readToLocationZ(scanner), readDistance(scanner)));
                } else if (line.contains("update")) {
                    executor.executeUpdateIdElement(new ArgumentUpdateImpl(readIdElement("update", line),readName(scanner), readCoordinatesX(scanner), readCoordinatesY(scanner), readFromLocationX(scanner), readFromLocationY(scanner), readFromLocationZ(scanner), readFromLocationName(scanner), readToLocationX(scanner), readToLocationY(scanner), readToLocationZ(scanner), readDistance(scanner) ));
                } else if(line.contains("remove_by_id")){
                    executor.executeRemoveById(new ArgumentIdImpl(readIdElement( "remove_by_id", line)));
                }else if(line.equals("clear")){
                    executor.executeClear(new ArgumentEmptyImpl());
                }else if(line.equals("save")) {
                    executor.executeSave(new ArgumentEmptyImpl());
                }else if (line.contains("execute_script")){
                    executor.executeScript(new ArgumentScriptImpl(readFileName(line)));
                }else if (line.equals("exit")){
                    //System.out.println("Программа завершена");
                    //break;
                }else if(line.equals("remove_first")){
                    executor.executeRemoveFirst(new ArgumentEmptyImpl());
                }else if(line.equals("sort")){
                    executor.executeSort(new ArgumentEmptyImpl());
                }else if (line.equals("add_if_min")){
                    executor.executeAddIfMin(new ArgumentElementImpl(readName(scanner), readCoordinatesX(scanner), readCoordinatesY(scanner), readFromLocationX(scanner), readFromLocationY(scanner), readFromLocationZ(scanner), readFromLocationName(scanner), readToLocationX(scanner), readToLocationY(scanner), readToLocationZ(scanner), readDistance(scanner)));
                }else if(line.contains("filter_contains_name")){
                    executor.executeFilterContainsName(new ArgumentNameImpl(readFilterContainsName(line)));
                }else if (line.equals("min_by_id")){
                    executor.executeMinById(new ArgumentIdImpl(readIdElement("min_by_id", line)));
                }else if (line.contains("filter_less_than_distance")){
                    executor.executeFilterLessThanDistance(new ArgumentDistanceImpl(readFilterLessThanDistance(line)));
                } else {
                    throw new FileException("Команда в файле указана неверно, поэтому ее невозможно выполнить");
                }
            }
        }
        catch(FileException e){
            return  new Response(Response.Status.ERROR,e.getMessage()+" "+"Скрипт содержит ошибки, поэтому его выполнение приостановлено!");
        }catch (FileNotFoundException e){
            return new Response(Response.Status.ERROR, "Такого файла не найдена");
        }catch(Exception e){
            return new Response(Response.Status.ERROR,  "Что-то пошло не так ");
        }
        return new Response(Response.Status.SUCCESS, "Выполнение команды закончено");
    }
    public Long readFilterLessThanDistance(String line) throws FileException {
        long distance=0;
        if (line.length()<26){
            throw new FileException("Вы не ввели поле distance!");
        }
        String l=line.substring(25).trim();
        try{
            distance=Long.parseLong(l);
            if (distance<=1){
                throw  new FileException("Поле distance должно быть больше 1!");
            }
        }
        catch (NumberFormatException e){
            throw new FileException("Поле distance введено неправильно!");
        }
        return distance;
    }
    public String readFileName(String line) throws FileException {
        String fileName;
        if (line.length() < 15) {
            throw new FileException("Вы не ввели fileName!");
        }
        fileName = line.substring(14).trim();
        return fileName;
    }
    public Long readIdElement( String trueLine, String line) throws FileException {
        long id=0;
        if (line.length() < (trueLine.length() + 1)) {
            throw new FileException("id не найдено");
        }
        String idElement = line.substring(trueLine.length()).trim();
        try {
            id = Long.parseLong(idElement);
            if (id<=0){
                throw new FileException("Поле id должно быть больше 0!");
        } } catch (NumberFormatException e) {
            throw new FileException("Поле id введено неправильно, оно должно быть целым числом!");
        }
        return id;
    }
    public String readName(Scanner scanner) throws FileException {
        String name=scanner.nextLine();
        if(name.isEmpty()){
            throw new FileException("Поле name не может быть пустым!");
        }
        return name;
    }
    public Long readDistance(Scanner scanner) throws FileException {
        long distance=scanner.nextLong();
        if(distance<=1){
            throw new FileException("Поле distance должно быть больше 1!");
        }
        return distance;
    }

    public Double readCoordinatesX(Scanner scanner) throws FileException {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            throw new FileException("Поле X не может быть пустым!");
        }
        double x;
        try {
            x = Double.parseDouble(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле X неправильное, оно должно быть дробным или целым числом!");
        }
        return x;
    }
    public float readCoordinatesY(Scanner scanner) throws FileException {
        String line=scanner.nextLine();
        float y;
        try{
            y=Float.parseFloat(line);
        }
        catch(NumberFormatException e){
            throw new FileException("Поле Y неправильное, оно должно быть дробным числом");
        }
        return y;
    }
    public Float readFromLocationX(Scanner scanner) throws FileException {
        String line= scanner.nextLine();
        float fromX;
        if (line.isEmpty()) {
            throw new FileException("Поле X не может быть пустым!");
        }
        try {
            fromX = Float.parseFloat(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле X введено неправльно, оно должно быть дробным или целым числом!");
        }
        return fromX;

    }
    public Long readFromLocationY(Scanner scanner) throws FileException {
        String line= scanner.nextLine();
        if (line.isEmpty()) {
            throw new FileException("Поле Y не может быть пустым!");
        }
        long y;
        try {
            y = Long.parseLong(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле Y введено неправильно, оно должно быть целым числом!");
        }
        return y;
    }
    public float readFromLocationZ(Scanner scanner) throws FileException {
        String line=scanner.nextLine();
        float z;
        try {
            z = Float.parseFloat(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле Z введено неправильно, оно должно быть целым числом!");

        }
        return z;
    }
    public String readFromLocationName(Scanner scanner) throws FileException {
        String line=scanner.nextLine();
        if(line.isEmpty()){
            return null;
        }
        char[] l = line.toCharArray();
        for (int i = 0; i < l.length; i++) {
            if (Character.isDigit(l[i])) {
                i = l.length;
                throw new FileException("Поле name введено неправильно, name должно состоять из букв!");
                }
            }
        if (line.length() > 737) {
            System.out.println("Длина поля name не должно быть больше 737!");
        }
        return line;
    }
    public Integer readToLocationX(Scanner scanner) throws FileException {
        String line=scanner.nextLine();
        if (line.isEmpty()) {
            throw new FileException("Поле X не может быть пустым!");
        }
        int x;
        try {
            x = Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле X введено неправильно, оно должно быть целым числом!");
        }
        return x;
    }
    public int readToLocationY(Scanner scanner) throws FileException {
        String line= scanner.nextLine();
        int y;
        try {
            y = Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле Y введено неправильно, оно должно быть целым числом!");
        }
        return y;
    }
    public Long readToLocationZ(Scanner scanner) throws FileException {
        String line=scanner.nextLine();
        if (line.isEmpty()) {
            throw new FileException("Поле Z не может быть пустым! ");
        }
        long z;
        try {
            z = Long.parseLong(line);
        } catch (NumberFormatException exception) {
            throw new FileException("Поле Z введено неправильно, оно должно быть целым числом!");
        }
        return z;
    }
    public String readFilterContainsName(String line) throws FileException {
        String name;
        if (line.length()< 21){
            throw  new FileException("name не найдено");
        }
        name=line.substring(20).trim();

        char[] l = name.toCharArray();
        for (int i = 0; i < l.length; i++) {
            if (Character.isDigit(l[i])) {
                i = l.length;
                throw new FileException("Поле name введено неправильно, name должно состоять из букв!");
                }
            }
        return name;
        }
    }



