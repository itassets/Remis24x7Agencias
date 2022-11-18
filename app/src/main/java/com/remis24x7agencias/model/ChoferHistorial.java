package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChoferHistorial {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("viajes")
    private List<ViajeHistorial> mViajes;

    public ChoferHistorial(String mStatus, String mDescripcion, List<ViajeHistorial> mViajes) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mViajes = mViajes;
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

    public List<ViajeHistorial> getmViajes() {
        return mViajes;
    }

    public void setmViajes(List<ViajeHistorial> mViajes) {
        this.mViajes = mViajes;
    }
}

