package ru.Lab6.server.informationCollection;

/**
 * Класс для получения информации о коллекции
 */

public class InformationCollectionRouteImpl implements InformationCollection {
    private String type;
    private String creationDate;
    private int size;

    public InformationCollectionRouteImpl(String type, String creationDate, int numbers){
        this.type=type;
        this.size=numbers;
        this.creationDate=creationDate;
    }

    /**
     * Метод инициализирует поле type коллекции
     * @param type-тип коллекции
     */
    public void setType(String type){
        this.type=type;

    }
    /**
     * Метод возвращает тип коллекции
     */
    public String getType(){
        return type;
    }

    /**
     * Метод инициализирует поле creationDate коллекции
     * @param creationDate-дата запуска программы
     */
    public void setCreationDate(String  creationDate){
        this.creationDate=creationDate;

    }

    /**
     * Метод возвращает creationDate коллекции
     * @return
     */
    public String getCreationDate(){
        return creationDate;
    }

    /**
     * Метод инициализирует поле size коллекции
     * @param numbers-количество элементов коллекции
     */
    public void setSize(int numbers){
        this.size=numbers;
    }

    /**
     * Метод возвращает размер коллекции
     * @return
     */
    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "InformationCollection{" +
                "type='" + type + '\'' +
                ", creationDate=" + creationDate +
                ", size=" + size +
                '}';
    }
}
