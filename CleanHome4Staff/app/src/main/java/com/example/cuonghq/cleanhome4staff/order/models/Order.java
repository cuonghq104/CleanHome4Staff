package com.example.cuonghq.cleanhome4staff.order.models;

//import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Cuonghq on 4/20/2017.
 */

public class Order {

//    @SerializedName("atm_id")
    private int atmId;

    private String address;

//    @SerializedName("owner_name")
    private String ownerName;

    private int type;

//    @SerializedName("phone_number")
    private String phone;

//    @SerializedName("service_id")
    private int serviceId;

    private int price;

    private String note;

//    @SerializedName("price_unit")
    private String priceUnit;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrice_unit() {
        return priceUnit;
    }

    public void setPrice_unit(String price_unit) {
        this.priceUnit = price_unit;
    }

    private OrderSubservice[] subservice;

    public Order() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Order(int atmId, String address, String ownerName, int type, String phone, int serviceId, OrderSubservice[] subservice) {
        this.atmId = atmId;
        this.address = address;
        this.ownerName = ownerName;
        this.type = type;
        this.phone = phone;
        this.serviceId = serviceId;
        this.subservice = subservice;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setSubservice(OrderSubservice[] subservice) {
        this.subservice = subservice;
    }

    public int getAtmId() {
        return atmId;
    }

    public String getAddress() {
        return address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public int getServiceId() {
        return serviceId;
    }

    public OrderSubservice[] getSubservice() {
        return subservice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "atmId=" + atmId +
                ", address='" + address + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", serviceId=" + serviceId +
                ", price=" + price +
                ", note='" + note + '\'' +
                ", priceUnit='" + priceUnit + '\'' +
                ", subservice=" + Arrays.toString(subservice) +
                '}';
    }
}
