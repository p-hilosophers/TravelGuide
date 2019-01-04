package com.hilosophers.p.travelguide.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Version {

    @SerializedName("tag_name")
    @Expose
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
