package com.example.ptb_10_a.models;

import com.google.gson.annotations.SerializedName;

public class UbahPassword{

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}
