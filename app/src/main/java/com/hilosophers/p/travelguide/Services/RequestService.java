package com.hilosophers.p.travelguide.Services;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {


    private static String url = "http://35.204.237.100:8081/";
                //"http://35.204.237.100:8081/";

    public static Retrofit.Builder initializeRequest()
    {
         return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

    }
}
