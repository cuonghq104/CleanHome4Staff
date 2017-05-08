package com.example.cuonghq.cleanhome4staff.commons.utils;

import android.content.SharedPreferences;

import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.LoginResponseBody;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class Constant {

    public static final String AUTHORIZATION_TOKEN = "token";

    public static SharedPreferences sharedPreferences;

    public static final String SHARED_PREFERENCES = "SP";

    public static String success_msg;

    public static String match_msg;

    public static LoginResponseBody responseBody;

    public static final String PHONE_CALL = "phone_call";

    public static final String MSG_SEND = "msg_send";

    public static final String MSG_MATCH = "msg_match";

}
