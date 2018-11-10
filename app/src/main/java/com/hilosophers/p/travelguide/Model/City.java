package com.hilosophers.p.travelguide.Model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class City {

    @SerializedName("cityId")
    private UUID cityId;

    @SerializedName("name")
    private String name;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("photoCount")
    private int photoCount;

    public City(){}

    public City(UUID cityId, String name, double longitude, double latitude, int photoCount) {
        this.cityId = cityId;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photoCount = photoCount;
    }

    public City(String name, double longitude, double latitude, int photoCount) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photoCount = photoCount;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
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

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }
}
