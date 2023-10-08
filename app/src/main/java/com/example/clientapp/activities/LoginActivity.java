package com.example.clientapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.clientapp.R;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText inputEditTextEmail;
    TextInputEditText inputEditTextPassword;

    AppCompatButton loginButton;

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

    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.button_login);

        retrofitService = new RetrofitService();
        memberApi = retrofitService.getRetrofit().create(MemberApi.class);
    }

}