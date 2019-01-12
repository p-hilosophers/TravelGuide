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

public class ByTimeActivity extends AppCompatActivity implements OnMapReadyCallback {
    private List<Sight> day = new ArrayList<>();
    private List<Sight> night = new ArrayList<>();
    private List<Sight> both = new ArrayList<>();
    private GoogleMap map;
    private Intent intent;
    private String city;
    private RadioGroup rg;
    private RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_time);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        city = intent.getStringExtra("cityName");
        rg = (RadioGroup)findViewById(R.id.radiogroup);
    }
    public void radioButtonClick(View v){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        Marker marker;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        rb=(RadioButton) findViewById(radiobuttonid);
        if (rb.getText().equals("Day")){
            for (Sight sight : day){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
            }
        }
        else if (rb.getText().equals("Night")){
            for (Sight sight : night){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
            }
        }
        else if (rb.getText().equals("Both")){
            for (Sight sight : both){
                marker=map.addMarker(new MarkerOptions().position(new LatLng(sight.getLatitude(),sight.getLongitude())).title(sight.getName()));
                builder.include(marker.getPosition());
            }
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cameraup = CameraUpdateFactory.newLatLngBounds(bounds,5);
        map.moveCamera(cameraup);
        map.animateCamera(cameraup);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Retrofit retrofit = RequestService.initializeRequest().build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        call.enqueue(new Callback<List<Sight>>() {
            @Override
            public void onResponse(Call<List<Sight>> call, Response<List<Sight>> response) {
                List<Sight> repos = response.body();
                for(Sight sight:repos){
                    if(sight.getDayNight().equals("Day")){
                        day.add(sight);
                    } else if (sight.getDayNight().equals("Night")) {
                        night.add(sight);
                        }
                        else if (sight.getDayNight().equals("Both")){
                        both.add(sight);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sight>> call, Throwable t) {

            }
        });

    }
}
