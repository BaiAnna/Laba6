package ru.Lab6.common.arguments;

/**
 * Класс для передачи аргументов элемента методу для добавления элемента в коллекцию
 */

public class ArgumentElementImpl implements Argument {
    public String name;
    public Double coordinatesX;
    public float coordinatesY;
    public Float fromX;
    public Long fromY;
    public float fromZ;
    public String fromName;
    public Integer toX;
    public int toY;
    public Long toZ;
    public Long distance;
    public ArgumentElementImpl(String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance){
        this.name=name;
        this.coordinatesX=coordinatesX;
        this.coordinatesY=coordinatesY;
        this.fromX=fromX;
        this.fromY=fromY;
        this.fromZ=fromZ;
        this.fromName=fromName;
        this.toX=toX;
        this.toY=toY;
        this.toZ=toZ;
        this.distance=distance;

    }
}
