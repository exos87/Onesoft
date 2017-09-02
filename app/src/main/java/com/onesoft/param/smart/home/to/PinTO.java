package com.onesoft.param.smart.home.to;

/**
 * Created by exosj on 12.06.2017.
 */

public class PinTO {

    double x;
    double y;
    String izba;
    Float value;

    public PinTO(double x, double y, String izba,Float value) {
        this.x = x;
        this.y = y;
        this.izba = izba;
        this.value = value;
    }

    public PinTO() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getIzba() {
        return izba;
    }

    public void setIzba(String izba) {
        this.izba = izba;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
