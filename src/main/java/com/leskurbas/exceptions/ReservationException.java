package com.leskurbas.exceptions;

public class ReservationException extends Exception {
    public ReservationException() {}
    public ReservationException(String msg) {
        super(msg);
    }
    public String toString(){
        return super.toString();
    }
}