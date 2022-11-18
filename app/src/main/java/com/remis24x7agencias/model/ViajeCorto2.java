package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class ViajeCorto2 {
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

    @SerializedName("via_espera")
    private String mVia_via_espera;

    @SerializedName("via_forma_pago")
    private String mVia_via_forma_pago;

    @SerializedName("via_costo_total")
    private String mVia_via_costo_total;

    @SerializedName("via_tipo_viaje")
    private String mVia_tipo_viaje;

    public ViajeCorto2(String mStatus, String mDescripcion, String mVia_estado, String mVia_pax, String mVia_origen, String mVia_destino, String mVia_dtime_viaje, String mVia_km, String mVia_duracion, String mVia_via_espera, String mVia_via_forma_pago, String mVia_via_costo_total, String mVia_tipo_viaje) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mVia_estado = mVia_estado;
        this.mVia_pax = mVia_pax;
        this.mVia_origen = mVia_origen;
        this.mVia_destino = mVia_destino;
        this.mVia_dtime_viaje = mVia_dtime_viaje;
        this.mVia_km = mVia_km;
        this.mVia_duracion = mVia_duracion;
        this.mVia_via_espera = mVia_via_espera;
        this.mVia_via_forma_pago = mVia_via_forma_pago;
        this.mVia_via_costo_total = mVia_via_costo_total;
        this.mVia_tipo_viaje = mVia_tipo_viaje;
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

    public String getmVia_via_espera() {
        return mVia_via_espera;
    }

    public void setmVia_via_espera(String mVia_via_espera) {
        this.mVia_via_espera = mVia_via_espera;
    }

    public String getmVia_via_forma_pago() {
        return mVia_via_forma_pago;
    }

    public void setmVia_via_forma_pago(String mVia_via_forma_pago) {
        this.mVia_via_forma_pago = mVia_via_forma_pago;
    }

    public String getmVia_via_costo_total() {
        return mVia_via_costo_total;
    }

    public void setmVia_via_costo_total(String mVia_via_costo_total) {
        this.mVia_via_costo_total = mVia_via_costo_total;
    }

    public String getmVia_tipo_viaje() {
        return mVia_tipo_viaje;
    }

    public void setmVia_tipo_viaje(String mVia_tipo_viaje) {
        this.mVia_tipo_viaje = mVia_tipo_viaje;
    }
}
