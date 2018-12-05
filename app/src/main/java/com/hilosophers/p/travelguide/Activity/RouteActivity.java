package com.hilosophers.p.travelguide.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.hilosophers.p.travelguide.Adapter.RoutesCustomAdapter;
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.RouteClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RouteActivity extends AppCompatActivity {

    private ListView routesList ;
    private List<String> routes = new ArrayList<>();
    private String city;
    private Intent intent;
    private String route ="" ;
    private ArrayList<ArrayList<Sight>> sightsList = new ArrayList<>();
    private List<List<Sight>> repos;

    @Override
    protected  void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_routes);
        routesList = findViewById(R.id.routeList);
        intent = getIntent();
        city = intent.getStringExtra("cityName");

       Retrofit retrofit = RequestService.initializeRequest().build();
        RouteClient client = retrofit.create(RouteClient.class);
        Call<List<List<Sight>>> call = client.repoForRoutes(city);

        call.enqueue(new Callback<List<List<Sight>>>() {
            @Override
            public void onResponse(Call<List<List<Sight>>> call, Response<List<List<Sight>>> response) {

                    repos = response.body();

                    for(List<Sight> sightItem:repos) {
                        route = "";
                        sightsList.add((ArrayList<Sight>) sightItem);
                        for (int i =0;i<sightItem.size();i++) {
                         route +=sightItem.get(i).getName()+", ";
                        }
                        routes.add(route);
                     }

                RoutesCustomAdapter adapter = new RoutesCustomAdapter(RouteActivity.this,routes);
                routesList.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<List<Sight>>> call, Throwable t) {
                Toast.makeText(RouteActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });
        routesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String uri = "https://www.google.com/maps/dir/?api=1&origin=Current+Location";
                List<Sight> sights = sightsList.get(position);
                //TO DO sort by distance
                uri += "&destination=" + sights.get(0).getLatitude() + "," + sights.get(0).getLongitude() + "&travelmode=walking";

                if(sights.size()>1) {
                    for (int i = 1; i < sights.size(); i++) {
                        uri += "&waypoints=" + sights.get(i).getLatitude() + "," + sights.get(i).getLongitude();
                    }
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }
}