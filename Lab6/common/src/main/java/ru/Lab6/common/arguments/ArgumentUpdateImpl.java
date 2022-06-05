package ru.Lab6.common.arguments;
/**
 * Класс для передачи аргументов элемента для обновления значения элемента
 */

public class ArgumentUpdateImpl implements Argument {
    public Long id;
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
    public ArgumentUpdateImpl(Long id, String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance) {
        this.id = id;
        this.name = name;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.fromX = fromX;
        this.fromY = fromY;
        this.fromZ = fromZ;
        this.fromName = fromName;
        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
        this.distance = distance;
    }
}
