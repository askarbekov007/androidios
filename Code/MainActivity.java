package com.example.myvk4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private CustomViewPager pager;
    private PagerAdapter pagerAdapter;
    HomeFragment homeFragment;
    FavouritesFragment favouritesFragment;
    FragmentTransaction transaction;
    List<Fragment> list = new ArrayList<>();
    boolean favClicked = false;
    private static final String TAG = "MyActivity";
    public void onBackPressed() {
        MainActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(homeFragment);
        list.add(favouritesFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setPagingEnabled(true);
        pager.setAdapter(pagerAdapter);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            pagerAdapter.notifyDataSetChanged();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0, false);
                    favClicked = false;
                    break;
                case R.id.navigation_favourites:
                    pager.setCurrentItem(1, false);
                    favClicked = true;
                    break;
            }
            return true;
        }
    };

}
