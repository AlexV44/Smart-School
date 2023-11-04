package com.example.clientapp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.clientapp.ChangeNumberItemsListener;
import com.example.clientapp.R;
import com.example.clientapp.adaptor.CartListAdaptor;
import com.example.clientapp.helper.ManagementCart;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView orderPriceText, emptyText;
    private AppCompatButton orderBtn;
    private ScrollView scrollView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        managementCart = new ManagementCart(requireContext());
        initView(view);
        initList();
        calculateCart();

        return view;
    }

    private void initView(View view) {
        recyclerViewList = view.findViewById(R.id.recyclerViewCart);
        orderPriceText = view.findViewById(R.id.orderPriceText);
        emptyText = view.findViewById(R.id.emptyText);
        scrollView = view.findViewById(R.id.scrollViewCart);
        initOrderButton(view);
    }

    private void initOrderButton(View view) {
        orderBtn = view.findViewById(R.id.orderBtn);

        orderBtn.setOnClickListener(v -> OnOrderClick());
    }

    private void OnOrderClick() {

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managementCart.getListCart(), requireContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        BigDecimal bigTotalPrice = BigDecimal.valueOf(managementCart.getTotalPrice());
        orderPriceText.setText(String.valueOf(bigTotalPrice));
    }
}