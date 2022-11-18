package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("formatted_address")
    private String mformatted_address;

    @SerializedName("latitud")
    private String mlatitud;

    @SerializedName("longitud")
    private String mlongitud;

    public Address(String status, String descripcion, String formatted_address, String latitud, String longitud){
        this.mStatus = status;
        this.mDescripcion = descripcion;
        this.mformatted_address = formatted_address;
        this.mlatitud = latitud;
        this.mlongitud = longitud;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public String getMformatted_address() {
        return mformatted_address;
    }

    public String getMlatitud() {
        return mlatitud;
    }

    public String getMlongitud() {
        return mlongitud;
    }
}
