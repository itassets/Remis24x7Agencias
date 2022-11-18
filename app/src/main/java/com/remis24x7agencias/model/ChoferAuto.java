package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

public class ChoferAuto {
    @SerializedName("status")
    String status;

    @SerializedName("descripcion")
    String descripcion;

    @SerializedName("marca")
    String mAuto_marca;

    @SerializedName("modelo")
    String mAuto_modelo;

    @SerializedName("color")
    String mAuto_color;

    @SerializedName("dominio")
    String mAuto_dominio;

    public ChoferAuto(String status, String descripcion, String mAuto_marca, String mAuto_modelo, String mAuto_color, String mAuto_dominio) {
        this.status = status;
        this.descripcion = descripcion;
        this.mAuto_marca = mAuto_marca;
        this.mAuto_modelo = mAuto_modelo;
        this.mAuto_color = mAuto_color;
        this.mAuto_dominio = mAuto_dominio;
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

    public String getmAuto_marca() {
        return mAuto_marca;
    }

    public void setmAuto_marca(String mAuto_marca) {
        this.mAuto_marca = mAuto_marca;
    }

    public String getmAuto_modelo() {
        return mAuto_modelo;
    }

    public void setmAuto_modelo(String mAuto_modelo) {
        this.mAuto_modelo = mAuto_modelo;
    }

    public String getmAuto_color() {
        return mAuto_color;
    }

    public void setmAuto_color(String mAuto_color) {
        this.mAuto_color = mAuto_color;
    }

    public String getmAuto_dominio() {
        return mAuto_dominio;
    }

    public void setmAuto_dominio(String mAuto_dominio) {
        this.mAuto_dominio = mAuto_dominio;
    }
}
