package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class ViajeCorto {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("via_estado")
    private String mVia_estado;

    @SerializedName("via_pax")
    private String mVia_pax;

    @SerializedName("via_origen")
    private String mVia_origen;

    @SerializedName("via_destino")
    private String mVia_destino;

    @SerializedName("via_dtime_viaje")
    private String mVia_dtime_viaje;

    @SerializedName("via_km")
    private String mVia_km;

    @SerializedName("via_duracion")
    private String mVia_duracion;

    @SerializedName("via_agencia")
    private String mVia_agencia;

    @SerializedName("via_chofer")
    private String mVia_chofer;

    @SerializedName("via_auto")
    private String mVia_auto;

    @SerializedName("via_costo_total")
    private String mVia_costo_total;

    @SerializedName("via_tipo_viaje")
    private String mVia_tipo_viaje;

    @SerializedName("via_reserva")
    private String mVia_via_reserva;

    @SerializedName("via_espera")
    private String mVia_via_espera;

    public ViajeCorto(String mStatus, String mDescripcion, String mVia_estado, String mVia_pax, String mVia_origen, String mVia_destino, String mVia_dtime_viaje, String mVia_km, String mVia_duracion, String mVia_agencia, String mVia_chofer, String mVia_auto, String mVia_costo_total, String mVia_tipo_viaje, String mVia_via_reserva, String mVia_via_espera) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mVia_estado = mVia_estado;
        this.mVia_pax = mVia_pax;
        this.mVia_origen = mVia_origen;
        this.mVia_destino = mVia_destino;
        this.mVia_dtime_viaje = mVia_dtime_viaje;
        this.mVia_km = mVia_km;
        this.mVia_duracion = mVia_duracion;
        this.mVia_agencia = mVia_agencia;
        this.mVia_chofer = mVia_chofer;
        this.mVia_auto = mVia_auto;
        this.mVia_costo_total = mVia_costo_total;
        this.mVia_tipo_viaje = mVia_tipo_viaje;
        this.mVia_via_reserva = mVia_via_reserva;
        this.mVia_via_espera = mVia_via_espera;
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

    public String getmVia_estado() {
        return mVia_estado;
    }

    public void setmVia_estado(String mVia_estado) {
        this.mVia_estado = mVia_estado;
    }

    public String getmVia_pax() {
        return mVia_pax;
    }

    public void setmVia_pax(String mVia_pax) {
        this.mVia_pax = mVia_pax;
    }

    public String getmVia_origen() {
        return mVia_origen;
    }

    public void setmVia_origen(String mVia_origen) {
        this.mVia_origen = mVia_origen;
    }

    public String getmVia_destino() {
        return mVia_destino;
    }

    public void setmVia_destino(String mVia_destino) {
        this.mVia_destino = mVia_destino;
    }

    public String getmVia_dtime_viaje() {
        return mVia_dtime_viaje;
    }

    public void setmVia_dtime_viaje(String mVia_dtime_viaje) {
        this.mVia_dtime_viaje = mVia_dtime_viaje;
    }

    public String getmVia_km() {
        return mVia_km;
    }

    public void setmVia_km(String mVia_km) {
        this.mVia_km = mVia_km;
    }

    public String getmVia_duracion() {
        return mVia_duracion;
    }

    public void setmVia_duracion(String mVia_duracion) {
        this.mVia_duracion = mVia_duracion;
    }

    public String getmVia_agencia() {
        return mVia_agencia;
    }

    public void setmVia_agencia(String mVia_agencia) {
        this.mVia_agencia = mVia_agencia;
    }

    public String getmVia_chofer() {
        return mVia_chofer;
    }

    public void setmVia_chofer(String mVia_chofer) {
        this.mVia_chofer = mVia_chofer;
    }

    public String getmVia_auto() {
        return mVia_auto;
    }

    public void setmVia_auto(String mVia_auto) {
        this.mVia_auto = mVia_auto;
    }

    public String getmVia_costo_total() {
        return mVia_costo_total;
    }

    public void setmVia_costo_total(String mVia_costo_total) {
        this.mVia_costo_total = mVia_costo_total;
    }

    public String getmVia_tipo_viaje() {
        return mVia_tipo_viaje;
    }

    public void setmVia_tipo_viaje(String mVia_tipo_viaje) {
        this.mVia_tipo_viaje = mVia_tipo_viaje;
    }

    public String getmVia_via_reserva() {
        return mVia_via_reserva;
    }

    public void setmVia_via_reserva(String mVia_via_reserva) {
        this.mVia_via_reserva = mVia_via_reserva;
    }

    public String getmVia_via_espera() {
        return mVia_via_espera;
    }

    public void setmVia_via_espera(String mVia_via_espera) {
        this.mVia_via_espera = mVia_via_espera;
    }
}
