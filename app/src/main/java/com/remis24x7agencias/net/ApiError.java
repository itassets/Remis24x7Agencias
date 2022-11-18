package com.remis24x7agencias.net;

import com.google.gson.annotations.SerializedName;

public class ApiError {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    public ApiError(String status, String descripcion){
        this.mStatus = status;
        this.mDescripcion = descripcion;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }
}
