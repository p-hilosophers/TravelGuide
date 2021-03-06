package com.hilosophers.p.travelguide.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hilosophers.p.travelguide.R;

public class WikiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        String city = getIntent().getStringExtra("cityName");

        String cityy = getIntent().getStringExtra("sightName");

        WebView wb =(WebView)findViewById(R.id.web);
        wb.setWebViewClient(new WebViewClient());
        if (city!=null) {
            wb.loadUrl("https://en.wikipedia.org/wiki/"+city);
        }else{
            wb.loadUrl("https://en.wikipedia.org/wiki/"+cityy);
        }

    }
}
