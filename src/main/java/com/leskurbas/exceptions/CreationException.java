package com.leskurbas.exceptions;

public class CreationException extends Exception {
    public CreationException() {}
    public CreationException(String msg) {
        super(msg);
    }
    public String toString(){
        return super.toString();
    }
}