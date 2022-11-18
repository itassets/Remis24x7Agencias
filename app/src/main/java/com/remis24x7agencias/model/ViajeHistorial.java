package com.remis24x7agencias.model;

public class ViajeHistorial {
    private int via_id;
    private String via_origen;
    private String via_destino;
    private String via_dtime_viaje;
    private String via_estado ;
    private String via_costo_total;

    public ViajeHistorial(int via_id, String via_origen, String via_destino, String via_dtime_viaje, String via_estado, String via_costo_total) {
        this.via_id = via_id;
        this.via_origen = via_origen;
        this.via_destino = via_destino;
        this.via_dtime_viaje = via_dtime_viaje;
        this.via_estado = via_estado;
        this.via_costo_total = via_costo_total;
    }

    public int getVia_id() {
        return via_id;
    }

    public void setVia_id(int via_id) {
        this.via_id = via_id;
    }

    public String getVia_origen() {
        return via_origen;
    }

    public void setVia_origen(String via_origen) {
        this.via_origen = via_origen;
    }

    public String getVia_destino() {
        return via_destino;
    }

    public void setVia_destino(String via_destino) {
        this.via_destino = via_destino;
    }

    public String getVia_dtime_viaje() {
        return via_dtime_viaje;
    }

    public void setVia_dtime_viaje(String via_dtime_viaje) {
        this.via_dtime_viaje = via_dtime_viaje;
    }

    public String getVia_estado() {
        return via_estado;
    }

    public void setVia_estado(String via_estado) {
        this.via_estado = via_estado;
    }

    public String getVia_costo_total() {
        return via_costo_total;
    }

    public void setVia_costo_total(String via_costo_total) {
        this.via_costo_total = via_costo_total;
    }
}
