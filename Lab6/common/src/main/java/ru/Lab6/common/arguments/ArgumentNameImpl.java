package ru.Lab6.common.arguments;
/**
 * Класс для передачи аргумента name методу
 */
public class ArgumentNameImpl implements Argument {
    public String name;
    public ArgumentNameImpl(String name){
        this.name=name;
    }
}
