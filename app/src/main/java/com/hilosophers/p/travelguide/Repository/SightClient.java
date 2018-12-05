package com.hilosophers.p.travelguide.Repository;
import com.hilosophers.p.travelguide.Model.Photo;
import com.hilosophers.p.travelguide.Model.Sight;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SightClient {

    @GET("/cities/{city}/sights")
    Call<List<Sight>> repoForSights(@Path("city") String city);

    @GET("sights/{sight}/photos")
    Call<List<Photo>> repoForPhotos(@Path("sight") String sight );



}
