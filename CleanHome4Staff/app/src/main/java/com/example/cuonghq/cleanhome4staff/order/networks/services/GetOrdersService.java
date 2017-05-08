package com.example.cuonghq.cleanhome4staff.order.networks.services;


import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public interface GetOrdersService {

    @GET("atm/laundry/order")
    Call<List<OrderAPIResponse>> getList(@Query("service") int service, @Header("Authorization") String token);

}
