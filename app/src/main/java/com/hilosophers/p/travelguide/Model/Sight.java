package com.hilosophers.p.travelguide.Model;

import java.util.UUID;

public class Sight {


    private UUID sightId;

    private String name;

    private double longitude;

    private double latitude;

    private City city;

    public Sight(){}

    public Sight(String name, double latitude, double longitude)
    {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public UUID getSightId() {
        return sightId;
    }

    public void setSightId(UUID sightId) {
        this.sightId = sightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
