package com.example.clientapp.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.CategoryAdaptor;
import com.example.clientapp.databinding.ActivityMainBinding;
import com.example.clientapp.domain.CategoryDomain;
import com.example.clientapp.fragments.CartFragment;
import com.example.clientapp.fragments.CatalogFragment;
import com.example.clientapp.fragments.OtherFragment;
import com.example.clientapp.fragments.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CatalogFragment());
        initViews();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initViews() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.catalog:
                    replaceFragment(new CatalogFragment());
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.other:
                    replaceFragment(new OtherFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }
}