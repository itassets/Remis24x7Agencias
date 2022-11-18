package com.remis24x7agencias.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.remis24x7agencias.R;
import com.remis24x7agencias.Remis24x7User;
import com.remis24x7agencias.model.UserLogin;
import com.remis24x7agencias.net.Api;
import com.remis24x7agencias.net.ApiError;
import com.remis24x7agencias.net.Setting;
import com.remis24x7agencias.storage.SharedPrefManager;
import com.remis24x7agencias.utils.Cifrado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.remis24x7agencias.net.Setting.USUARIO_CONFIRMADO;
import static com.remis24x7agencias.net.Setting.USUARIO_NO_CONFIRMADO;
import static com.remis24x7agencias.net.Setting.urlFCM;
import static com.remis24x7agencias.net.Setting.urlResetContrasena;
import static com.remis24x7agencias.net.Setting.urllogin;

public class LoginActivity extends AppCompatActivity {
    Api jsonPlaceHolderApi;
    Retrofit retrofit;
    private Button btn_ingresar;
    private EditText txtusuario, txtpassword;
    private CheckBox chk_recordar;
    private TextView olvide_contrasena, de_contraseña, crear_cuenta;
    Remis24x7User user;
    Cifrado cifrado;
    String bodyHash;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusuario = (EditText) findViewById(R.id.tiedt_username);
        txtpassword = (EditText) findViewById(R.id.tiedt_contrasena);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        chk_recordar = (CheckBox) findViewById(R.id.chk_recordar);
        olvide_contrasena = (TextView) findViewById(R.id.olvidecontrasena);
        crear_cuenta = (TextView) findViewById(R.id.textViewCrearCuenta);
        crear_cuenta.setVisibility(View.GONE);

        pb = (ProgressBar) findViewById(R.id.progressBarLogin);
        cifrado = new Cifrado();

        user = SharedPrefManager.getInstance(this).getUser();
        if (user.getRecordarLogin()) {
            txtusuario.setText(user.getUser_name());
            txtpassword.setText(cifrado.decryptText(user.getUser_password()));
            chk_recordar.setChecked(true);
        }else{
            txtusuario.setText("");
            txtpassword.setText("");
            chk_recordar.setChecked(false);
        }

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // guardar en preferencias
                if (chk_recordar.isChecked()){
                    user.setRecordarLogin(true);
                    user.setUser_name( txtusuario.getText().toString().toLowerCase());
                    user.setUser_password(cifrado.encryptText(txtpassword.getText().toString()));
                }else{
                    user.setRecordarLogin(false);
                    user.setUser_name("");
                    user.setUser_password("");
                }
                SharedPrefManager.getInstance(LoginActivity.this).saveUser(user);
                doLogin();
            }
        });

        olvide_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Olvido su contraseña", Toast.LENGTH_LONG).show();
                if (!txtusuario.getText().toString().trim().equals("")) {
                    doResetContrasena();
                }else{
                    Toast.makeText(LoginActivity.this, "Ingresa tu usuario para recuperar tu contraseña.", Toast.LENGTH_LONG).show();
                }
            }
        });

    } //onCreate

    private void doLogin(){
        pb.setVisibility(View.VISIBLE);
        String username = txtusuario.getText().toString().toUpperCase();
        final String spassword = Setting.convertPassMd5(txtpassword.getText().toString());

        retrofit = new Retrofit.Builder()
                .baseUrl(urllogin)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<UserLogin> call = jsonPlaceHolderApi.userLogin(Setting.tokenAPP, username, spassword);

        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }

                UserLogin resultadoUser = response.body();

                String status = resultadoUser.getmStatus();
                String descripcion = resultadoUser.getmDescripcion();

                if (status.equals("EXITO")) {
                    int user_id = Integer.parseInt(resultadoUser.getmUser_id().trim());
                    int user_per_id = Integer.parseInt(resultadoUser.getmUser_per_id().trim());
                    int user_tipo = Integer.parseInt(resultadoUser.getmUser_tipo().trim());
                    String user_name = resultadoUser.getmUser_name();
                    String apellido = resultadoUser.getmUser_apellido();
                    String nombre = resultadoUser.getmUser_nombre();
                    String celuar = resultadoUser.getmUser_celular().trim();
                    int user_status = Integer.parseInt(resultadoUser.getmUser_status().trim());
                    int empresa_id = Integer.parseInt(resultadoUser.getmUser_empresa_id());
                    String email = resultadoUser.getmUser_email();

                    String empresa = "ADMINISTRACION";
                    if (empresa_id!=0) {
                        empresa = resultadoUser.getmUser_empresa();
                    }
                    if (user_status==USUARIO_CONFIRMADO) {
                        Remis24x7User user = SharedPrefManager.getInstance(LoginActivity.this).getUser();
                        user.setUser_id(user_id);
                        user.setUser_tipo(user_tipo);
                        user.setUser_apellido(apellido);
                        user.setUser_nombre(nombre);
                        user.setUser_celular(celuar);
                        user.setUser_per_id(user_per_id);
                        user.setUser_email(email);
                        user.setUser_empresa(empresa);
                        user.setUser_empresa_id(empresa_id);


                        user.setRecordarLogin(chk_recordar.isChecked());
                        if (chk_recordar.isChecked()) {
                            user.setUser_name(user_name);
                            user.setUser_password(cifrado.encryptText(txtpassword.getText().toString()));
                        }else{
                            user.setUser_name("");
                            user.setUser_password("");
                        }
                        // obtener y registrar en el servidor el token FCM
                        //getFCMToken(user_id);
                        // -----------------------------------------------
                        pb.setVisibility(View.INVISIBLE);
                        SharedPrefManager.getInstance(LoginActivity.this).saveUser(user);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else{
                        pb.setVisibility(View.INVISIBLE);
                        if (user_status==USUARIO_NO_CONFIRMADO){
                            Toast.makeText(LoginActivity.this, "Debes confirmar tu dirección de mail", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(LoginActivity.this, "NO EXISTE EL USUARIO INGRESADO", Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, descripcion, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();
            }
        });

    } //doLogin

    private void doResetContrasena(){
        // retrofit enviar email con token - cargar activity: confirmarusuarioactivity
        pb.setVisibility(View.VISIBLE);
        bodyHash = Setting.getBodyHash(txtusuario.getText().toString().trim());
        retrofit = new Retrofit.Builder()
                .baseUrl(urlResetContrasena)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ApiError> call = jsonPlaceHolderApi.resetContrasena(Setting.tokenAPP, bodyHash, txtusuario.getText().toString().trim());

        call.enqueue(new Callback<ApiError>() {
            @Override
            public void onResponse(Call<ApiError> call, Response<ApiError> response) {

                if (!response.isSuccessful()) {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }

                ApiError resultadoUser = response.body();

                String status = resultadoUser.getmStatus();
                String descripcion = resultadoUser.getmDescripcion();
                pb.setVisibility(View.INVISIBLE);
                if (status.equals("EXITO")) {
                    Toast.makeText(LoginActivity.this, "Nueva contraseña enviada. Chequear mail", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, descripcion, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiError> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Se produjo un error. Intenta nuevamente.", Toast.LENGTH_LONG).show();
            }
        });


    }//doResetContrasena

    // FCM -----------------------------------------------------------------------------------------
    private void getFCMToken(int user_id){
        final int userId = user_id;
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println( "Fetching FCM registration token failed" +  task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String fcmToken = task.getResult();
                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        //System.out.print(fcmToken);
                        guardarFCM(fcmToken, userId);
                    }
                });
    }

    // Guardo fcm en la base
    private void guardarFCM(String fcmToken, int user_id){
        bodyHash = Setting.getBodyHash(String.valueOf(user_id).trim());
        retrofit = new Retrofit.Builder()
                .baseUrl(urlFCM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(Api.class);

        Call<ApiError> call = jsonPlaceHolderApi.registrarfcm(Setting.tokenAPP, bodyHash, user_id, fcmToken);

        call.enqueue(new Callback<ApiError>() {
            @Override
            public void onResponse(Call<ApiError> call, Response<ApiError> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Notificaciones: " + response.message(), Toast.LENGTH_LONG).show();
                    return;
                }else{

                    ApiError resultadoUser = response.body();
                    String status = resultadoUser.getmStatus();
                    String descripcion = resultadoUser.getmDescripcion();

                    if (status.equals("EXITO")) {
                        Toast.makeText(LoginActivity.this, "Notificaciones habilitadas!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, descripcion, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ApiError> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Se produjo un error. Notificaciones no disponibles.", Toast.LENGTH_LONG).show();
            }
        });
    }//
}
