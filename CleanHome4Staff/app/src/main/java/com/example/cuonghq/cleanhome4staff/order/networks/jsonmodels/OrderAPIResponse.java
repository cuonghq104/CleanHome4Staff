package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.LoginResponseBody;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class OrderAPIResponse {

    private int id;

    private String note;

    private ATM atm;

    private LoginResponseBody user;

    private String created;

    @SerializedName("delete_flag")
    private boolean deleteFlag;

    private String address;

    @SerializedName("owner_name")
    private String ownerName;

    private int type;

    @SerializedName("phone_number")
    private String phoneNumber;

    private Service service;

    private int price;

    @SerializedName("price_unit")
    private String priceUnit;

    @SerializedName("phone_call")
    private int phoneCall;

    @SerializedName("msg_match")
    private int msgMatch;

    @SerializedName("msg_send")
    private int msgSend;

    public OrderAPIResponse(int id, String note, ATM atm, LoginResponseBody user, String created, boolean deleteFlag, String address, String ownerName, int type, String phoneNumber, Service service, int price, String priceUnit, int phoneCall, int msgMatch, int msgSend, boolean sendMessage) {
        this.id = id;
        this.note = note;
        this.atm = atm;
        this.user = user;
        this.created = created;
        this.deleteFlag = deleteFlag;
        this.address = address;
        this.ownerName = ownerName;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.price = price;
        this.priceUnit = priceUnit;
        this.phoneCall = phoneCall;
        this.msgMatch = msgMatch;
        this.msgSend = msgSend;
        this.sendMessage = sendMessage;
    }

    public int getPhoneCall() {
        return phoneCall;
    }

    public int getMsgMatch() {
        return msgMatch;
    }

    public int getMsgSend() {
        return msgSend;
    }

    private boolean sendMessage;

    public boolean isSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public ATM getAtm() {
        return atm;
    }

    public LoginResponseBody getUser() {
        return user;
    }

    public String getCreated() {
        return created;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Service getService() {
        return service;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }
}
