package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/28/2017.
 */

public class Term {
    private int id;

    private String created;

    private String term;

    @SerializedName("not_match_msg")
    private String notMatchMsg;

    @SerializedName("success_msg")
    private String successMsg;

    public Term(int id, String created, String term, String notMatchMsg, String successMsg) {
        this.id = id;
        this.created = created;
        this.term = term;
        this.notMatchMsg = notMatchMsg;
        this.successMsg = successMsg;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getTerm() {
        return term;
    }

    public String getNotMatchMsg() {
        return notMatchMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }
}
