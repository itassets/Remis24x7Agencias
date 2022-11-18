package com.remis24x7agencias.net;


import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Setting {
    public static final String apiSecret = "94ea3289278da1ad8d3a41ead20283d89323621d69b1cb0ffa1a10145da2778b22c2a4886e83c77bef9129f389479097b2d1f2854ad5330aebe97bd667b498f7";
    public static String tokenAPP;

    public static String appVersion = "1.0";
    public static String googleMapsApiKey = "AIzaSyCsMqN8uUUJl8cNWKhPVJLOsKuhrWtmei4";
    private static final String[] indexes = {"toppings" , "poppings"};
    private static final String[] toppings = {"azUl", "Catalina", "tomatE", "Tango", "BRAVO", "Lima", "FoxProx"};
    private static final String[] poppings = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};

    //private static final String IP = "http://192.168.0.136/";
    private static final String IP = "https://api.24x7.com.ar/"; //"https://24x7.com.ar/";

    //Credencial Digital URL
    public static final String urlCredencialDigital = "https://bo.24x7.com.ar/credenciales/";

    //Terminos y Condiciones URL
    public static final String urlTerminosYCondiciones = "http://24x7.com.ar/tyc/tyc_chofer.html";

    //Política Privacidad URL
    public static final String urlPoliticaPrivacidad = "https://24x7.com.ar/tyc/privacidad_chofer.html";

    // Octubre 2021: migracion al VPS. api.24x7.com.ar apunta a remisapi por lo tanto se remueven de todas las urls.
    // Todos los static final String url... deben concatenar sin "remisapi" directamente "public/....."

    public static final String urlJelow = IP + "public/api/"; // "remiseapi/public/api/";
    // USERS
    public static final String urllogin = IP + "public/api/users/"; //"remiseapi/public/api/users/";
    public static final String urlResetContrasena =  IP + "public/api/users/resetpass/";
    public static final String urlSetContrasena =  IP + "public/api/users/setcontra/";
    // VIAJES
    public static final String urlviajependiente =  IP + "public/api/viajes/";
    public static final String urlGetViaje =   IP + "public/api/viaje/";
    public static final String urlGetViajeChofer =   IP + "public/api/viajechofer/";
    public static final String urlAceptarRechazar = IP + "public/api/viajes/chofer/";
    public static final String urlUbicacionChofer = IP + "public/api/viajes/chofer/";

    public static final String urlListadoViajes = IP + "public/api/viajes/";
    public static final String urlHistorialViajes = IP + "public/api/viajes/agencia/chofer/viajes/";
    public static final String urlAbonarViaje = IP + "public/api/viajes/agencia/viaje/";
    public static final String urlCalificarPax = IP + "public/api/viajes/chofer/viaje/";
    // MAPS
    public static final String urlGetAddress =  IP + "public/api/maps/";
    //public static final String urlGetActualAddress=  IP + "public/api/maps/getActualAddress/";
    //public static final String urlGetDistance =  IP + "public/api/maps/";
    public static final String urlGetDirections = IP + "public/api/maps/";


    // AGENCIAS
    public static final String urlAgenciaChoferViajesReservas = IP + "public/api/viajes/agencia/chofer/viajesreservas2/";
    public static final String urlGetChoferAuto = IP + "public/api/viajes/agencia/chofer/auto/";
    public static final String urlAgenciaChoferCantViajesReservas = IP + "public/api/viajes/agencia/chofer/";
    public static final String urlAgenciaViajeCobrado = IP + "public//api/viajes/agencia/viaje/";

    public static final String urlFotoChofer = "";
    public static final String urlFotoAuto = "";
    public static final String urlAgenciaChoferViajeActual = IP + "public/api/viajes/agencia/chofer/viajeactual/";

    //FCM
    public static final String urlFCM =  IP + "public/api/users/fcm/registrarfcm/";

    public static final String homeAddress = "Av. Mitre 585 Quilmes Argentina";
    public static final double homeLatitude = -34.720386;
    public static final double homeLongitude = -58.254809;

    public static final int PAX_COMUN = 0;
    public static final int PAX_EMPRESA = 1;
    public static final int CHOFER = 4;

    public static final int USUARIO_CONFIRMADO = 1;
    public static final int USUARIO_NO_CONFIRMADO = 2;

    public static final String PAGO_EFECTIVO = "1";
    public static final String PAGO_CUENTA_CORRIENTE = "2";
    public static final String PAGO_BILLETERA_ELECTRONICA = "3";
    public static final String PAGO_TARJETA_CREDITO = "4";

    public static final String TIPO_VIAJE_PAX_COMUN = "PAX";
    public static final String TIPO_VIAJE_EMPRESA = "EMPRESA";
    public static final String TIPO_VIAJE_TABULADO_EMPRESA = "TABULADO";
    public static final String TIPO_VIAJE_PREMIUN = "PREMIUN";

    public static final String CALIFICACION_BUENA = "BUENA";
    public static final String CALIFICACION_NEUTRA = "NEUTRA";
    public static final String CALIFICACION_MALA = "MALA";

    public static final String CHOFER_EN_CAMINO = "CY";
    public static final String CHOFER_EN_PUERTA_PAX = "CP";

    public static double lastLatitud = 0.0;
    public static double lastLongitud = 0.0;

    public Setting() {
    }

    private static String getWord(String index){
        Locale locale = new Locale( "es" , "AR" );
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        String hoy = simpleDateFormat.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int d = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (index.equals("toppings")){
            return toppings[d] + hoy;
        }else{
            return poppings[d] + hoy;
        }
    }

    public static String getHoy() {
        Locale locale = new Locale( "es" , "AR" );
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        String hoy = simpleDateFormat.format(new Date());
        return hoy;
    }

    public static String getBodyHash(String message){
        String bodyHash = "";
        Locale locale = new Locale( "es" , "AR" );
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        String hoy = simpleDateFormat.format(new Date());
        bodyHash = getHash(message + hoy);
        return bodyHash;
    }

    public static String getHash(String message) {
        String token = "";
        String key = Setting.apiSecret;

        try {
            final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"

            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());

            token = bytesToHex(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String getToken() {
        String token = "";
        String key = Setting.apiSecret;
        Random random = new Random();
        int index = random.nextInt(2);
        String cadena = indexes[index];
        String message = getWord(cadena);
        try {
            final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"

            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());

            token = bytesToHex(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static byte[] hmac(String algorithm, byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }

    public static String setSinTildes(String cadena){
        String resultado = cadena.replace('á','a');
        resultado = resultado.replace('é', 'e');
        resultado = resultado.replace('í', 'i');
        resultado = resultado.replace('ó', 'o');
        resultado = resultado.replace('ú', 'u');
        resultado = resultado.replace('Á', 'A');
        resultado = resultado.replace('É', 'E');
        resultado = resultado.replace('Í', 'I');
        resultado = resultado.replace('Ó', 'O');
        resultado = resultado.replace('Í', 'U');
        return resultado;
    }

}
