package com.remis24x7agencias.model;

public class ViajeReserva {
    private int via_id;
    private int via_pax_id;
    private String via_pax;
    private String via_telefono;
    private String via_origen;
    private String via_destino;
    private String via_tipo_viaje;
    private String via_dtime_viaje;
    private String via_duracion;
    private String via_kms;
    private String via_costo;
    private int via_cant_pax;
    private String via_espera;
    private String via_forma_pago;
    private String via_estado;
    private String via_reserva;

    public ViajeReserva(int via_id, int via_pax_id, String via_pax, String via_telefono, String via_origen, String via_destino, String via_tipo_viaje, String via_dtime_viaje, String via_duracion, String via_kms, String via_costo, int via_cant_pax, String via_espera, String via_forma_pago, String via_estado, String via_reserva) {
        this.via_id = via_id;
        this.via_pax_id = via_pax_id;
        this.via_pax = via_pax;
        this.via_telefono = via_telefono;
        this.via_origen = via_origen;
        this.via_destino = via_destino;
        this.via_tipo_viaje = via_tipo_viaje;
        this.via_dtime_viaje = via_dtime_viaje;
        this.via_duracion = via_duracion;
        this.via_kms = via_kms;
        this.via_costo = via_costo;
        this.via_cant_pax = via_cant_pax;
        this.via_espera = via_espera;
        this.via_forma_pago = via_forma_pago;
        this.via_estado = via_estado;
        this.via_reserva = via_reserva;
    }

    public int getVia_id() {
        return via_id;
    }

    public void setVia_id(int via_id) {
        this.via_id = via_id;
    }

    public int getVia_pax_id() {
        return via_pax_id;
    }

    public void setVia_pax_id(int via_pax_id) {
        this.via_pax_id = via_pax_id;
    }

    public String getVia_pax() {
        return via_pax;
    }

    public void setVia_pax(String via_pax) {
        this.via_pax = via_pax;
    }

    public String getVia_telefono() {
        return via_telefono;
    }

    public void setVia_telefono(String via_telefono) {
        this.via_telefono = via_telefono;
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

    public String getVia_tipo_viaje() {
        return via_tipo_viaje;
    }

    public void setVia_tipo_viaje(String via_tipo_viaje) {
        this.via_tipo_viaje = via_tipo_viaje;
    }

    public String getVia_dtime_viaje() {
        return via_dtime_viaje;
    }

    public void setVia_dtime_viaje(String via_dtime_viaje) {
        this.via_dtime_viaje = via_dtime_viaje;
    }

    public String getVia_duracion() {
        return via_duracion;
    }

    public void setVia_duracion(String via_duracion) {
        this.via_duracion = via_duracion;
    }

    public String getVia_kms() {
        return via_kms;
    }

    public void setVia_kms(String via_kms) {
        this.via_kms = via_kms;
    }

    public String getVia_costo() {
        return via_costo;
    }

    public void setVia_costo(String via_costo) {
        this.via_costo = via_costo;
    }

    public int getVia_cant_pax() {
        return via_cant_pax;
    }

    public void setVia_cant_pax(int via_cant_pax) {
        this.via_cant_pax = via_cant_pax;
    }

    public String getVia_espera() {
        return via_espera;
    }

    public void setVia_espera(String via_espera) {
        this.via_espera = via_espera;
    }

    public String getVia_forma_pago() {
        return via_forma_pago;
    }

    public void setVia_forma_pago(String via_forma_pago) {
        this.via_forma_pago = via_forma_pago;
    }

    public String getVia_estado() {
        return via_estado;
    }

    public void setVia_estado(String via_estado) {
        this.via_estado = via_estado;
    }

    public String getVia_reserva() {
        return via_reserva;
    }

    public void setVia_reserva(String via_reserva) {
        this.via_reserva = via_reserva;
    }
}
