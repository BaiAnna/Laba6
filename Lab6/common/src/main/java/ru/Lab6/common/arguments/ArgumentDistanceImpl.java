package ru.Lab6.common.arguments;

/**
 * Класс для передачи аргумента distance методу
 */
public class ArgumentDistanceImpl implements Argument {
    public Long distance;
    public ArgumentDistanceImpl(Long distance){
        this.distance=distance;
    }
}
