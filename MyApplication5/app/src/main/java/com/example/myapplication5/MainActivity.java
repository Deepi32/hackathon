package com.example.myapplication5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void switchToNextTab(int fromTab) {
        Log.d("MainActivity", "switchToNextTab, fromTab: " + fromTab);

        switch (fromTab) {
            case R.id.navigation_home:
                navController.navigate(R.id.navigation_dashboard);
                break;
            case R.id.navigation_dashboard:
                navController.navigate(R.id.navigation_notifications);
                break;
            case R.id.navigation_notifications:
                navController.navigate(R.id.navigation_dashboard);
                break;
        }
    }

    public void saveBookingData() {}

    public void saveSimData() {}

    public void saveUploadDocumentsData() {}
}
