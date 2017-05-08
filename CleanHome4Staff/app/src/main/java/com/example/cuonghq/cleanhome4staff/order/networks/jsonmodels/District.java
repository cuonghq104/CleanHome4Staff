package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class District {

    private int id;

    private String created;

    @SerializedName("delete_flag")
    private String deleteFlag;

    private String name;

    public District(int id, String create, String deleteFlag, String name) {
        this.id = id;
        this.created = create;
        this.deleteFlag = deleteFlag;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCreate() {
        return created;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

