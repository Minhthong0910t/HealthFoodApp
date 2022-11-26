package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;


import com.bumptech.glide.Glide;
import com.example.myapplication.FRAGMENT.AddLoaiSanPham;

import com.example.myapplication.MODEL.FRAGMENT.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public  ChipNavigationBar chipNavigationBar;
    FragmentManager fragmentManager;



    DrawerLayout mdrawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hover();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.naHostFratment, new HomeFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.maps:
//                        fragment = new AddSanPhamFragment();
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                    case R.id.profile:
                        fragment = new AddLoaiSanPham();
                        break;


                }
                if(fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.naHostFratment, fragment).commit();
                }
            }
        });
        findViewById(R.id.immenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // navigation
        navigationView.setItemIconTintList(null);
         NavController navController = Navigation.findNavController(this, R.id.naHostFratment);
        NavigationUI.setupWithNavController(navigationView, navController);

    }


    private void hover(){

        chipNavigationBar = findViewById(R.id.chipNavigationbar);
        mdrawerLayout = findViewById(R.id.drawlayout);
        navigationView = findViewById(R.id.id_navigtion);

    }

}