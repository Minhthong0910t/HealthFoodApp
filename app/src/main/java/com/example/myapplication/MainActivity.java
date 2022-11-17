package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.view.View;


import com.example.myapplication.FRAGMENT.AddLoaiSanPham;
import com.example.myapplication.FRAGMENT.AddSanPhamFragment;
import com.example.myapplication.FRAGMENT.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
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
                        fragment = new AddSanPhamFragment();
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