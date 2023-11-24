package com.example.clientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.clientapp.R;
import com.example.clientapp.model.LoginRequest;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText inputEditTextEmail;
    TextInputEditText inputEditTextPassword;

    AppCompatButton loginButton;
    Button btnGoRegister;

    RetrofitService retrofitService;
    MemberApi memberApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        inputEditTextEmail = findViewById(R.id.textInputEmail);
        inputEditTextPassword = findViewById(R.id.textInputPassword);

        initLoginButton();
        initBtnGoRegister();
    }

    private void initBtnGoRegister() {
        btnGoRegister = findViewById(R.id.btnGoRegister);
        btnGoRegister.setOnClickListener(view -> OnGoToRegisterActivity());
    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.button_login);

        retrofitService = new RetrofitService();
        memberApi = retrofitService.getRetrofit().create(MemberApi.class);

        loginButton.setOnClickListener(view -> OnUserLogin());
    }

    private void OnUserLogin() {
        String email = String.valueOf(inputEditTextEmail.getText());
        String password = String.valueOf(inputEditTextPassword.getText());
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        memberApi.login(request).enqueue(new Callback<Smember>() {
            @Override
            public void onResponse(Call<Smember> call, Response<Smember> response) {
                if(response.body() == null) {
                    Toast.makeText(LoginActivity.this, "Неверный email или пароль.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Вход выполнен успешно!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Smember> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Неверный email или пароль.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnGoToRegisterActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}