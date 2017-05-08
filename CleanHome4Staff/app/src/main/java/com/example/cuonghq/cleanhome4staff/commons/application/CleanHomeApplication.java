package com.example.cuonghq.cleanhome4staff.commons.application;

import android.app.Application;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static com.example.cuonghq.cleanhome4staff.commons.utils.Constant.SHARED_PREFERENCES;
import static com.example.cuonghq.cleanhome4staff.commons.utils.Utils.retrofit;
import static com.example.cuonghq.cleanhome4staff.commons.utils.Constant.sharedPreferences;

/**
 * Created by Cuonghq on 4/29/2017.
 */

public class CleanHomeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://42.112.30.73/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

    }
}
