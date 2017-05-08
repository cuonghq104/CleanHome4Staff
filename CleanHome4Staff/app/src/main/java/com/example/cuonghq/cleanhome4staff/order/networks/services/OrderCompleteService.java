package com.example.cuonghq.cleanhome4staff.order.networks.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public interface OrderCompleteService {

    @GET("atm/laundry/done")
    Call<String> completingOrder(@Query("order") int order, @Header("Authorization") String token);
}
