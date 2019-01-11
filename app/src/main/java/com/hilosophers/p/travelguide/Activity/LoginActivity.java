package com.hilosophers.p.travelguide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.Authentication.EncryptService;
import com.hilosophers.p.travelguide.Model.User;
import com.hilosophers.p.travelguide.Model.Version;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.Repository.UserClient;
import com.hilosophers.p.travelguide.Repository.VersionClient;
import com.hilosophers.p.travelguide.Services.RequestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        final TextView version = findViewById(R.id.version);
        final EditText email = findViewById(R.id.editTextEmail);
        final EditText password = findViewById(R.id.editTextPassword);
        final Button login = findViewById(R.id.buttonLogin);
        final TextView registerLink = findViewById(R.id.textViewRegisterHere);


        Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl("https://api.github.com/")
                                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        VersionClient client = retrofit.create(VersionClient.class);
        Call<Version> call = client.getLatestVersion("p-hilosophers","TravelGuide");

        call.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {

                    Version repo = response.body();
                    version.setText(repo.getVersion());
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                    version.setText("version");
            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(email.getText().toString().equals("") || password.getText().toString().equals(""))) {
                    Retrofit retrofit = RequestService.initializeRequest().build();
                    UserClient client = retrofit.create(UserClient.class);
                    Call<User> call = client.userLogin(email.getText().toString(), EncryptService.encryptPassword(password.getText().toString()));
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Intent areaIntent = new Intent(LoginActivity.this, CityActivity.class);
                            LoginActivity.this.startActivity(areaIntent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "The inserted e-mail or password is incorrect !", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Please fill in your credentials .", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
