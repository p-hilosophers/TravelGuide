package com.hilosophers.p.travelguide.Repository;
import com.hilosophers.p.travelguide.Model.Route;
import com.hilosophers.p.travelguide.Model.Sight;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RouteClient  {

    @GET("/routes/{cityName}")
    Call<Route> repoForRoutes(@Path("cityName") String cityName);

}
