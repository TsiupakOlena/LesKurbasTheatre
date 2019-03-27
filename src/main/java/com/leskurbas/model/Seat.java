package com.leskurbas.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private long id;
    private byte row;
    private short number;
    private byte isVIP;
    private byte isReserved;
    private long sessionID;
    private long reservationID;

    public Seat() {}

    public Seat(byte row, short number, byte isVIP, byte isReserved,
                long sessionID, long reservationID) {
        this.row = row;
        this.number = number;
        this.isVIP = isVIP;
        this.isReserved = isReserved;
        this.sessionID = sessionID;
        this.reservationID = reservationID;
    }

    public Seat(int id, byte row, short number, byte isVIP,
                byte isReserved, long sessionID, long reservationID) {
        this(row, number, isVIP, isReserved, sessionID, reservationID);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public byte isVIP() {
        return isVIP;
    }

    public void setVIP(byte VIP) {
        isVIP = VIP;
    }

    public byte isReserved() {
        return isReserved;
    }

    public void setReserved(byte reserved) {
        isReserved = reserved;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionIdD) {
        this.sessionID = sessionID;
    }

    public long getReservationID() {
        return reservationID;
    }

    public void setReservationID(long reservationID) {
        this.reservationID = reservationID;
    }
}
