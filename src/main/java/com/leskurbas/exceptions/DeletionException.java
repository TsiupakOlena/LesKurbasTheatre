package com.leskurbas.exceptions;

public class DeletionException extends Exception {
    public DeletionException() {}
    public DeletionException(String msg) {
        super(msg);
    }
    public String toString(){
        return super.toString();
    }
}
