package ru.Lab6.server.informationCollection;

public interface InformationCollection {
    void setType(String type);
    String getType();
    void setCreationDate(String creationDate);
    String  getCreationDate();
    void setSize(int size);
    int getSize();
}
