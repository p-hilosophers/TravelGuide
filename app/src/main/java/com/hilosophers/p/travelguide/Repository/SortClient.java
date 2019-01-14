package com.hilosophers.p.travelguide.Repository;


import com.hilosophers.p.travelguide.Model.SightRo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface SortClient {

    @GET("distanceSort/{originsLat}/{originsLon}/{cityName}")
    Call<List<SightRo>> getDistanceList(@Path("originsLat") Double originsLat, @Path("originsLon") Double originsLon, @Path("cityName") String cityName);

}
