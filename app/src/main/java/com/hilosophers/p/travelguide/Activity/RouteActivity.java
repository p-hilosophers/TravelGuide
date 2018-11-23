package com.hilosophers.p.travelguide.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.RouteClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteActivity extends AppCompatActivity {

    private ListView routesList;
    private List<String> routes;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routes);
        routesList = findViewById(R.id.routeList);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://83.212.103.26:8080/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        RouteClient client = retrofit.create(RouteClient.class);
        Call<List<List<Sight>>> call = client.repoForRoutes();

        call.enqueue(new Callback<List<List<Sight>>>() {
            @Override
            public void onResponse(Call<List<List<Sight>>> call, Response<List<List<Sight>>> response) {

                String route ="";
                List<List<Sight>> repos = response.body();
                for(List<Sight> sightItem:repos) {
                    for (int i =0;i<sightItem.size();i++) {
                        route +=sightItem.get(i).getName()+", ";
                    }
                    routes.add(route);
                 }

            }
            @Override
            public void onFailure(Call<List<List<Sight>>> call, Throwable t) {
                Toast.makeText(RouteActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });
        ListAdapter routeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, routes);
        routesList.setAdapter(routeAdapter);
    }
}