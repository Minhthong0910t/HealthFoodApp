package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;


import com.example.myapplication.FRAGMENT.AddLoaiSanPham;
import com.example.myapplication.FRAGMENT.AddSanPhamFragment;
import com.example.myapplication.FRAGMENT.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hover();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
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
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }
            }
        });

    }
    private void hover(){
        //  ed_search_main = findViewById(R.id.ed_search);

        // recyclerView = findViewById(R.id.recyrcle_danhSachSp_horizontal);
        //recyclerViewFavorite  = findViewById(R.id.recyrcle_danhSachSp_favourite);
        chipNavigationBar = findViewById(R.id.chipNavigationbar);


    }

}