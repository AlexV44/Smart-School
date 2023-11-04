package com.example.clientapp.activities;

import static com.example.clientapp.model.Role.*;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.clientapp.R;
import com.example.clientapp.model.School;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolApi;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    AppCompatButton btnRegister;

    MemberApi memberApi;
    SchoolApi schoolApi;
    private ArrayList<School> schools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getExistedSchools();
        initViews();
    }

    private void getExistedSchools() {
        RetrofitService retrofitService = new RetrofitService();
        SchoolApi schoolApi1;
        schoolApi1 = retrofitService.getRetrofit().create(SchoolApi.class);
        List<School> schools1 = (List<School>) schoolApi1.getAllSchools();
        System.out.println(schools1.get(0));
    }


//        schoolApi = retrofitService.getRetrofit().create(SchoolApi.class);
//        schoolApi.getAllSchools()
//                .enqueue(new Callback<List<School>>() {
//            @Override
//            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
//                List<School> array =  response.body();
//                System.out.println(array.get(0));
//                for (int i = 0; i < response.body().size(); i++) {
//                    if(response.body().get(i) != null) {
//                        schools.add(response.body().get(i));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<School>> call, Throwable t) {
//
//            }
//        });

    private void initViews() {
        tfFio = findViewById(R.id.tfFio);
        tfEmail = findViewById(R.id.tfEmail);
        tfSchoolIdentifier = findViewById(R.id.tfSchoolIdentifier);
        tfPassword = findViewById(R.id.tfPassword);
        tfPassReturn = findViewById(R.id.tfPassReturn);
        // initRegisterButton();
    }

//    private void initRegisterButton() {
//        btnRegister = findViewById(R.id.btnRegister);
//
//        RetrofitService retrofitService = new RetrofitService();
//        memberApi = retrofitService.getRetrofit().create(MemberApi.class);
//
//        btnRegister.setOnClickListener(view -> onRegisterButton());
//    }
//
//    private void onRegisterButton() {
//        String name = String.valueOf(tfFio.getText());
//        String password = String.valueOf(tfPassword.getText());
//        String schoolIdentifier = String.valueOf(tfSchoolIdentifier.getText());
//        String passReturn = String.valueOf(tfPassReturn.getText());
//        String email = String.valueOf(tfEmail.getText());
//
//
////        School school = null;
////        assert schools != null;
////        for (School sch : schools) {
////            if(sch.getIdentifier().equals(schoolIdentifier)) {
////                school = sch;
////            }
////        }
//        School school1 = new School();
//        school1.setIdentifier(schoolIdentifier);
//        school1.setName("2138");
//        Smember member = new Smember();
//        member.setName(name);
//        member.setEmail(email);
//        member.setSchool(school1);
//        member.setRole(ROLE_USER);
//        member.setPassword(password);
//        if(passReturn.equals(password)) {
//        } else {
//            Toast.makeText(RegistrationActivity.this, "Incorrect passwords!", Toast.LENGTH_SHORT).show();
//        }
//
//        memberApi.save(member)
//                .enqueue(new Callback<Smember>() {
//                    @Override
//                    public void onResponse(Call<Smember> call, Response<Smember> response) {
//                        Toast.makeText(RegistrationActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Smember> call, Throwable t) {
//                        Toast.makeText(RegistrationActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
//                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred");
//                    }
//                });
//    }
}


