package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("user_id")
    private String muser_id;

    @SerializedName("user_per_id")
    private String muser_per_id;

    @SerializedName("user_tipo")
    private String muser_tipo;

    @SerializedName("user_name")
    private String muser_name;

    @SerializedName("user_status")
    private String muser_status;

    @SerializedName("user_codigo")
    private String muser_codigo;

    @SerializedName("user_token")
    private String muser_token;

    public User(String status, String descripcion, String user_id, String user_per_id, String user_tipo, String user_name,
                String user_status, String user_codigo, String user_token){
        this.mStatus = status;
        this.mDescripcion = descripcion;
        this.muser_id = user_id;
        this.muser_per_id = user_per_id;
        this.muser_tipo = user_tipo;
        this.muser_status = user_status;
        this.muser_codigo = user_codigo;
        this.muser_token = user_token;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public String getMuser_id() {
        return muser_id;
    }

    public String getMuser_per_id() {
        return muser_per_id;
    }

    public String getMuser_tipo() {
        return muser_tipo;
    }

    public String getMuser_name() {
        return muser_name;
    }
    public String getMuser_status() {
        return muser_status;
    }

    public String getMuser_codigo() {
        return muser_codigo;
    }

    public String getMuser_token() {
        return muser_token;
    }
}
