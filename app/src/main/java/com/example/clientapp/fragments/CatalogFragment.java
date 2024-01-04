package com.example.clientapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.CategoryAdaptor;
import com.example.clientapp.adaptor.ProductAdaptor;
import com.example.clientapp.domain.CategoryDomain;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Product;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewProductsList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatalogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
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
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        recyclerViewCategory(view);
        recyclerViewProducts(view);

        return view;
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.categoriesRecyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Мучное", "cat_3"));
        category.add(new CategoryDomain("Напитки", "cat_4"));
        category.add(new CategoryDomain("Снеки", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewProducts(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewProductsList = view.findViewById(R.id.productsRecyclerView);
        recyclerViewProductsList.setLayoutManager(linearLayoutManager);
        ArrayList<Product> products = new ArrayList<>();
        RetrofitService retrofitService = new RetrofitService();
        SchoolApi schoolApi = retrofitService.getRetrofit().create(SchoolApi.class);
        adapter2 = new ProductAdaptor(products, requireActivity());
        recyclerViewProductsList.setAdapter(adapter2);
        schoolApi.getProducts(UserSessionManager.getInstance().getSmember().getSchoolId()).enqueue(new Callback<List<Product>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products.clear();
                products.addAll(response.body());
                adapter2.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }
}