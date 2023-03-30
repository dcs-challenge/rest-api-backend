package com.dcs.backend.exception;

public class ParamsMissingException extends RuntimeException{
    private String message;

    public ParamsMissingException(){
    }

    public ParamsMissingException(String msg){
        super(msg);
        this.message = msg;
    }
}

