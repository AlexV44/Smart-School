package com.example.clientapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientapp.R;
import com.example.clientapp.activities.StaffOrders;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Role;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    private TextView orderText, editTextNumberDecimal;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewOrdersList;
    private List<Order> orders;
    private Button btnSetStaff;

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
        btnSetStaff = view.findViewById(R.id.btnSetStaff);

        orderText.setVisibility(View.GONE);
        editTextNumberDecimal.setVisibility(View.GONE);
        btnSetStaff.setVisibility(View.GONE);
        if (getActivity().getIntent().getStringExtra("role").equals(Role.STAFF.toString())) {
            orderText.setVisibility(View.VISIBLE);
            orderText.setOnClickListener(v -> OnOrdersList());
        }
        if (getActivity().getIntent().getStringExtra("role").equals(Role.DIRECTOR.toString())) {
            editTextNumberDecimal.setVisibility(View.VISIBLE);
            btnSetStaff.setVisibility(View.VISIBLE);
            btnSetStaff.setOnClickListener(v -> onSetStaffBtn());
        }
    }

    private void onSetStaffBtn() {
        if(editTextNumberDecimal.getText().toString().equals("")) {
        } else {
            int id = Integer.parseInt(editTextNumberDecimal.getText().toString());
            if(!(UserSessionManager.getInstance().getSmember().getId() == id)) {
                setStaff(id);
            }
            Toast.makeText(requireContext(), "Нельзя назначить самого себя!", Toast.LENGTH_SHORT).show();
        }
    }

    private void OnOrdersList() {
        Intent intent = new Intent(requireContext(), StaffOrders.class);
        startActivity(intent);
    }

    private void setStaff(int id) {
        RetrofitService retrofitService = new RetrofitService();
        MemberApi memberApi = retrofitService.getRetrofit().create(MemberApi.class);
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
}