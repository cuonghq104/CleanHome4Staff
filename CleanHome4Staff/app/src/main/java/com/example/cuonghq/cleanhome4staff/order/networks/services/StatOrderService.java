package com.example.cuonghq.cleanhome4staff.order.networks.services;

import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Cuonghq on 5/5/2017.
 */

public interface StatOrderService {

    @POST("atm/laundry/stat_order")
    Call<List<OrderAPIResponse>> action(@Header("Authorization") String token, @Body RequestBody requestBody);
}
