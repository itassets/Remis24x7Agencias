package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class UsuarioCreado {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("username")
    private String mUsername;

    public UsuarioCreado(String mStatus, String mDescripcion, String mUsername) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mUsername = mUsername;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public String getmUsername() {
        return mUsername;
    }
}
