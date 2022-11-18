package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChoferViajesReservas {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("viajes")
    private List<ViajeReserva> mViajes;

    public ChoferViajesReservas(String mStatus, String mDescripcion, List<ViajeReserva> mViajes) {
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

    public List<ViajeReserva> getmViajes() {
        return mViajes;
    }

    public void setmViajes(List<ViajeReserva> mViajes) {
        this.mViajes = mViajes;
    }
}
