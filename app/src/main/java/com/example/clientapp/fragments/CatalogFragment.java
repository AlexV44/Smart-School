package com.example.clientapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.CategoryAdaptor;
import com.example.clientapp.adaptor.ProductAdaptor;
import com.example.clientapp.databinding.ActivityMainBinding;
import com.example.clientapp.domain.CategoryDomain;
import com.example.clientapp.model.Product;

import java.util.ArrayList;

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
    ActivityMainBinding binding;
    RecyclerView.Adapter adapter, adapter2;
    RecyclerView recyclerViewCategoryList, recyclerViewProductsList;

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
        // Inflate the layout for this fragment
        return view;
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.categoriesRecyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewProducts(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewProductsList = view.findViewById(R.id.productsRecyclerView);
        recyclerViewProductsList.setLayoutManager(linearLayoutManager);

        ArrayList<Product> productsList = new ArrayList<>();
        productsList.add(new Product("Pepperoni pizza", "pop_1", "Slices peperoni, cheese, tomatoes, oregano, chicken", 9.76));
        productsList.add(new Product("Burger", "pop_2", "Cheese, sauce", 8.34));
        productsList.add(new Product("Vegetable pizza", "pop_3", "Cheese, olive oil", 10.));

        adapter2 = new ProductAdaptor(productsList);
        recyclerViewProductsList.setAdapter(adapter2);
    }
}