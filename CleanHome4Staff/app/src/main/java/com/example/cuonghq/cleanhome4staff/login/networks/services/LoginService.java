package com.example.cuonghq.cleanhome4staff.login.networks.services;


import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.LoginResponseBody;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public interface LoginService {
    @POST("atm/laundry/login")
    Call<LoginResponseBody> login(@Body RequestBody requestBody);
}
