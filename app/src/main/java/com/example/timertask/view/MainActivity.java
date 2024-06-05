package com.example.timertask.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.timertask.R;
import com.example.timertask.databinding.ActivityMainBinding;
import com.example.timertask.view.fragments.DashboardFragment;
import com.example.timertask.view.fragments.TimerFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new TimerFragment()).commit();

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int mItemId = menuItem.getItemId();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (mItemId == R.id.menu_timer) {
                    fragmentTransaction.replace(R.id.frame_layout,new TimerFragment()).commit();
                    return true;
                } else if (mItemId == R.id.menu_dashboard) {
                    fragmentTransaction.replace(R.id.frame_layout,new DashboardFragment()).commit();
                    return true;
                }

                return false;

            }
        });
    }
}