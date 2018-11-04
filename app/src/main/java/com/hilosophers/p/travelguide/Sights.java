package com.hilosophers.p.travelguide;


public class Sights {
    private double lat;
    private double log;
    private String name;

    public double getLat() {
        return lat;
    }
    public String getName() {
        return name;
    }
    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setName(String name) {
        this.name = name;
    }






    public Sights(double lat,double log, String name)
    {
        this.lat = lat;
        this.log = log;
        this.name = name;
    }



}
