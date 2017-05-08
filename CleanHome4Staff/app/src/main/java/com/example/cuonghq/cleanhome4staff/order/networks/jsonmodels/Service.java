package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class Service {

    private int id;

    private String created;

    @SerializedName("delete_flag")
    private boolean deleteFlag;

    private String name;

    private int type;

    public Service(int id, String created, boolean deleteFlag, String name, int type) {
        this.id = id;
        this.created = created;
        this.deleteFlag = deleteFlag;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
