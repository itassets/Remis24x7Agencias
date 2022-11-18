package com.remis24x7agencias.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.remis24x7agencias.R;
import com.remis24x7agencias.Remis24x7User;
import com.remis24x7agencias.model.ViajeActual;
import com.remis24x7agencias.net.Api;
import com.remis24x7agencias.net.ApiError;
import com.remis24x7agencias.net.Setting;
import com.remis24x7agencias.storage.SharedPrefManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.remis24x7agencias.net.Setting.urlCredencialDigital;
import static com.remis24x7agencias.net.Setting.urlAgenciaChoferViajeActual;
import static com.remis24x7agencias.net.Setting.urlUbicacionChofer;

public class MainActivity extends AppCompatActivity {
    TextView titulo;
    TextView textViewReservas, textViewViajes;
    Api jsonPlaceHolderApi;
    Retrofit retrofit;
    String bodyHash;
    String parametros;
    Remis24x7User user;
    ProgressBar pb;
    BottomNavigationView bottomNavigationView;
    FusedLocationProviderClient mFusedLocationClient;
    long ultimaConsulta;
    long intervalo = 15000;  // Intervalo mínimo inicial entre consultas: 15 segundos.
    MediaPlayer mp, mpToc;
    private final int TIEMPO = 45000; // Consulta automática cada 45 segundos
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo = (TextView) findViewById(R.id.labelAgencia);
        TextView fechaTextView = (TextView) findViewById(R.id.editTextFecha);
        textViewReservas = (TextView) findViewById(R.id.textViewReservas);
        textViewViajes = (TextView) findViewById(R.id.textViewViajes);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        user = SharedPrefManager.getInstance(this).getUser();
        pb = (ProgressBar) findViewById(R.id.progressBarChoferActivity);

        titulo.setText(user.getUser_empresa());
        Locale locale = new Locale("es", "AR");
        String pattern = "EEEE d' de 'MMMM' de 'yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        String hoy = simpleDateFormat.format(new Date());
        fechaTextView.setText(hoy);
        textViewReservas.setText("Reservas: 0");
        textViewViajes.setText("Viajes: 0");

        bodyHash = "";
        ultimaConsulta = intervalo;

        mp = MediaPlayer.create(this, R.raw.avisoviaje);
        mpToc = MediaPlayer.create(this, R.raw.toctoc);
        handler = new Handler();

        parametros = armarParametros();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ejecutarTarea();
    }

    void ejecutarTarea(){
        mConsulta.run();
    }

    private Runnable mConsulta = new Runnable() {
        @Override
        public void run() {
            getCant_Viajes_Reservas();
            handler.postDelayed(this, TIEMPO);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(mConsulta);
    }

    void getCant_Viajes_Reservas(){};

    String toMD5(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(s));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        }catch (NoSuchAlgorithmException e){
            return "";
        }
    }

    String armarParametros() {
        String p = "";
        String seed = "cH0f3R";
        String id = Integer.toHexString(user.getUser_per_id()*10);
        String key = toMD5(seed + user.getUser_per_id() + user.getUser_apellido());
        if (!key.equals("")){
            Random aleatorio = new Random(System.currentTimeMillis());
            int indice = aleatorio.nextInt(6);
            char c = seed.charAt(indice);
            String id0 = Character.toString(c) + id;
            p = "?k=" + key + "&id=" + id0;
        }
        return p;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.viajes:
                            misViajes();
                            break;

                        case R.id.choferes:
                            choferes();
                            break;

                    }
                    return true;
                }
            };

    void choferes(){
        Intent intent = new Intent(MainActivity.this, ChoferesActivity.class);
        startActivity(intent);
    }

    private void misViajes() {
        Intent intent = new Intent(MainActivity.this, ViajesActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainactivity_toolbar_menu, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.mis_datos:
                intent = new Intent(this, MisDatosActivity.class);
                startActivity(intent);
                return true;

            case R.id.mis_seteos:
                intent = new Intent(this, MisSeteosActivity.class);
                startActivity(intent);
                return true;


            case R.id.cerrar_sesion:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}