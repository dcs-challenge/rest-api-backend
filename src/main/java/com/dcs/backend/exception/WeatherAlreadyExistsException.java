package com.dcs.backend.exception;

public class WeatherAlreadyExistsException extends RuntimeException{
    private String message;

    public WeatherAlreadyExistsException(){
    }

    public WeatherAlreadyExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
