package com.remis24x7agencias.utils;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
    String passwordEncriptacion = "3d36dbd8a3cac44199864482ec7265ec";//"24x7R24x7e24x7m24x7i24x7s";

    public Cifrado() {
    }

    public String encryptText(String cadena) {
        String encrypt_pwd = "";
        //int len = cadena.length();
        //len /= 2;
        //StringBuilder b1 = new StringBuilder(cadena.substring(0, len));
        //StringBuilder b2 = new StringBuilder(cadena.substring(len));
        //cadena = b1.reverse().toString() + b2.reverse().toString();
        try {
            encrypt_pwd = encriptar(cadena, passwordEncriptacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt_pwd;
    }

    public String decryptText(String cadena) {
        String decrypt_pwd = "";
        //int len = cadena.length();
        //len /= 2;
        //StringBuilder b1 = new StringBuilder(cadena.substring(0, len));
        //StringBuilder b2 = new StringBuilder(cadena.substring(len));
        //b1.reverse();
        //b2.reverse();
        //cadena = b1.toString() + b2.toString();
        try {
            decrypt_pwd = desencriptar(cadena, passwordEncriptacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt_pwd;
    }

    private String desencriptar(String datos, String password) throws Exception {
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDescoficados = Base64.decode(datos, Base64.DEFAULT);
        byte[] datosDesencriptadosByte = cipher.doFinal(datosDescoficados);
        String datosDesencriptadosString = new String(datosDesencriptadosByte);
        return datosDesencriptadosString;
    }

    private String encriptar(String datos, String password) throws Exception {
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return datosEncriptadosString;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }

}
