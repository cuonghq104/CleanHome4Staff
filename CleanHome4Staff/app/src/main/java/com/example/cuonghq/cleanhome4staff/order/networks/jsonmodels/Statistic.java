package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/28/2017.
 */

public class Statistic {
    private int id;

    @SerializedName("total_order")
    private int totalOrder;

    @SerializedName("total_revenue")
    private int totalRevenue;

    @SerializedName("total_success_order")
    private int totalSuccessOrder;

    @SerializedName("total_success_revenue")
    private int totalSuccessRevenue;

    @SerializedName("total_cancel_order")
    private int totalCancelOrder;

    @SerializedName("total_cancel_revenue")
    private int totalCancelRevenue;

    public Statistic(int id, int totalOrder, int totalRevenue, int totalSuccessOrder, int totalSuccessRevenue, int totalCancelOrder, int totalCancelRevenue) {
        this.id = id;
        this.totalOrder = totalOrder;
        this.totalRevenue = totalRevenue;
        this.totalSuccessOrder = totalSuccessOrder;
        this.totalSuccessRevenue = totalSuccessRevenue;
        this.totalCancelOrder = totalCancelOrder;
        this.totalCancelRevenue = totalCancelRevenue;
    }

    public int getId() {
        return id;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public int getTotalSuccessOrder() {
        return totalSuccessOrder;
    }

    public int getTotalSuccessRevenue() {
        return totalSuccessRevenue;
    }

    public int getTotalCancelOrder() {
        return totalCancelOrder;
    }

    public int getTotalCancelRevenue() {
        return totalCancelRevenue;
    }
}
