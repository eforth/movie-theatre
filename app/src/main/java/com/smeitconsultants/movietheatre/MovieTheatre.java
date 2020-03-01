package com.smeitconsultants.movietheatre;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class MovieTheatre extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
