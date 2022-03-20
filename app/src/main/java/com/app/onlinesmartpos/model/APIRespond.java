package com.app.onlinesmartpos.model;

import com.google.gson.annotations.SerializedName;

public class APIRespond {

    @SerializedName("message")
    private String massage;

    public String getMassage() {
        return massage;
    }

}
