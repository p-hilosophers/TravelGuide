package com.hilosophers.p.travelguide.Model;

import java.util.UUID;

public class Photo {
    private UUID idPhoto;

    private String name;

    private double longitude;

    private double latitude;
    private String imageUrl;
    private Sight sight;


    public Photo(){}

    public Photo(UUID idPhoto, String name, double longitude, double latitude, String imageUrl) {
        this.idPhoto = idPhoto;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imageUrl = imageUrl;
    }



    public UUID getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(UUID idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
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


}
