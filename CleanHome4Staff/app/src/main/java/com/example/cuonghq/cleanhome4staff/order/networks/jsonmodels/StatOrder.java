package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 5/4/2017.
 */

public class StatOrder {
    private String action;

    @SerializedName("order_id")
    private int orderId;

    public StatOrder(String action, int orderId) {
        this.action = action;
        this.orderId = orderId;
    }

    public String getAction() {
        return action;
    }

    public int getOrderId() {
        return orderId;
    }
}
