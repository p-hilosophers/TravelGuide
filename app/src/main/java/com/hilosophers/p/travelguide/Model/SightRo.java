package com.hilosophers.p.travelguide.Model;


public class SightRo {

    private String name;
    private double distance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        String string = this.name + " " + this.distance;
        return string;
    }
}
