package com.hilosophers.p.travelguide.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hilosophers.p.travelguide.DataValidation;
import com.hilosophers.p.travelguide.Model.User;
import com.hilosophers.p.travelguide.R;
import com.hilosophers.p.travelguide.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private DataValidation dataVal = new DataValidation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");

        final EditText name = findViewById(R.id.editTextName);
        final EditText surname = findViewById(R.id.editTextSurname);
        final EditText email = findViewById(R.id.editTextEmail);
        final EditText password = findViewById(R.id.editTextPassword);
        final EditText confirmPassword = findViewById(R.id.editTextConfirm);

        Button registerBtn = findViewById(R.id.buttonRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString()
                );
                if(dataVal.checkValidation(
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        confirmPassword.getText().toString()))
                {
                    sendNetworkRequest(user);
                }else{
                    Toast.makeText(RegisterActivity.this, dataVal.checkWarningStatus(""),Toast.LENGTH_SHORT).show();
                    dataVal.clearMessage();
                }
            }
        });
    }

    private void sendNetworkRequest(User user) {
        final TextView text = findViewById(R.id.text);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.0.3:8080/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterActivity.this, "You " + response.body().getName() + " successfully made an account !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Something went wrong .", Toast.LENGTH_SHORT).show();
            }
        });
    }
}