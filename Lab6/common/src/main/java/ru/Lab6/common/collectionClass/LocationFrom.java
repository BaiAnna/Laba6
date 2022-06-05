package ru.Lab6.common.collectionClass;

public class LocationFrom {
    private Float x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private float z;
    private String name; //Длина строки не должна быть больше 737, Поле может быть null
    public LocationFrom( Float x, Long y, float z, String name){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }
    public void setX(Float x){
        this.x=x;
    }
    public Float getX(){
        return x;
    }
    public void setY(Long y){
        this.y=y;
    }
    public Long getY(){
        return y;
    }
    public void setZ(float z){this.z=z;}
    public float getZ(){return z;}
    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    @Override
    public String toString() {
        return "LocationFrom{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}

