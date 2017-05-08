package com.example.cuonghq.cleanhome4staff.order.networks.services;


import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public interface GetService {
    @GET("atm/laundry/service")
    Call<List<Service>> getServicesList(@Header("Authorization") String token);

}
