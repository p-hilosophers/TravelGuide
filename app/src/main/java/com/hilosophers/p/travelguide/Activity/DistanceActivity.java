package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SortClient;
import com.hilosophers.p.travelguide.Services.RequestService;
import com.hilosophers.p.travelguide.Model.SightRo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DistanceActivity extends AppCompatActivity {

    private Double originsLat, originsLon;
    private String cityName, nameDistance;
    private List<String> listNameDistance = new ArrayList<>();
    private Intent intent;
    private List<SightRo> dtoList = new ArrayList<>();
    private ListView sortedDistanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_distance);
        sortedDistanceList = findViewById(R.id.distanceList);
        intent = getIntent();
        originsLat = intent.getDoubleExtra("originsLat", 0);
        originsLon = intent.getDoubleExtra("originsLon", 0);
        cityName = intent.getStringExtra("cityName");

        Retrofit retrofit = RequestService.initializeRequest().build();
        SortClient client = retrofit.create(SortClient.class);
        Call<List<SightRo>> call = client.getDistanceList(originsLat, originsLon, cityName);

        call.enqueue(new Callback<List<SightRo>>() {
            @Override
            public void onResponse(Call<List<SightRo>> call, Response<List<SightRo>> response) {

                dtoList = response.body();

                for(SightRo item : dtoList){
                    nameDistance = "";
                    nameDistance = item.getName() + "\n\t(" + item.getDistance()/1000 + " km" + ")";
                    listNameDistance.add(nameDistance);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(DistanceActivity.this,R.layout.sorted_by_distance,listNameDistance);
                sortedDistanceList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SightRo>> call, Throwable t) {
                Toast.makeText(DistanceActivity.this, "Couldn't establish a connection with the server !", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
