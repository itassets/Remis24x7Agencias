package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class ViajeActual {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("via_id")
    private String mVia_id;

    public ViajeActual(String mStatus, String mDescripcion, String mVia_id) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mVia_id = mVia_id;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public void setmDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    public String getmVia_id() {
        return mVia_id;
    }

    public void setmVia_id(String mVia_id) {
        this.mVia_id = mVia_id;
    }
}
