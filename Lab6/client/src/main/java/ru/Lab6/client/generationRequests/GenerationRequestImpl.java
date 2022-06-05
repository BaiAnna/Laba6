package ru.Lab6.client.generationRequests;

import ru.Lab6.common.arguments.*;
import ru.Lab6.common.request.Request;

import java.util.List;
import java.util.Map;

public class GenerationRequestImpl implements GenerationRequest {
    Request request=null;
    @Override
    public Request help() {
        ArgumentEmptyImpl argument = new ArgumentEmptyImpl();
        return request = new Request("help", argument);
    }
    @Override
    public Request add(String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance) {
        ArgumentElementImpl argument = new ArgumentElementImpl(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName, toX, toY, toZ, distance);
        return request = new Request("add", argument);
    }
    @Override
    public Request info(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("info", argument);
    }
    @Override
    public Request show(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("show", argument);
    }
    @Override
    public Request sort(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("sort", argument);
    }
    @Override
    public Request exit(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("exit", argument);
    }
    @Override
    public Request clear(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("clear", argument);
    }
    @Override
    public Request addIfMin(String name, Double coordinatesX, float coordinatesY, Float fromX, Long fromY, float fromZ, String fromName, Integer toX, int toY, Long toZ, Long distance){
        ArgumentElementImpl argument=new ArgumentElementImpl(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName, toX, toY, toZ, distance);
        return request=new Request("add_if_min", argument);
    }
    @Override
    public Request removeFirst(){
        ArgumentEmptyImpl argument=new ArgumentEmptyImpl();
        return request=new Request("remove_first", argument);
    }
    @Override
    public Request removeById(Long id){
        ArgumentIdImpl argument=new ArgumentIdImpl(id);
        return request=new Request("remove_by_id", argument);
    }
    @Override
    public Request minById(Long id) {
        ArgumentIdImpl argument = new ArgumentIdImpl(id);
        return request = new Request("min_by_id", argument);
    }
    @Override
    public Request filterContainsName(String name){
        ArgumentNameImpl argument= new ArgumentNameImpl(name);
        return request=new Request("filter_contains_name", argument);
    }
    @Override
    public Request filterLessThanDistance(Long distance){
        ArgumentDistanceImpl argument=new ArgumentDistanceImpl(distance);
        return request=new Request("filter_less_than_distance", argument);
    }
    @Override
    public Request executeScript(String fileName){
        ArgumentScriptImpl argument=new ArgumentScriptImpl(fileName);
        return  request=new Request("execute_script", argument);
    }
}




