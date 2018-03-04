package com.example.firebasetest;

public class Entry{
    private String ID;
    private String GPS;
    private int status;

    public Entry(String ID, String GPS, int status) {
        this.ID = ID;
        this.GPS = GPS;
        this.status = status;
    }

    public Entry() {
        this.ID = "NO ID";
        this.GPS = "NO GPS";
        this.status = -1;
    }

    public String getID() {
        return ID;
    }

    public String getGPS() {
        return GPS;
    }

    public int getStatus() {
        return status;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}