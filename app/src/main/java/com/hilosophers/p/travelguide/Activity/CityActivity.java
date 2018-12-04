package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Adapter.CustomListView;
import com.hilosophers.p.travelguide.Repository.CityClient;
import com.hilosophers.p.travelguide.Model.City;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Services.RequestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CityActivity extends AppCompatActivity {

    private List<String> names = new ArrayList<>();
    private List<String> photos = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        listView = findViewById(R.id.city_listview);


        Retrofit retrofit = RequestService.initializeRequest().build();

        CityClient client = retrofit.create(CityClient.class);
        Call<List<City>> call = client.repoForCity();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                CustomListView customListView ;
                List<City> repos = response.body();
                for(City city: repos)
                {
                    names.add(city.getName());
                    photos.add(city.getPhoto());
                }
                customListView = new CustomListView(CityActivity.this,names,photos);
                listView.setAdapter(customListView);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(CityActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CityActivity.this,SightActivity.class);
                intent.putExtra("cityName",listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}
