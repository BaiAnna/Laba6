package ru.Lab6.common.collectionClass;

public class Coordinates {
    private Double x; //Максимальное значение поля: 33, Поле не может быть null
    private float y;
    public Coordinates(Double x, float y){
        this.x=x;
        this.y=y;
    }
    public void setX(Double x){
        this.x=x;
    }
    public Double getX(){
        return x;
    }
    public void setY(float y){
        this.y=y;
    }
    public float getY(){
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
