package ru.Lab6.common.collectionClass;

public class LocationTo {
    private Integer x; //Поле не может быть null
    private int y;
    private Long z; //Поле не может быть null
    public LocationTo( Integer x, int y, Long z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public void setX(Integer x){
        this.x=x;
    }
    public Integer getX(){
        return x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getY(){
        return y;
    }
    public void setZ(Long z){ this.z=z;};
    public Long getZ(){ return z;}

    @Override
    public String toString() {
        return "LocationTo{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
