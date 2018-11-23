package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Adapter.CustomListPhotoView;
import com.hilosophers.p.travelguide.Model.Photo;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SightClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SightsPhotosActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> photos = new ArrayList<>();
    private Intent intent;
    private String sight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        intent = getIntent();
        sight = intent.getStringExtra("sightName");




        listView = (ListView) findViewById(R.id.sights_photos);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:8080/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Photo>> call = client.repoForPhotos(sight);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                CustomListPhotoView customListPhotoView ;
                List<Photo> repos = response.body();
                for(Photo photo: repos)
                {

                    photos.add(photo.getImage());
                }
                customListPhotoView = new CustomListPhotoView(SightsPhotosActivity.this,photos);
                listView.setAdapter(customListPhotoView);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(SightsPhotosActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });


            }

    }

