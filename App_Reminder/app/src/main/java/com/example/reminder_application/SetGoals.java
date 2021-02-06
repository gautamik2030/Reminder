package com.example.reminder_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SetGoals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goals);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();



    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.item1:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.item2:
                    selectedFragment = new DrinkFragment();
                    break;

                case R.id.item3:
                    selectedFragment = new BreakFragment();
                    break;

                case R.id.item4:
                    selectedFragment = new SettingsFragment();
                    break;
            }


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();


            return true;
        }
    };
}