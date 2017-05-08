package com.example.cuonghq.cleanhome4staff.order.networks.services;



import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Term;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cuonghq on 4/28/2017.
 */

public interface GetTermService {

    @GET("atm/laundry/master/term")
    Call<List<Term>> getTerm();
}
