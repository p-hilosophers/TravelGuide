package com.hilosophers.p.travelguide.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hilosophers.p.travelguide.Model.City;
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SightClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class SightByTimeActivity extends AppCompatActivity implements OnMapReadyCallback {
    private List<Sight> day = new ArrayList<>();
    private List<Sight> night = new ArrayList<>();
    private List<Sight> both = new ArrayList<>();
    private GoogleMap map;
    private Intent intent;
    private String city;
     RadioGroup rg;
     RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_by_time);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.timemap);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        city = intent.getStringExtra("cityName");
        rg = (RadioGroup)findViewById(R.id.radioGroup);
    }
    public void rclick(View v){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        Marker marker;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int count=0;

        rb=(RadioButton) findViewById(radiobuttonid);
        if (rb.getText().equals("day")){
            for (Sight sight : day){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
                count ++;
            }
        }
        else if (rb.getText().equals("night")){
            for (Sight sight : night){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
                count ++;
            }
        }
        else if (rb.getText().equals("both")){
            for (Sight sight : both){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
                count ++;
            }
        }
        if(count>0){
        LatLngBounds bounds = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,5);
        map.moveCamera(cameraUpdate);
        map.animateCamera(cameraUpdate);
    }}



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        Retrofit retrofit = RequestService.initializeRequest().build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        call.enqueue(new Callback<List<Sight>>() {
            @Override
            public void onResponse(Call<List<Sight>> call, Response<List<Sight>> response) {
                List<Sight> repos = response.body();
                for(Sight sight:repos){
                    System.out.println(sight.getDayNight());
                    System.out.println(sight.getDayNight() == "Day");
                    if(sight.getDayNight().equals("Day")){
                        day.add(sight);
                    } else if (sight.getDayNight().equals("Night")) {
                        night.add(sight);
                    }
                    else if (sight.getDayNight().equals("Both")){
                        both.add(sight);

                    }
                }
                Toast.makeText(SightByTimeActivity.this, "in response", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Sight>> call, Throwable t) {
                Toast.makeText(SightByTimeActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
