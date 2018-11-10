package com.hilosophers.p.travelguide.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;

import java.util.ArrayList;
import java.util.List;



public class SightsMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights_map);
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
        //String mLine;


        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            mMap.setMyLocationEnabled(true);
            return;
        }*/


    Sight s1 = new Sight("Tower Of London",51.508530, -0.076132);
    Sight s2 = new Sight("Buckingham Palace",51.501476, -0.140634);
        List<Sight> sightlist = new ArrayList<>();
        sightlist.add(s1);
        sightlist.add(s2);
        Marker marker;
        for (Sight s:sightlist)
        {

            marker = googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(s.getLatitude(),s.getLongitude()))
            .title(s.getName()));

        }
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(s1.getLatitude(),s1.getLongitude()) ));
        /*Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(38.609556, -1.139637))
                .anchor(0.5f, 0.5f)
                .title("Title1")
                .alpha(0.7f)//opacity
                .snippet("Snippet1"));//added information*/
    }


}
