package com.remis24x7agencias.model;


import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("user_id")
    private String mUser_id;               // en caso de éxito id usuario en tabla usuario

    @SerializedName("user_per_id")
    private String mUser_per_id;           // id del usuario en la tabla correspondiente (pax, pax_empresa, chofer)

    @SerializedName("user_tipo")
    private String mUser_tipo;              // tipo de usuario:0:pax, 1:pax_empresa, 2: operador sistema,
    // 3: operador agencia, 4: chofer agencia, 8:invitado,
    // 9:administrador sistema (2,3,4,9 tabla personal)

    @SerializedName("user_name")
    private String mUser_name;             // usuario de login al sistema/app

    @SerializedName("user_apellido")
    private String mUser_apellido;         // apellido del usuario

    @SerializedName("user_nombre")
    private String mUser_nombre;           // nombre del usuario

    @SerializedName("user_email")
    private String mUser_email;            // mail del usuario

    @SerializedName("user_celular")
    private String mUser_celular;

    @SerializedName("user_status")
    private String mUser_status;           // estado del usuario en la tabla usuario

    @SerializedName("user_empresa_id")
    private String mUser_empresa_id;       // el usuario es de empresa, id de la empresa

    @SerializedName("user_empresa")
    private String mUser_empresa;          // la razon social de la empresa

    @SerializedName("user_cco_id")
    private String mUser_cco_id;           // id del centro de costo

    @SerializedName("user_cco_denominacion")
    private String mUser_cco_denominacion; // nombrel del centro de costo

    @SerializedName("user_agn_id")
    private String mUser_agn_id;           // si el usuario es chofer id de la agencia del chofer

    @SerializedName("user_agn_denominacion")
    private String mUser_agn_denominacion; // nombre de la agencia

    public UserLogin(String mStatus, String mDescripcion, String mUser_id, String mUser_per_id, String mUser_tipo, String mUser_name, String mUser_apellido, String mUser_nombre, String mUser_email, String mUser_celular, String mUser_status, String mUser_empresa_id, String mUser_empresa, String mUser_cco_id, String mUser_cco_denominacion, String mUser_agn_id, String mUser_agn_denominacion) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mUser_id = mUser_id;
        this.mUser_per_id = mUser_per_id;
        this.mUser_tipo = mUser_tipo;
        this.mUser_name = mUser_name;
        this.mUser_apellido = mUser_apellido;
        this.mUser_nombre = mUser_nombre;
        this.mUser_email = mUser_email;
        this.mUser_celular = mUser_celular;
        this.mUser_status = mUser_status;
        this.mUser_empresa_id = mUser_empresa_id;
        this.mUser_empresa = mUser_empresa;
        this.mUser_cco_id = mUser_cco_id;
        this.mUser_cco_denominacion = mUser_cco_denominacion;
        this.mUser_agn_id = mUser_agn_id;
        this.mUser_agn_denominacion = mUser_agn_denominacion;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public String getmUser_id() {
        return mUser_id;
    }

    public String getmUser_per_id() {
        return mUser_per_id;
    }

    public String getmUser_tipo() {
        return mUser_tipo;
    }

    public String getmUser_name() {
        return mUser_name;
    }

    public String getmUser_apellido() {
        return mUser_apellido;
    }

    public String getmUser_nombre() {
        return mUser_nombre;
    }

    public String getmUser_email() {
        return mUser_email;
    }

    public String getmUser_celular() {
        return mUser_celular;
    }

    public String getmUser_status() {
        return mUser_status;
    }

    public String getmUser_empresa_id() {
        return mUser_empresa_id;
    }

    public String getmUser_empresa() {
        return mUser_empresa;
    }

    public String getmUser_cco_id() {
        return mUser_cco_id;
    }

    public String getmUser_cco_denominacion() {
        return mUser_cco_denominacion;
    }

    public String getmUser_agn_id() {
        return mUser_agn_id;
    }

    public String getmUser_agn_denominacion() {
        return mUser_agn_denominacion;
    }
}
