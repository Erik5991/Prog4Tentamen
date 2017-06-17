package com.example.erik.prog4tentamen.controller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Teunvz on 15-6-2017.
 */

public class TokenController {
    private Context context;
    private SharedPreferences tokenSharedPreference;


    public TokenController(Context activityContext){
        context = activityContext;
        tokenSharedPreference = context.getSharedPreferences("token", context.MODE_PRIVATE);
    }

    public void setToken(String token){
        tokenSharedPreference.edit().putString("token", token).apply();
        tokenSharedPreference.edit().commit();
    }

    public void setID(Integer ID){
        tokenSharedPreference.edit().putString("id", ID + "").apply();
        tokenSharedPreference.edit().commit();
    }

    public String getToken(){
        return tokenSharedPreference.getString("token", null);
    }

    public String getID(){
        return tokenSharedPreference.getString("id", null);
    }

    public void clearToken(){
        tokenSharedPreference.edit().clear().apply();
    }
}