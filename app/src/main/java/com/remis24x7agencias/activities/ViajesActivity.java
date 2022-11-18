package com.remis24x7agencias.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.remis24x7agencias.R;
import com.remis24x7agencias.model.ChoferViajesReservas;
import com.remis24x7agencias.model.ViajeActual;
import com.remis24x7agencias.net.Api;
import com.remis24x7agencias.model.ViajeReserva;
import com.remis24x7agencias.net.ApiError;
import com.remis24x7agencias.net.Setting;
import com.remis24x7agencias.utils.OnSwipeTouchListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.remis24x7agencias.net.Setting.CHOFER_EN_CAMINO;
import static com.remis24x7agencias.net.Setting.CHOFER_EN_PUERTA_PAX;
import static com.remis24x7agencias.net.Setting.urlAceptarRechazar;
import static com.remis24x7agencias.net.Setting.urlAgenciaChoferViajesReservas;
import static com.remis24x7agencias.net.Setting.urlListadoViajes;

public class ViajesActivity extends AppCompatActivity {
    Api jsonPlaceHolderApi;
    Retrofit retrofit;
    BottomNavigationView bottomNavigationView;
    String tipo;
    String avisoTipo;
    String fecha;
    int choferId;
    int viaje_id;
    String agencia;
    TextView textPax, textOrigen, textDestino, textTipoViaje, textPasajeros, textFechaHora, textEstado,textViewCosto;
    int index;
    ArrayList<ViajeReserva> viajeChoferArrayList;
    ViajeReserva viajeChofer;
    String bodyHash;
    TextView textTipo;
    ProgressBar pb;
    ImageView conEspera, avisarPax;
    ConstraintLayout layout;
    Button botonAceptar, botonRechazar, botonIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes);
        layout = findViewById(R.id.viajesLayout);
        layout.setOnTouchListener(new OnSwipeTouchListener(ViajesActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                viajeSiguiente();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                viajeAnterior();
            }
        });
        pb = (ProgressBar) findViewById(R.id.progressBarViajeChofer);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            tipo = parametros.getString("tipo");
            fecha = parametros.getString("fecha");
            choferId = parametros.getInt("choferId");
            agencia = parametros.getString("agencia");
            if (fecha==null){
                fecha = Setting.getHoy();
            }
        }
        bottomNavigationView =  (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        botonRechazar = (Button) findViewById(R.id.buttonRechazar);
        botonRechazar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                rechazar();
            }
        });
        botonIniciar = (Button) findViewById(R.id.buttonIniciar);
        botonIniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                iniciar();
            }
        });
        botonIniciar.setVisibility(View.INVISIBLE);
        botonAceptar = (Button) findViewById(R.id.buttonAceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                aceptar();
            }
        });
        TextView textViewAgencia = (TextView) findViewById(R.id.textViewAgencia);
        textViewAgencia.setText(agencia);

        textTipo = (TextView) findViewById(R.id.textTipo);
        /* Cambio: muestra viajes y reservas juntas
        if (tipo.equals("V")) {
            textTipo.setText("Mis Viajes");
        }else{
            textTipo.setText("Mis Reservas");
        }
        */
        textTipo.setText("Mis Viajes");
        textPax = (TextView) findViewById(R.id.textPaxName);
        textOrigen = (TextView) findViewById(R.id.labelOrigen);
        textDestino = (TextView) findViewById(R.id.labelDestino);
        textTipoViaje = (TextView) findViewById(R.id.textTipoViaje);
        textPasajeros = (TextView) findViewById(R.id.editTextPasajeros);
        textFechaHora = (TextView) findViewById(R.id.textFechaViaje);
        textEstado = (TextView) findViewById(R.id.textEstado);
        conEspera = (ImageView) findViewById(R.id.imageView19);
        avisarPax = (ImageView) findViewById(R.id.imageView11);
        avisarPax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                doAvisoPax();
            }
        });
        textViewCosto = (TextView) findViewById(R.id.textViewCosto);
        textPax.setText("");
        textOrigen.setText("");
        textDestino.setText("");
        textTipoViaje.setText("");
        textPasajeros.setText("");
        textFechaHora.setText("");
        textViewCosto.setText("");
        textEstado.setText("Estado: ");
        avisoTipo = "";
        index = -1;
        viaje_id = 0;
        getViajeReservas();
    } //onCreate

    void doAvisoPax(){
        doConfirmarAviso();
    }//doAvisoPax

    void doConfirmarAviso(){
        String avisoAlPax = "Avisa al pasajero que...";
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("NOTIFICAR AL PASAJERO");
        alertDialog.setMessage(avisoAlPax);
        alertDialog.setPositiveButton("Estoy en camino", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                avisoTipo = CHOFER_EN_CAMINO;
                doAvisarPax(avisoTipo);
                //Toast.makeText(ViajeChoferActivity.this, "aviso", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("Estoy esperándote", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                avisoTipo = CHOFER_EN_PUERTA_PAX;
                doAvisarPax(avisoTipo);
            }
        });
        alertDialog.show();

    }// doConfirmarAviso

    void showNoData(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("No hay datos para mostrar");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.show();
    } // showNoData

    void doConfirmaAccion(String accion){
        final String action = accion;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        String titulo = "Confirme que ";
        if (action.equals("A")){
            titulo = titulo + "ACEPTA ";
        }else{
            if (action.equals("R")) {
                titulo = titulo + "RECHAZA ";
            }else{
                titulo = titulo + "INICIA";
            }
        }
        if (viajeChofer.getVia_reserva().equals("N")){
            titulo = titulo + " el viaje?";
        }else{
            titulo = titulo + " la reserva?";
        }
        alertDialog.setTitle(titulo);
        alertDialog.setMessage("");
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (action.equals("I")){
                    doCheckEstadoViaje();
                }else {
                    doAceptarRechazar(action);
                }
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog.show();
    } //doConfirmaAccion

    void doCheckEstadoViaje(){
        // chequear estado viaje NO CANCELADO o PROGRAMADO api 1173
        // usar clase ViajeActual
        // url = urlListadoViajes
        // getEstadoViaje
        pb.setVisibility(View.INVISIBLE);
        bodyHash = Setting.getBodyHash(String.valueOf(viajeChofer.getVia_id()));
        retrofit = new Retrofit.Builder()
                .baseUrl(urlListadoViajes)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ViajeActual> call = jsonPlaceHolderApi.getEstadoViaje(Setting.tokenAPP, bodyHash, viajeChofer.getVia_id());

        call.enqueue(new Callback<ViajeActual>() {
            @Override
            public void onResponse(Call<ViajeActual> call, Response<ViajeActual> response) {
                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViajesActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.INVISIBLE);
                ViajeActual resultadoChofer = response.body();
                if (resultadoChofer.getmStatus().equals("EXITO")){
                    if (resultadoChofer.getmDescripcion().equals("PROGRAMADO")) {
                        doAceptarRechazar("I");
                    }else{
                        if (resultadoChofer.getmDescripcion().equals("CANCELADO")) {
                            viajeChofer.setVia_estado(resultadoChofer.getmDescripcion());
                            Toast.makeText(ViajesActivity.this, "EL VIAJE HA SIDO CANCELADO", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ViajesActivity.this, "EL VIAJE SE HA MODIFICADO. NO PUEDE INICIARSE.", Toast.LENGTH_LONG).show();
                        }
                        viajeChofer.setVia_estado(resultadoChofer.getmDescripcion());
                        textEstado.setText(resultadoChofer.getmDescripcion());
                    }
                }
            }

            @Override
            public void onFailure(Call<ViajeActual> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(ViajesActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();

            }
        });
    }//doCheckEstadoViaje


    void doAceptarRechazar(String action){
        pb.setVisibility(View.VISIBLE);
        final String faction = action;
        bodyHash = Setting.getBodyHash(viajeChofer.getVia_id() + action);
        retrofit = new Retrofit.Builder()
                .baseUrl(urlAceptarRechazar)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ApiError> call = jsonPlaceHolderApi.choferaceptarechazar(Setting.tokenAPP, bodyHash, viajeChofer.getVia_id(), action);

        call.enqueue(new Callback<ApiError>() {
            @Override
            public void onResponse(Call<ApiError> call, Response<ApiError> response) {

                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViajesActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.INVISIBLE);
                ApiError resultadoChofer = response.body();
                if (resultadoChofer.getmStatus().equals("EXITO")){
                    if (faction.equals("I")){
                        viaje_id = viajeChofer.getVia_id();
                        doIniciar();
                    }else {
                        if (faction.equals("A")) {
                            viajeChofer.setVia_estado("PROGRAMADO");
                            textEstado.setText((viajeChofer.getVia_reserva().equals("N") ? "Viaje ACEPTADO" : "Reserva ACEPTADA"));
                            avisarPax.setVisibility(View.VISIBLE);
                            botonIniciar.setVisibility(View.VISIBLE);
                        } else {
                            viajeChofer.setVia_estado("SIN CHOFER");
                            textEstado.setText((viajeChofer.getVia_reserva().equals("N") ? "Viaje RECHAZADO" : "Reserva RECHAZADA"));
                            botonIniciar.setVisibility(View.INVISIBLE);
                        }
                        viajeChoferArrayList.set(index, viajeChofer);
                        String msg = "Ha " + (faction.equals("A") ? "aceptado " : "rechazado ") + (viajeChofer.getVia_reserva().equals("N") ? "el viaje." : "la reserva.");
                        Toast.makeText(ViajesActivity.this, msg, Toast.LENGTH_LONG).show();
                        return;
                    }
                }

            }

            @Override
            public void onFailure(Call<ApiError> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(ViajesActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();

            }
        });

    } // doAceptarRechazar

    void doIniciar(){
        //   Intent intent = new Intent(this, Remis24x7Activity.class);
        //   Bundle bundle = new Bundle();
        //  bundle.putInt("viaje_id", viaje_id);
        // intent.putExtras(bundle);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
    } // doIniciar

    void viajeAnterior(){
        if (index == 0) {
            Toast.makeText(ViajesActivity.this, "No hay anteriores", Toast.LENGTH_LONG).show();
        } else {
            index = index - 1;
            showViaje();
        }
    }

    void rechazar(){
        doConfirmaAccion("R");
    }

    void iniciar(){
        if (viajeChofer.getVia_estado().equals("PROGRAMADO")){
            doConfirmaAccion("I");
        }else{
            Toast.makeText(ViajesActivity.this, "Debe ACEPTAR " + (viajeChofer.getVia_reserva().equals("N") ? "el VIAJE":"la RESERVA") + " para poder INICIAR.", Toast.LENGTH_LONG).show();
        }
    }

    void aceptar(){
        if (viajeChofer.getVia_estado().equals("PROGRAMADO")){
            Toast.makeText(ViajesActivity.this, (viajeChofer.getVia_reserva().equals("N") ? "VIAJE ya aceptado.":"RESERVA ya aceptada."), Toast.LENGTH_LONG).show();
        }else {
            doConfirmaAccion("A");
        }
    }

    void viajeSiguiente(){
        if (index==viajeChoferArrayList.size()-1) {
            Toast.makeText(ViajesActivity.this, "No hay siguientes", Toast.LENGTH_LONG).show();
        }else {
            index = index + 1;
            showViaje();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.anterior:
                            viajeAnterior();
                            break;

                        case R.id.siguiente:
                            viajeSiguiente();
                            break;
                    }
                    return true;
                }
            };


    void showViaje(){
        viajeChofer = (ViajeReserva) viajeChoferArrayList.get(index);
        botonIniciar.setVisibility(View.INVISIBLE);
        if ((viajeChofer.getVia_estado().equals("CHOFER") || viajeChofer.getVia_estado().equals("PROGRAMADO"))) {
            if (viajeChofer.getVia_estado().equals("PROGRAMADO")) {
                botonIniciar.setVisibility(View.VISIBLE);
                avisarPax.setVisibility(View.VISIBLE);
            }else{
                avisarPax.setVisibility(View.INVISIBLE);
            }

            if (viajeChofer.getVia_reserva().equals("N")) {
                textTipo.setText("Viaje #" + viajeChofer.getVia_id());
            } else {
                textTipo.setText("Reserva # " + viajeChofer.getVia_id());
            }
            textPax.setText(viajeChofer.getVia_pax());
            textOrigen.setText(viajeChofer.getVia_origen());
            textDestino.setText(viajeChofer.getVia_destino());
            textTipoViaje.setText(viajeChofer.getVia_tipo_viaje());
            if (viajeChofer.getVia_espera().equals("No")) {
                conEspera.setVisibility(View.INVISIBLE);
            }else{
                conEspera.setVisibility(View.VISIBLE);
            }
            textViewCosto.setText(viajeChofer.getVia_costo());
            if (viajeChofer.getVia_cant_pax() <= 1) {
                textPasajeros.setText("Sin pasajeros adicionales");
            } else {
                textPasajeros.setText(viajeChofer.getVia_cant_pax() + " pasajero(s) adicional(es)");
            }
            textFechaHora.setText(viajeChofer.getVia_dtime_viaje());
            if (viajeChofer.getVia_estado().equals("CHOFER")) {
                if (viajeChofer.getVia_reserva().equals("N")) {
                    textEstado.setText("Viaje asignado: ACEPTE o RECHACE");
                } else {
                    textEstado.setText("Reserva asignada: ACEPTE o RECHACE");
                }
            } else {
                textEstado.setText(viajeChofer.getVia_estado());
            }
        }
    } // showViaje

    void getViajeReservas(){
        pb.setVisibility(View.VISIBLE);
        String es_reserva = "T";

        bodyHash = Setting.getBodyHash(choferId + es_reserva + fecha);

        retrofit = new Retrofit.Builder()
                .baseUrl(urlAgenciaChoferViajesReservas)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ChoferViajesReservas> call = jsonPlaceHolderApi.getChoferViajes(Setting.tokenAPP, bodyHash,choferId,es_reserva,fecha);

        call.enqueue(new Callback<ChoferViajesReservas>() {
            @Override
            public void onResponse(Call<ChoferViajesReservas> call, Response<ChoferViajesReservas> response) {

                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViajesActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.INVISIBLE);
                ChoferViajesReservas resultadoChofer = response.body();
                if (resultadoChofer.getmStatus().equals("EXITO")){
                    viajeChoferArrayList = ( ArrayList<ViajeReserva>) resultadoChofer.getmViajes();
                    index = 0;
                    showViaje();
                }else{
                    showNoData();
                }

            }

            @Override
            public void onFailure(Call<ChoferViajesReservas> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(ViajesActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();

            }
        });
    }//getViajeReservas

    void doAvisarPax(String tipo){
        // Toast.makeText(this, "Avisando al Pasaajero...",Toast.LENGTH_SHORT).show();
        pb.setVisibility(View.VISIBLE);
        bodyHash = Setting.getBodyHash(viajeChofer.getVia_id() +  "4v1s0" + tipo);
        retrofit = new Retrofit.Builder()
                .baseUrl(urlAceptarRechazar)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ApiError> call = jsonPlaceHolderApi.avisoViaje(Setting.tokenAPP, bodyHash, viajeChofer.getVia_id(), tipo);

        call.enqueue(new Callback<ApiError>() {
            @Override
            public void onResponse(Call<ApiError> call, Response<ApiError> response) {

                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(ViajesActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.INVISIBLE);
                ApiError resultadoChofer = response.body();
                if (resultadoChofer.getmStatus().equals("EXITO")){
                    Toast.makeText(ViajesActivity.this, "Notificación enviada al Pasajero", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ViajesActivity.this, "No pudo enviarse la notificación enviada al Pasajero", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiError> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(ViajesActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();
            }
        });
    } //doAvisarPax
}

