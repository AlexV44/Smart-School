package com.example.clientapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.activities.ProductsInStockActivity;
import com.example.clientapp.activities.StaffLunchRequests;
import com.example.clientapp.activities.StaffOrders;
import com.example.clientapp.adaptor.OrderAdaptor;
import com.example.clientapp.adaptor.SchoolProductQuantityAdaptor;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.LunchRequest;
import com.example.clientapp.model.Product;
import com.example.clientapp.model.Role;
import com.example.clientapp.model.School;
import com.example.clientapp.model.SchoolProduct;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.LunchRequestApi;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.ProductApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolApi;
import com.example.clientapp.retrofit.SchoolProductApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    private TextView orderText, editTextNumberDecimal, editTextStudentsCount, editTextFreeStudentsCount, lunchRequestsText, mainTitle, productsInStock;
    private Button setStaffBtn, lunchRequestBtn;
    private Spinner rolesSpinner, classNumberSpinner, classLetterSpinner;
    private ConstraintLayout constraintLayoutRequest, constraintLayoutSetStaff, constraintLayoutStaffInfo;

    private String [] roles = {"Учитель", "Буфетчик"};
    private String [] classes = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private String [] letters = {"А", "Б", "В", "Г", "Д"};
    private String roleItem;
    private String classNumberItem;
    private String classLetterItem;
    private String className;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OtherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        orderText = view.findViewById(R.id.ordersText);
        editTextNumberDecimal = view.findViewById(R.id.editTextId);
        setStaffBtn = view.findViewById(R.id.btnSetStaff);
        editTextFreeStudentsCount = view.findViewById(R.id.editTextFreeStudentsCount);
        editTextStudentsCount = view.findViewById(R.id.editTextStudentsCount);
        lunchRequestBtn = view.findViewById(R.id.lunchRequestBtn);
        constraintLayoutRequest = view.findViewById(R.id.constraintLayoutRequest);
        constraintLayoutStaffInfo = view.findViewById(R.id.constraintLayoutStaffInfo);
        constraintLayoutSetStaff = view.findViewById(R.id.constraintLayoutSetStaff);
        lunchRequestsText = view.findViewById(R.id.lunchRequestsText);
        productsInStock = view.findViewById(R.id.productsInStock);
        mainTitle = view.findViewById(R.id.mainTitle);

        classNumberSpinner = view.findViewById(R.id.classNumberSpinner);
        classLetterSpinner = view.findViewById(R.id.classLetterSpinner);
        rolesSpinner = view.findViewById(R.id.rolesSpinner);

        ArrayAdapter<String> classNumberAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, classes);
        classNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classNumberSpinner.setAdapter(classNumberAdapter);
        classNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classNumberItem = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> classLetterAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, letters);
        classLetterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classLetterSpinner.setAdapter(classLetterAdapter);
        classLetterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classLetterItem = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rolesSpinner.setAdapter(adapter);
        rolesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleItem = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setViewsVisibility();
    }

    private void setViewsVisibility() {
        RetrofitService retrofitService = new RetrofitService();
        MemberApi memberApi = retrofitService.getRetrofit().create(MemberApi.class);
        LunchRequestApi lunchApi = retrofitService.getRetrofit().create(LunchRequestApi.class);
        constraintLayoutRequest.setVisibility(View.GONE);
        constraintLayoutStaffInfo.setVisibility(View.GONE);
        constraintLayoutSetStaff.setVisibility(View.GONE);

        if (getActivity().getIntent().getStringExtra("role").equals(Role.USER.toString())) {
            mainTitle.setText("");
        }

        if (getActivity().getIntent().getStringExtra("role").equals(Role.STAFF.toString())) {
            mainTitle.setText("Питание");
            constraintLayoutStaffInfo.setVisibility(View.VISIBLE);
            orderText.setOnClickListener(v -> onOrdersList());
            lunchRequestsText.setOnClickListener(v -> onWatchLunchRequests());
            productsInStock.setOnClickListener(v -> onCheckProductsInStock());
        }
        if (getActivity().getIntent().getStringExtra("role").equals(Role.TEACHER.toString())) {
            mainTitle.setText("Назначение старосты");
            constraintLayoutSetStaff.setVisibility(View.VISIBLE);
            rolesSpinner.setVisibility(View.GONE);
            setStaffBtn.setOnClickListener(v -> onSetElderBtn(memberApi));
        }
        if(getActivity().getIntent().getStringExtra("role").equals(Role.DIRECTOR.toString())) {
            mainTitle.setText("Назначение персонала");
            constraintLayoutSetStaff.setVisibility(View.VISIBLE);
            setStaffBtn.setOnClickListener(v -> onSetMainRolesBtn(memberApi));
        }
        if(getActivity().getIntent().getStringExtra("role").equals(Role.ELDER.toString())) {
            mainTitle.setText("Оформление заявки");
            constraintLayoutRequest.setVisibility(View.VISIBLE);
            lunchRequestBtn.setOnClickListener(v -> onSetLunchRequestBtn(lunchApi));
        }
    }

    private void onCheckProductsInStock() {
        Intent intent = new Intent(requireContext(), ProductsInStockActivity.class);
        startActivity(intent);
    }

    private void onWatchLunchRequests() {
        Intent intent = new Intent(requireContext(), StaffLunchRequests.class);
        startActivity(intent);
    }

    public String getMoscowTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat moscowTime = new SimpleDateFormat("HH:mm:ss");
        moscowTime.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return moscowTime.format(new Date());
    }

    private void onSetLunchRequestBtn(LunchRequestApi lunchRequestApi) {
        LunchRequest lunchRequest = new LunchRequest();
        className = classNumberItem + "\"" + classLetterItem + "\"";
        if(editTextStudentsCount.getText().toString().equals("") || editTextFreeStudentsCount.getText().toString().equals("") || className.equals("")) {
        } else {
            int studentsCount = Integer.parseInt(editTextStudentsCount.getText().toString());
            int freeStudentsCount = Integer.parseInt(editTextFreeStudentsCount.getText().toString());
            lunchRequest.setClassName(className);
            lunchRequest.setStudentsQuantity(studentsCount);
            lunchRequest.setFreeStudentsQuantity(freeStudentsCount);
            lunchRequest.setRequestTime(getMoscowTime());
            lunchRequest.setSchoolId(UserSessionManager.getInstance().getSmember().getSchoolId());
            sentLunchRequest(lunchRequest, lunchRequestApi);
        }
    }

    private void onSetElderBtn(MemberApi memberApi) {
        if(editTextNumberDecimal.getText().toString().equals("")) {
        } else {
            int id = Integer.parseInt(editTextNumberDecimal.getText().toString());
            if(!(UserSessionManager.getInstance().getSmember().getId() == id)) {
                setElder(id, memberApi);
            } else {
                Toast.makeText(requireContext(), "Нельзя назначить самого себя!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onSetMainRolesBtn(MemberApi memberApi) {
        switch (roleItem) {
            case "Буфетчик":
                if(editTextNumberDecimal.getText().toString().equals("")) {
                } else {
                    int id = Integer.parseInt(editTextNumberDecimal.getText().toString());
                    if(!(UserSessionManager.getInstance().getSmember().getId() == id)) {
                        setStaff(id, memberApi);
                    } else {
                        Toast.makeText(requireContext(), "Нельзя назначить самого себя!", Toast.LENGTH_SHORT).show();
                    }
                }
            case "Учитель":
                if(editTextNumberDecimal.getText().toString().equals("")) {
                } else {
                    int id = Integer.parseInt(editTextNumberDecimal.getText().toString());
                    if(!(UserSessionManager.getInstance().getSmember().getId() == id)) {
                        setTeacher(id, memberApi);
                    } else {
                        Toast.makeText(requireContext(), "Нельзя назначить самого себя!", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private void onOrdersList() {
        Intent intent = new Intent(requireContext(), StaffOrders.class);
        startActivity(intent);
    }

    private void setStaff(int id, MemberApi memberApi) {
        memberApi.setStaffById(id).enqueue(new Callback<Smember>() {
            @Override
            public void onResponse(Call<Smember> call, Response<Smember> response) {
                    Toast.makeText(requireContext(), "Сотрудник назначен!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Smember> call, Throwable t) {
                Toast.makeText(requireContext(), "Сотрудник уже назначен.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTeacher(int id, MemberApi memberApi) {
        memberApi.setTeacherById(id).enqueue(new Callback<Smember>() {
            @Override
            public void onResponse(Call<Smember> call, Response<Smember> response) {
                Toast.makeText(requireContext(), "Учитель назначен!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Smember> call, Throwable t) {
                Toast.makeText(requireContext(), "Учитель уже назначен.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setElder(int id, MemberApi memberApi) {
        memberApi.setElderById(id).enqueue(new Callback<Smember>() {
            @Override
            public void onResponse(Call<Smember> call, Response<Smember> response) {
                Toast.makeText(requireContext(), "Староста назначен!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Smember> call, Throwable t) {
                Toast.makeText(requireContext(), "Староста уже назначен.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sentLunchRequest(LunchRequest lunchRequest, LunchRequestApi lunchRequestApi) {
        lunchRequestApi.takeLunchRequest(lunchRequest).enqueue(new Callback<LunchRequest>() {
            @Override
            public void onResponse(Call<LunchRequest> call, Response<LunchRequest> response) {
                Toast.makeText(requireContext(), "Заявка отправлена!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LunchRequest> call, Throwable t) {
                Toast.makeText(requireContext(), "Повторите попытку.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}