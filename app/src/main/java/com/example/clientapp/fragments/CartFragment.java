package com.example.clientapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.ChangeNumberItemsListener;
import com.example.clientapp.R;
import com.example.clientapp.adaptor.CartListAdaptor;
import com.example.clientapp.helper.ManagementCart;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Product;
import com.example.clientapp.retrofit.MemberApi;
import com.example.clientapp.retrofit.OrderApi;
import com.example.clientapp.retrofit.RetrofitService;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView orderPriceText, emptyText, balanceTxt;
    private AppCompatButton orderBtn;
    private ScrollView scrollView;
    private OrderApi orderApi;
    private MemberApi memberApi;
    private double balance;
    private final double orderLimit = 15.;

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
        balance = UserSessionManager.getInstance().getSmember().getBalance();
        recyclerViewList = view.findViewById(R.id.recyclerViewCart);
        orderPriceText = view.findViewById(R.id.orderPriceText);
        emptyText = view.findViewById(R.id.emptyText);
        scrollView = view.findViewById(R.id.scrollViewCart);
        balanceTxt = view.findViewById(R.id.balance);
        balanceTxt.setText(String.valueOf(balance));
        initOrderButton(view);
    }

    private void initOrderButton(View view) {
        orderBtn = view.findViewById(R.id.orderBtn);

        RetrofitService retrofitService = new RetrofitService();
        orderApi = retrofitService.getRetrofit().create(OrderApi.class);
        memberApi = retrofitService.getRetrofit().create(MemberApi.class);
        orderBtn.setOnClickListener(v -> OnOrderClick());
    }

    private void OnOrderClick() {
        if(managementCart.getTotalPrice() < orderLimit) {
            if (balance >= managementCart.getTotalPrice()) {
                List<Product> orderProducts = managementCart.getListCart();
                Order order = new Order();
                order.setProducts(orderProducts);
                order.setMemberId(UserSessionManager.getInstance().getSmember().getId());
                order.setSchoolId(UserSessionManager.getInstance().getSmember().getSchoolId());
                orderApi.takeorder(order).enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        Toast.makeText(requireContext(), "Заказ оформлен!", Toast.LENGTH_SHORT).show();
                        BigDecimal bigDecimalBalance = BigDecimal.valueOf(balance);
                        BigDecimal bigDecimalCost = BigDecimal.valueOf(managementCart.getTotalPrice());
                        BigDecimal bigDecimalResult = bigDecimalBalance.subtract(bigDecimalCost);
                        balanceTxt.setText(String.valueOf(bigDecimalResult));
                        UserSessionManager.getInstance().getSmember().setBalance(bigDecimalResult.doubleValue());
                        balance = UserSessionManager.getInstance().getSmember().getBalance();
                        updateBalance();
                    }
                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {

                    }
                });

            } else {
                Toast.makeText(requireContext(), "Недостаточно средств.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "Превышен лимит.", Toast.LENGTH_SHORT).show();
        }
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

    private void updateBalance() {
        memberApi.updateUserBalance(UserSessionManager.getInstance().getSmember()).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {

            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

            }
        });
    }
}