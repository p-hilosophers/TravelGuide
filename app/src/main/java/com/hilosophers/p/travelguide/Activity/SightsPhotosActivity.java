package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Adapter.CustomListPhotoView;
import com.hilosophers.p.travelguide.Model.Photo;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SightClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SightsPhotosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private List<String> photos = new ArrayList<>();
    private Intent intent;
    private String sight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        intent = getIntent();
        sight = intent.getStringExtra("sightName");
        listView = findViewById(R.id.sights_photos);
//        listView.setEmptyView(findViewById(R.id.loadingPanel));



        Retrofit retrofit = RequestService.initializeRequest().build();
        SightClient client = retrofit.create(SightClient.class);
        Call<List<Photo>> call = client.repoForPhotos(sight);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                CustomListPhotoView customListPhotoView ;
                List<Photo> repos = response.body();
                for(Photo photo: repos)
                {

                    photos.add(photo.getImage());
                }
                customListPhotoView = new CustomListPhotoView(SightsPhotosActivity.this,photos);
                listView.setAdapter(customListPhotoView);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(SightsPhotosActivity.this,"Something went wrong, please re-try later...",Toast.LENGTH_SHORT).show();
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Wikepedia_Search) {
            Intent intent = new Intent(SightsPhotosActivity.this, WikiActivity.class);
            String city = getIntent().getStringExtra("sightName");
            intent.putExtra("sightName", city);
            startActivity(intent);

        } else if (id == R.id.Back) {
            Intent intent = new Intent(SightsPhotosActivity.this, SightActivity.class);
            startActivity(intent);
        } else if (id == R.id.Close_App) {
            finish();
            moveTaskToBack(true);
        }



        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

