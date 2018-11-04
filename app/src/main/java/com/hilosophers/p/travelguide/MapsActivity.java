package com.hilosophers.p.travelguide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng paris = new LatLng(48.864716, 2.349014);
        mMap.addMarker(new MarkerOptions().position(paris).title("Paris"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris));*/

        LatLng london = new LatLng(51.509865, -0.118092);
        mMap.addMarker(new MarkerOptions().position(london).title("London"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(london,10),5000,null);

        /*LatLng barcelona = new LatLng(41.390205, 2.154007);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Barcelona"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(barcelona));

        LatLng athens = new LatLng(37.983810, 23.727539);
        mMap.addMarker(new MarkerOptions().position(athens).title("Athens"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(athens));*/

    }
}
