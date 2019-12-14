package com.example.myapplication5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication5.model.BookingDetails;
import com.example.myapplication5.model.FileDetails;
import com.example.myapplication5.model.SimDetails;
import com.example.myapplication5.model.VisaDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    NavController navController;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
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
                navController.navigate(R.id.navigation_payment);
                break;
            case R.id.navigation_payment:
                navController.navigate(R.id.navigation_summary);
                break;
            case R.id.navigation_summary:
                //navController.navigate(R.id.navigation_summary);
                break;
        }
    }

    public void saveBookingData(BookingDetails bookingDetails) {
        mainViewModel.setBookingDetails(bookingDetails);
    }

    public BookingDetails getBookingDetails() {
        return mainViewModel.getBookingDetails().getValue();
    }

    public void saveSimData(SimDetails simDetails) {
        mainViewModel.setSelectedSim(simDetails);
    }

    public SimDetails getSimDetails() {
        return mainViewModel.getSelectedSim().getValue();
    }

    public void saveUploadDocumentsData(VisaDetails visa, FileDetails idFile, FileDetails photFile)
    {
        mainViewModel.setVisaDetails(visa);
        mainViewModel.setIdFile(idFile);
        mainViewModel.setPhotoFile(photFile);
    }

    public void savePaymentDetails(boolean isPaymentDone, String paymentResult) {
        mainViewModel.setIsPaymentDone(isPaymentDone);
        mainViewModel.setPayResult(paymentResult);
    }
}
