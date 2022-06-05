package ru.Lab6.common.arguments;

/**
 * Класс для передачи аргумента Id методу
 */
public class ArgumentIdImpl implements Argument {
    public long id;
    public ArgumentIdImpl(Long id){
        this.id=id;
    }
}
