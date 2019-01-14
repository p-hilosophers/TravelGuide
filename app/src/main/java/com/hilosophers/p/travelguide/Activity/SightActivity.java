package com.hilosophers.p.travelguide.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
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
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SightClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class SightActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private List<Sight> sightlist = new ArrayList<>();
    private Button showRoutesBtn;
    private String city;
    private Intent intent;
    private LocationRequest mLocationRequest;
    private Double latitude, longitude;

    private long UPDATE_INTERVAL = 10 * 1000;
    private long FASTEST_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights_map);
        startLocationUpdates();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Button distanceCalculation = findViewById(R.id.button);
        showRoutesBtn = findViewById(R.id.routesBtn);
        intent = getIntent();
        city = intent.getStringExtra("cityName");

        showRoutesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SightActivity.this, RouteActivity.class);
                intent.putExtra("cityName", city);
                startActivity(intent);
            }
        });

        distanceCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDistance = new Intent(SightActivity.this, DistanceActivity.class);
                intentDistance.putExtra("originsLat",latitude);
                intentDistance.putExtra("originsLon",longitude);
                intentDistance.putExtra("cityName",city);
                SightActivity.this.startActivity(intentDistance);
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        if (checkPermissions()) {
            googleMap.setMyLocationEnabled(true);
        }

        Retrofit retrofit = RequestService.initializeRequest().build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Sight>> call = client.repoForSights(city);

        call.enqueue(new Callback<List<Sight>>() {
            @Override
            public void onResponse(Call<List<Sight>> call, Response<List<Sight>> response) {

                Marker marker;
                List<Sight> repos = response.body();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Sight sight : repos) {
                    sightlist.add(sight);
                    marker = googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                            .title(sight.getName()));
                    builder.include(marker.getPosition());
                }
                LatLng city = new LatLng(sightlist.get(0).getCity().getLatitude(), sightlist.get(0).getCity().getLongitude());


                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 5);
                mMap.moveCamera(cu);
                mMap.animateCamera(cu);

                Toast.makeText(SightActivity.this, "in response", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Sight>> call, Throwable t) {
                Toast.makeText(SightActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(SightActivity.this, SightsPhotosActivity.class);
                intent.putExtra("sightName", marker.getTitle());
                startActivity(intent);
                return true;
            }
        });
    }

    protected void startLocationUpdates() {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                            onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
        }

        public void onLocationChanged(Location location) {
//            // New location has now been determined
//            String msg = "Updated Position: " +
//                    Double.toString(location.getLatitude()) + "," +
//                    Double.toString(location.getLongitude());
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            // You can now create a LatLng Object for use with maps
            latitude = location.getLatitude();
            longitude = location.getLongitude();

        }

        private boolean checkPermissions() {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                requestPermissions();
                return false;
            }
        }

        private void requestPermissions() {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
}




