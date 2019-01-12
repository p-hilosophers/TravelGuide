package com.hilosophers.p.travelguide.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Sight implements Serializable {


    @SerializedName("sightId")
    @Expose
    private UUID sightId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("longitude")
    @Expose
    private double longitude;

    @SerializedName("latitude")
    @Expose
    private double latitude;

    @SerializedName("city")
    @Expose
    private City city;

    @SerializedName("season")
    @Expose
    private String season;

    public Sight(){}



    public Sight(String name, double latitude, double longitude, String season)
    {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.season = season;
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

    public String getSeason() { return season; }

    public void setSeason(String season) { this.season = season; }


}
