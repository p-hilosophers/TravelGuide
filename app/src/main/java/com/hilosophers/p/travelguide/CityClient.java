package com.hilosophers.p.travelguide;

import com.hilosophers.p.travelguide.Model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityClient {

    @GET("/cities/")
    Call<List<City>> repoForCity();
}
