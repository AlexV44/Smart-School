package com.example.clientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.clientapp.R;
import com.example.clientapp.model.SignupRequest;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText tfFio;
    TextInputEditText tfEmail;
    TextInputEditText tfSchoolIdentifier;
    TextInputEditText tfPassword;
    TextInputEditText tfPassReturn;

    Button btnToLogin;

    AppCompatButton btnRegister;

    MemberApi memberApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    private void initViews() {
        tfFio = findViewById(R.id.tfName);
        tfEmail = findViewById(R.id.tfEmail);
        tfSchoolIdentifier = findViewById(R.id.tfSchoolIdentifier);
        tfPassword = findViewById(R.id.tfPassword);
        tfPassReturn = findViewById(R.id.tfPassReturn);
        initRegisterButton();
        initBtnToLogin();
    }

    private void initBtnToLogin() {
        btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initRegisterButton() {
        btnRegister = findViewById(R.id.btnRegister);

        RetrofitService retrofitService = new RetrofitService();
        memberApi = retrofitService.getRetrofit().create(MemberApi.class);

        btnRegister.setOnClickListener(view -> onRegisterButton());
    }

    private void onRegisterButton() {
        String name = String.valueOf(tfFio.getText());
        String password = String.valueOf(tfPassword.getText());
        String schoolIdentifier = String.valueOf(tfSchoolIdentifier.getText());
        String passReturn = String.valueOf(tfPassReturn.getText());
        String email = String.valueOf(tfEmail.getText());

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setName(name);
        signupRequest.setEmail(email);
        signupRequest.setSchoolIdentifier(schoolIdentifier);

        if(!password.isEmpty() && passReturn.equals(password) && !name.isEmpty() && !email.isEmpty() && !schoolIdentifier.isEmpty()) {
            signupRequest.setPassword(password);
            memberApi.signup(signupRequest)
                    .enqueue(new Callback<Smember>() {
                        @Override
                        public void onResponse(Call<Smember> call, Response<Smember> response) {
                            Toast.makeText(RegistrationActivity.this, "Регистация успешна.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Smember> call, Throwable t) {
                            Toast.makeText(RegistrationActivity.this, "Повторите попытку.", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Ошибка 888.");
                        }
                    });
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(RegistrationActivity.this, "Введите данные корректно!", Toast.LENGTH_SHORT).show();
        }
    }
}


