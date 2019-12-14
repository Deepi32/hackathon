package com.example.myapplication5.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyBottomNavigationView extends BottomNavigationView {
    public MyBottomNavigationView(Context context) {
        super(context);
    }

    public MyBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void selectMenuItem(MenuItem item) {
        //this.selectedListener
    }
}
