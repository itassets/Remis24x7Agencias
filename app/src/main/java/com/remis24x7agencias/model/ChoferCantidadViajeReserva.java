package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class ChoferCantidadViajeReserva {
    @SerializedName("status")
    String status;

    @SerializedName("descripcion")
    String descripcion;

    @SerializedName("viajes")
    int sCantViajes;

    @SerializedName("reservas")
    int sCantReservas;

    public ChoferCantidadViajeReserva(String status, String descripcion, int sCantViajes, int sCantReservas) {
        this.status = status;
        this.descripcion = descripcion;
        this.sCantViajes = sCantViajes;
        this.sCantReservas = sCantReservas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getsCantViajes() {
        return sCantViajes;
    }

    public void setsCantViajes(int sCantViajes) {
        this.sCantViajes = sCantViajes;
    }

    public int getsCantReservas() {
        return sCantReservas;
    }

    public void setsCantReservas(int sCantReservas) {
        this.sCantReservas = sCantReservas;
    }
}
