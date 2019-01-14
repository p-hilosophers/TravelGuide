package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Model.PopularityRo;
import com.hilosophers.p.travelguide.Model.SightRo;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SortClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PopularityActivity extends AppCompatActivity {

    private String cityName, nameCount;
    private List<String> listNameCount = new ArrayList<>();
    private Intent intent;
    private List<PopularityRo> dtoList = new ArrayList<>();
    private ListView sortedPopularityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popularity);
        setTitle("Sorted from least to most popular");
        sortedPopularityList = findViewById(R.id.popularityList);

        intent = getIntent();
        cityName = intent.getStringExtra("cityName");

        Retrofit retrofit = RequestService.initializeRequest().build();
        SortClient client = retrofit.create(SortClient.class);
        Call<List<PopularityRo>> call = client.getPopularityList(cityName);
        call.enqueue(new Callback<List<PopularityRo>>() {
            @Override
            public void onResponse(Call<List<PopularityRo>> call, Response<List<PopularityRo>> response) {

                dtoList = response.body();

                for(PopularityRo item : dtoList){
                    nameCount = "";
                    nameCount = item.getName() + " has " + item.getPhotoCount() + " photographs !";
                    listNameCount.add(nameCount);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(PopularityActivity.this,R.layout.sorted_by_distance, listNameCount);
                sortedPopularityList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PopularityRo>> call, Throwable t) {
                Toast.makeText(PopularityActivity.this, "Couldn't establish a connection with the server !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
