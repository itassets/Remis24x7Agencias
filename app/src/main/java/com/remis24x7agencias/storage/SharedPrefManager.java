package com.remis24x7agencias.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.remis24x7agencias.Remis24x7User;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "Remis24x7_Agencias_Prefs";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if (mInstance==null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(Remis24x7User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("firstart", user.getFirstStart());
        editor.putBoolean("recordarLogin", user.getRecordarLogin());
        editor.putInt("user_id", user.getUser_id());
        editor.putString("user_name", user.getUser_name());
        editor.putString("user_password", user.getUser_password());
        editor.putString("user_apellido", user.getUser_apellido());
        editor.putString("user_nombre", user.getUser_nombre());
        editor.putString("user_celular", user.getUser_celular());
        editor.putInt("user_tipo", user.getUser_tipo());
        editor.putInt("user_per_id", user.getUser_per_id());
        editor.putInt("user_empresa_id", user.getUser_empresa_id());
        editor.putString("user_empresa", user.getUser_empresa());
        editor.putString("user_email", user.getUser_email());

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return (sharedPreferences.getInt("user_id", -1) != -1);
    }

    public boolean isFirstStart(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return (sharedPreferences.getBoolean("firstart", false));
    }

    public Remis24x7User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new Remis24x7User(
                sharedPreferences.getBoolean("firstart", true),
                sharedPreferences.getBoolean("recordarLogin", false),
                sharedPreferences.getInt("user_id", -1),
                sharedPreferences.getString("user_name", ""),
                sharedPreferences.getString("user_password", ""),
                sharedPreferences.getString("user_apellido", ""),
                sharedPreferences.getString("user_nombre", ""),
                sharedPreferences.getString("user_celular", ""),
                sharedPreferences.getInt("user_tipo", -1),
                sharedPreferences.getInt("user_per_id", -1),
                sharedPreferences.getInt("user_empresa_id",  -1),
                sharedPreferences.getString("user_empresa",""),
                sharedPreferences.getString("user_email", ""));
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean firstStart =  sharedPreferences.getBoolean("firstart", false);
        editor.clear();
        editor.putBoolean("firstart", firstStart);
        editor.apply();
    }
}
