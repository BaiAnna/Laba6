package ru.Lab6.client.generationRequests;

import ru.Lab6.common.request.Request;

public interface GenerationRequest {
    Request help();
    Request add(String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance);
    Request info();
    Request show();
    Request sort();
    Request exit();
    Request clear();
    Request addIfMin(String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance);
    Request removeFirst();
    Request removeById(Long id);
    Request minById(Long id);
    Request filterContainsName(String name);
    Request filterLessThanDistance(Long distance);
    Request executeScript(String fileName);
}
