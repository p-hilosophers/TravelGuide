package com.hilosophers.p.travelguide.Repository;

import com.hilosophers.p.travelguide.Model.Sight;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RouteClient {

    @GET("/cities/")
    Call<List<List<Sight>>> repoForRoutes();
}
