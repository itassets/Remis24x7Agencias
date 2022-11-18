package com.remis24x7agencias.net;


import com.remis24x7agencias.model.Address;
import com.remis24x7agencias.model.ChoferAuto;
import com.remis24x7agencias.model.ChoferCantidadViajeReserva;
import com.remis24x7agencias.model.ChoferHistorial;
import com.remis24x7agencias.model.ChoferViajesReservas;
import com.remis24x7agencias.model.Directions;
import com.remis24x7agencias.model.User;
import com.remis24x7agencias.model.UserLogin;
import com.remis24x7agencias.model.UsuarioCreado;
import com.remis24x7agencias.model.ViajeActual;
import com.remis24x7agencias.model.ViajeCorto;
import com.remis24x7agencias.model.ViajeCorto2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    // Users ---------------------------------------------------------------------------------------
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @FormUrlEncoded
    @POST("loginagencia")
    Call<UserLogin> userLogin(
            @Header("Remis-Token") String token,
            @Field("id") String id,
            @Field("contrasena") String contrasena);

    @FormUrlEncoded
    @POST("crearcuenta")
    Call<UsuarioCreado> userNuevo(
            @Field("documento") String documento,
            @Field("codigo") String codigo,
            @Field("username") String username,
            @Field("apellido") String apellido,
            @Field("nombre") String nombre,
            @Field("movil") String movil,
            @Field("email") String email,
            @Field("contrasena") String contrasena);

    @FormUrlEncoded
    @POST("chequear")
    Call<ApiError> userConfirm(
            @Field("documento") String documento,
            @Field("token") String token);

    @FormUrlEncoded
    @POST("gettoken")
    Call<ApiError> resetContrasena(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("username") String username);

    @FormUrlEncoded
    @POST("settoken")
    Call<ApiError> registrarfcm(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("user_id") int user_id,
            @Field("fcmtoken") String fcmtoken);


    // ---------------------------------------------------------------------------------------------
    // Viajes --------------------------------------------------------------------------------------
    // Devuelve datos si el paxid tiene un viaje pendiente
    // Devuelve los datos de un viaje - el registro de la tabla
    @GET("datos/{id}")
    Call<ViajeCorto> getDatosViaje(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // Devuelve los datos de un viaje id para un chofer
    @GET("datos/{id}")
    Call<ViajeCorto2> getDatosViaje2(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);
    // ---------------------------------------------------------------------------------------------
    // Mapas ---------------------------------------------------------------------------------------
    // getAddress recibe una dirección en formato: calle numero localidad provincia
    // devuelve objeto direccion.
    @GET("getAddress/{addr}/{source}")
    Call<Address> getAddress(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("addr") String addr,
            @Path("source") int source);


    // Devuelve las polylines para graficar el trayecto desde el origen al destino
    // Origen y destino expresados en latitud y longitud.
    @GET("getDirections/{origen}/{destino}")
    Call<Directions> getDirections(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("origen") String origen,
            @Path("destino") String destino);

    // Chofer --------------------------------------------------------------------------------------
    // Devuelve auto de un chofer
    @GET("maneja/{id}")
    Call<ChoferAuto> getChoferAuto(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // Devuelve cantidad de viajes/reservas que tiene un chofer a partir del día de hoy
    @GET("cantviajes/{id}")
    Call<ChoferCantidadViajeReserva> getChoferCantidadViajes(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // Devuelve el id del viaje iniciado por el chofer id
    @GET("consultar/{id}")
    Call<ViajeActual> getViajeActual(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // Devuelve el ESTADO de un viaje
    @GET("status/{id}")
    Call<ViajeActual> getEstadoViaje(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // Rechazar un viaje. Un chofer rechaza o acepta o inicia o finaliza un viaje.
    @POST("aceptarechazariniciar/{id}/{action}")
    Call<ApiError> choferaceptarechazar(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id,
            @Path("action") String action);

    // Devuelve lista de viajes+reservas que tiene un chofer a partir del día de hoy
    @GET("lista/{id}/{tipo}/{fecha}")
    Call<ChoferViajesReservas> getChoferViajes(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id,
            @Path("tipo") String tipo,
            @Path("fecha") String fecha);

    // Devuelve el historial de viajes/reservas de un chofer (20 viajes)
    @GET("historial/{id}")
    Call<ChoferHistorial> getChoferHistorialViajes(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);

    // confirmar token
    @FormUrlEncoded
    @POST("chequear")
    Call<ApiError> userConfirm(
            @Header("Remis-Token") String rtoken,
            @Header("Remis-Token-X") String tokenx,
            @Field("documento") String documento,
            @Field("token") String token);

    // reseteo de contraseña
    @FormUrlEncoded
    @POST("setpassword")
    Call<ApiError> setContrasena(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("id") int id,
            @Field("passw") String newpassw,
            @Field("codigo") String codigo);

    // Viaje abonado.
    @FormUrlEncoded
    @POST("cobrado")
    Call<ApiError> abonarViaje(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("id") int id,
            @Field("formapago") String formapago,
            @Field("costo_adicional") String costo_adicional,
            @Field("costo_total") String costo_total);

    // Enviar calificacion pasajero.
    @FormUrlEncoded
    @POST("calificarpax")
    Call<ApiError> calificaPax(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("viajeid") int viajeid,
            @Field("pax") String pax,
            @Field("choferid") int choferid,
            @Field("calificacion") int calificacion,
            @Field("descripcion") String descripcion);

    // Enviar ubicacion chofeer.
    @FormUrlEncoded
    @POST("ubicacion")
    Call<ApiError> ubicacionChofer(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("choferid") int choferid,
            @Field("latitud") double latitud,
            @Field("longitud") double longitud);

    // Enviar notificación al pasajero.
    @POST("choferavisa/{id}/{tipo}")
    Call<ApiError> avisoViaje(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id,
            @Path("tipo") String tipo);

    // Viaje abonado.
    @FormUrlEncoded
    @POST("activar")
    Call<ApiError> activarChofer(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Field("choferid") int choferid,
            @Field("activo") int activo);

    @GET("disponible/{id}")
    Call<ApiError> getChoferDisponibilidad(
            @Header("Remis-Token") String token,
            @Header("Remis-Token-X") String tokenx,
            @Path("id") int id);


    // ---------------------------------------------------------------------------------------------
}

