package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class SightBySeasonActivity extends AppCompatActivity implements OnMapReadyCallback {
    private List<Sight> fall =new ArrayList<>();
    private List<Sight> winter =new ArrayList<>();
    private List<Sight> spring =new ArrayList<>();
    private List<Sight> summer =new ArrayList<>();
    private List<Sight> multiseason =new ArrayList<>();
    private GoogleMap sMap;
    private Intent intent;
    private String city;
    RadioButton rb;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_by_season);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
               .findFragmentById(R.id.Seasonmap);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        city = intent.getStringExtra("cityName");
        rg = (RadioGroup)findViewById(R.id.rggroup);
    }

    public void rbclick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        Marker marker;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        rb = (RadioButton) findViewById(radiobuttonid);
        if (rb.getText().equals("Fall")) {
            for (Sight sight : fall) {
                marker = sMap.addMarker(new MarkerOptions()
                        .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                        .title(sight.getName()));
                builder.include(marker.getPosition());
            }
        } else if (rb.getText().equals("Winter")) {
            for (Sight sight : winter) {
                marker = sMap.addMarker(new MarkerOptions()
                        .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                        .title(sight.getName()));
                builder.include(marker.getPosition());
            }

        } else if (rb.getText().equals("Spring")) {
            for (Sight sight : spring) {
                marker = sMap.addMarker(new MarkerOptions()
                        .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                        .title(sight.getName()));
                builder.include(marker.getPosition());
            }
        } else if (rb.getText().equals("Summer")) {
            for (Sight sight : summer) {
                marker = sMap.addMarker(new MarkerOptions()
                        .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                        .title(sight.getName()));
                builder.include(marker.getPosition());
            }
        } else {
            for (Sight sight : multiseason) {
                marker = sMap.addMarker(new MarkerOptions()
                        .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                        .title(sight.getName()));
                builder.include(marker.getPosition());
            }
        }

        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 5);
        sMap.moveCamera(cu);
        sMap.animateCamera(cu);
    }

      @Override
        public void onMapReady (final GoogleMap googleMap) {
        sMap= googleMap;


        Retrofit retrofit = RequestService.initializeRequest().build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        call.enqueue(new Callback<List<Sight>>() {
            @Override
            public void onResponse(Call<List<Sight>> call, Response<List<Sight>> response) {
                List<Sight> repos= response.body();
                for(Sight sight: repos){
                    System.out.println(sight.getSeason());
                    System.out.println(sight.getSeason() == "Fall");
                    if(sight.getSeason().equals("Fall")){

                        fall.add(sight);

                    }
                    else if(sight.getSeason().equals("Winter")){
                        winter.add(sight);

                    }
                    else if(sight.getSeason().equals("Spring")){
                        spring.add(sight);
                    }
                    else if(sight.getSeason().equals("Summer")){
                        summer.add(sight);
                    }
                    else {
                        multiseason.add(sight);
                }
                }
                Toast.makeText(SightBySeasonActivity.this, "in response", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Sight>> call, Throwable t) {
                Toast.makeText(SightBySeasonActivity.this,"error :(",Toast.LENGTH_SHORT).show();
            }
        });

    }

}

