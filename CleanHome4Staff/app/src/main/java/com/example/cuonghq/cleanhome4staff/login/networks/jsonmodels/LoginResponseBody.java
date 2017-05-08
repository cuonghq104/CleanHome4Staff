package com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class LoginResponseBody {

    private String username;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String email;

    @SerializedName("img_url")
    private String imageUrl;

    @SerializedName("auth_token")
    private String authenticationToken;

    public LoginResponseBody(String username, String firstName, String lastName, String email, String imageUrl, String authenticationToken) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.authenticationToken = authenticationToken;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
