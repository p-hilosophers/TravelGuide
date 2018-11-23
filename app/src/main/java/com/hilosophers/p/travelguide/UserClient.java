package com.hilosophers.p.travelguide;

import com.hilosophers.p.travelguide.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    @POST("users")
    Call<User> createAccount(@Body User user);

    @GET("users/{userEmail}/{password}")
    Call<User> userLogin(@Path("userEmail") String userEmail, @Path("password") String password);


}
