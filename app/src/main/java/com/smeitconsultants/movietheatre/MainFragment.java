package com.smeitconsultants.movietheatre;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;


    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        navController = Navigation.findNavController(view.findViewById(R.id.nav_host_main));

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        navController.navigate(R.id.homeFragment);
                        return true;
                    case R.id.action_feed:
                        navController.navigate(R.id.homeFragment);
                        //return true;
                    case R.id.action_account:
                        navController.navigate(R.id.homeFragment);
                        //return true;
                    case R.id.action_store:
                        navController.navigate(R.id.homeFragment);
                        //return true;
                }
                return false;
            }
        });
    }

}
