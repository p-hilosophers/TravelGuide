package com.hilosophers.p.travelguide.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Adapter.CustomListView;
import com.hilosophers.p.travelguide.CityClient;
import com.hilosophers.p.travelguide.Model.City;
import com.hilosophers.p.travelguide.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityActivity extends AppCompatActivity {

    private List<String> names = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        listView = (ListView) findViewById(R.id.city_listview);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.3:8080/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
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
                }
                customListView = new CustomListView(CityActivity.this,names);
                listView.setAdapter(customListView);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(CityActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
