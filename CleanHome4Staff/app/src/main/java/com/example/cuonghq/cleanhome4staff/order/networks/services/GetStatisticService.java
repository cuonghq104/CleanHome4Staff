package com.example.cuonghq.cleanhome4staff.order.networks.services;


import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Statistic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cuonghq on 4/28/2017.
 */

public interface GetStatisticService {

    @GET("atm/laundry/report")
    Call<List<Statistic>> getStatistic(@Query("by") String unit, @Header("Authorization") String token);
}
