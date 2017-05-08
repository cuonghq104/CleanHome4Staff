package com.example.cuonghq.cleanhome4staff.order.models;

//import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/20/2017.
 */

public class OrderSubservice {

//    @SerializedName("sub_service_id")
    private int id;

    private int quantity;

//    @SerializedName("branch")
    private int brand;

    public OrderSubservice(int id, int quantity, int brand) {
        this.id = id;
        this.quantity = quantity;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "OrderSubservice{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", brand=" + brand +
                '}';
    }
}
