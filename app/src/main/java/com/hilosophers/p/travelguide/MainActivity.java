package com.hilosophers.p.travelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

                button = findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v)
                {
                        openMap();
                }
        });
    }

             public void openMap()
             {
                Intent intent = new Intent(this,SightsMap.class);
                startActivity(intent);
             }
}
