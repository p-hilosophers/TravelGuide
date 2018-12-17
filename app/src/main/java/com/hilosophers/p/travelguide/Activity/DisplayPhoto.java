package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.hilosophers.p.travelguide.Adapter.CustomListPhotoView;
import com.hilosophers.p.travelguide.Model.Photo;
import com.hilosophers.p.travelguide.Model.Sight;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.SightClient;
import com.hilosophers.p.travelguide.Services.RequestService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DisplayPhoto extends AppCompatActivity {
    ImageView DisplayimageView;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);
        DisplayimageView= findViewById(R.id.DisplayimageView);


        String imgURL;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                imgURL = null;
            } else {
                imgURL = extras.getString("image_url");
            }
        } else {
            imgURL = (String) savedInstanceState.getSerializable("image_url");
        }
        Picasso.with(this).load(imgURL).into(DisplayimageView);




    }
}