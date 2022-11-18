package com.remis24x7agencias.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Directions {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("descripcion")
    private String mDescripcion;

    @SerializedName("directions")
    private String[] mDirections;

    public Directions(String mStatus, String mDescripcion, String[] mDirections) {
        this.mStatus = mStatus;
        this.mDescripcion = mDescripcion;
        this.mDirections = mDirections;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public void setmDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    public String[] getmDirections() {
        return mDirections;
    }

    public void setmDirections(String[] mDirections) {
        this.mDirections = mDirections;
    }

    public String[] getPaths(JSONArray jsonArray){
        int count = jsonArray.length();
        String[] polylines = new String[count];
        try {
            for (int i = 0; i < count; i++) {
                polylines[i] = getPath(jsonArray.getJSONObject(i));
            }
        }catch (JSONException e){
            e.printStackTrace();
            polylines = null;
        }
        return polylines;
    }


    public String getPath(JSONObject jsonObject){
        String polyline = null;
        try {
            polyline = jsonObject.getJSONObject("polyline").getString("points");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return polyline;
    }
}

