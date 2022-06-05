package ru.Lab6.common.arguments;

/**
 * Класс для передачи аргумента fileName методу
 */

public class ArgumentScriptImpl implements Argument{
    public String fileName;
    public ArgumentScriptImpl(String fileName){
        this.fileName=fileName;
    }
}
