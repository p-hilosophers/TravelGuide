package com.hilosophers.p.travelguide.Repository;

import com.hilosophers.p.travelguide.Model.Version;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VersionClient {

    @GET("repos/{user}/{repo}/releases/latest")
    Call<Version> getLatestVersion(@Path("user") String user,@Path("repo") String repo);
}
